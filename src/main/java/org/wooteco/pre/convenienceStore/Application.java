package org.wooteco.pre.convenienceStore;

import org.wooteco.pre.convenienceStore.controller.StoreController;
import org.wooteco.pre.convenienceStore.service.StoreService;
import org.wooteco.pre.convenienceStore.view.OutputView;

public class Application {
    public static void main(String[] args) {
        StoreController storeController = new StoreController(new StoreService(), new OutputView());
        storeController.open();
    }
}
