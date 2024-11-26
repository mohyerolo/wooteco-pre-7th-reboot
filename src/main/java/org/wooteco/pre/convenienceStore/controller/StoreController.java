package org.wooteco.pre.convenienceStore.controller;

import org.wooteco.pre.convenienceStore.config.AppConfig;
import org.wooteco.pre.convenienceStore.domain.order.Order;
import org.wooteco.pre.convenienceStore.service.OrderItemService;
import org.wooteco.pre.convenienceStore.service.OrderService;
import org.wooteco.pre.convenienceStore.service.ProductService;
import org.wooteco.pre.convenienceStore.service.StoreService;
import org.wooteco.pre.convenienceStore.view.InputView;
import org.wooteco.pre.convenienceStore.view.OutputView;

import java.util.function.Supplier;

public class StoreController {
    private static final InputView inputView = AppConfig.inputView();
    private final OutputView outputView = AppConfig.outputView();

    private final StoreService storeService;
    private final ProductService productService;
    private OrderService orderService;

    public StoreController(final StoreService storeService, final ProductService productService) {
        this.storeService = storeService;
        this.productService = productService;
    }

    public void open() {
        setup();
        orderService = new OrderService(new OrderItemService(productService));
        Order order = takeOrder();
    }

    private void setup() {
        storeService.makeStore();
        outputView.printGreetings();
        outputView.printPresentProductsStatus(productService.createProductsDto());
    }

    private Order takeOrder() {
        return executeWithRetry(() -> orderService.createOrder(inputView.readOrder()));
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
