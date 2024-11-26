package org.wooteco.pre.convenienceStore;

import org.wooteco.pre.convenienceStore.config.AppConfig;
import org.wooteco.pre.convenienceStore.controller.StoreController;

public class Application {
    public static void main(String[] args) {
        StoreController storeController = AppConfig.setStore();
        storeController.open();
    }
}
