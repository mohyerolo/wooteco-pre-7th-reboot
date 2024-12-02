package org.wooteco.pre.convenienceStore.controller;

import org.wooteco.pre.convenienceStore.config.AppConfig;
import org.wooteco.pre.convenienceStore.constants.Membership;
import org.wooteco.pre.convenienceStore.domain.order.Order;
import org.wooteco.pre.convenienceStore.domain.order.Receipt;
import org.wooteco.pre.convenienceStore.domain.order.UpdateOrderItem;
import org.wooteco.pre.convenienceStore.dto.ReceiptDto;
import org.wooteco.pre.convenienceStore.dto.UpdateDto;
import org.wooteco.pre.convenienceStore.service.OrderService;
import org.wooteco.pre.convenienceStore.service.ProductService;
import org.wooteco.pre.convenienceStore.service.StoreService;
import org.wooteco.pre.convenienceStore.validator.InputValidator;
import org.wooteco.pre.convenienceStore.view.InputView;
import org.wooteco.pre.convenienceStore.view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class StoreController {
    private static final String ANSWER_Y = "Y";
    private static final InputView inputView = AppConfig.inputView();
    private final OutputView outputView = AppConfig.outputView();

    private final StoreService storeService;
    private final ProductService productService;
    private final OrderService orderService;

    public StoreController(final StoreService storeService, final ProductService productService, final OrderService orderService) {
        this.storeService = storeService;
        this.productService = productService;
        this.orderService = orderService;
    }

    public void open() {
        storeService.makeStore();
        do {
            printStore();
            Order order = takeOrder();
            if (order.isStillExist()) {
                printReceipt(order);
            }
        } while (askRestart());
    }

    private void printStore() {
        outputView.printGreetings();
        outputView.printPresentProductsStatus(productService.createProductsDto());
    }

    private Order takeOrder() {
        Order order = askOrder();
        List<UpdateOrderItem> itemNeedUpdate = orderService.getItemNeedUpdate(order);
        updateOrder(itemNeedUpdate);
        return order;
    }

    private void printReceipt(final Order order) {
        applyMembership(order);
        Receipt receipt = orderService.divideItems(order);
        outputView.printReceipt(ReceiptDto.from(receipt));
    }

    private Order askOrder() {
        return executeWithRetry(() -> orderService.createOrder(inputView.readOrder(), Membership.NONE));
    }

    private void updateOrder(final List<UpdateOrderItem> updateOrderItems) {
        updateOrderItems.forEach(this::updateItem);
    }

    private void updateItem(final UpdateOrderItem item) {
        if (item.isAddable()) {
            takeFree(item);
            return;
        }
        noPromotion(item);
    }

    private void takeFree(final UpdateOrderItem item) {
        if (askTakeFree(item)) {
            item.increaseQuantity();
        }
    }

    private void noPromotion(final UpdateOrderItem item) {
        if (!askNoPromotionOk(item)) {
            item.decreaseQuantity();
        }
    }

    private boolean askTakeFree(final UpdateOrderItem item) {
        return executeWithRetry(() -> {
            String answer = inputView.readFreeTake(new UpdateDto(item));
            InputValidator.validateAnswer(answer);
            return answer.equals(ANSWER_Y);
        });
    }

    private boolean askNoPromotionOk(final UpdateOrderItem item) {
        return executeWithRetry(() -> {
            String answer = inputView.readNoPromotionOK(new UpdateDto(item));
            InputValidator.validateAnswer(answer);
            return answer.equals(ANSWER_Y);
        });
    }

    private void applyMembership(final Order order) {
        if (askMembership()) {
            order.applyMembership();
        }
    }

    private boolean askMembership() {
        return executeWithRetry(() -> {
            String answer = inputView.readMembership();
            InputValidator.validateAnswer(answer);
            return answer.equals(ANSWER_Y);
        });
    }

    private boolean askRestart() {
        return executeWithRetry(() -> {
            String answer = inputView.readRestart();
            InputValidator.validateAnswer(answer);
            return answer.equals(ANSWER_Y);
        });
    }

    private <T> T executeWithRetry(final Supplier<T> action) {
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
