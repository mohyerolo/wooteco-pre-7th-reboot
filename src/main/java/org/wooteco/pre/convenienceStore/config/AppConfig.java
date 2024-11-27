package org.wooteco.pre.convenienceStore.config;

import org.wooteco.pre.convenienceStore.controller.StoreController;
import org.wooteco.pre.convenienceStore.dao.ProductDao;
import org.wooteco.pre.convenienceStore.service.*;
import org.wooteco.pre.convenienceStore.view.InputView;
import org.wooteco.pre.convenienceStore.view.OutputView;

public class AppConfig {
    private static final ProductDao PRODUCT_DAO = new ProductDao();
    private static final ProductService PRODUCT_SERVICE = new DefaultProductService(PRODUCT_DAO);

    public static StoreController setStore() {
        return new StoreController(storeService(), PRODUCT_SERVICE, orderService());
    }

    public static InputView inputView() {
        return InputView.getInstance();
    }

    public static OutputView outputView() {
        return OutputView.getInstance();
    }

    private static StoreService storeService() {
        return new StoreService(PRODUCT_SERVICE);
    }

    private static OrderService orderService() {
        return new OrderService(new OrderItemService(PRODUCT_SERVICE));
    }
}
