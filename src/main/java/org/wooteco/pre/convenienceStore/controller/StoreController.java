package org.wooteco.pre.convenienceStore.controller;

import org.wooteco.pre.convenienceStore.config.AppConfig;
import org.wooteco.pre.convenienceStore.constants.Membership;
import org.wooteco.pre.convenienceStore.dao.ProductDao;
import org.wooteco.pre.convenienceStore.domain.order.Order;
import org.wooteco.pre.convenienceStore.service.*;
import org.wooteco.pre.convenienceStore.view.InputView;
import org.wooteco.pre.convenienceStore.view.OutputView;

import java.util.function.Supplier;

public class StoreController {
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
        setup();
        Order order = takeOrder();
    }

    private void setup() {
        storeService.makeStore();
        outputView.printGreetings();
        outputView.printPresentProductsStatus(productService.createProductsDto());
    }

    private Order takeOrder() {
        return executeWithRetry(() -> orderService.createOrder(inputView.readOrder(), Membership.DEFAULT));
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
