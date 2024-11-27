package org.wooteco.pre.convenienceStore.config;

import org.wooteco.pre.convenienceStore.controller.StoreController;
import org.wooteco.pre.convenienceStore.service.StoreService;
import org.wooteco.pre.convenienceStore.view.InputView;
import org.wooteco.pre.convenienceStore.view.OutputView;

public class AppConfig {
    public static StoreController setStore() {
        return new StoreController(storeService());
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
}
