package org.wooteco.pre.convenienceStore.config;

import org.wooteco.pre.convenienceStore.controller.StoreController;
import org.wooteco.pre.convenienceStore.dao.ProductDao;
import org.wooteco.pre.convenienceStore.service.ProductService;
import org.wooteco.pre.convenienceStore.service.StoreService;
import org.wooteco.pre.convenienceStore.view.InputView;
import org.wooteco.pre.convenienceStore.view.OutputView;

public class AppConfig {
    public static StoreController setStore() {
        return new StoreController(storeService(), productService());
    }

    public static InputView inputView() {
        return InputView.getInstance();
    }

    public static OutputView outputView() {
        return OutputView.getInstance();
    }

    private static StoreService storeService() {
        return new StoreService();
    }

    private static ProductService productService() {
        return new ProductService(ProductDao.getInstance());
    }

}
