package org.wooteco.pre.convenienceStore.controller;

import org.wooteco.pre.convenienceStore.service.StoreService;
import org.wooteco.pre.convenienceStore.view.OutputView;

public class StoreController {
    private final StoreService storeService;
    private final OutputView outputView;

    public StoreController(final StoreService storeService, final OutputView outputView) {
        this.storeService = storeService;
        this.outputView = outputView;
    }

    public void open() {
        storeService.makeStore();
        outputView.printGreetings();
        outputView.printPresentProductsStatus(storeService.createProductsDto());
    }

}
