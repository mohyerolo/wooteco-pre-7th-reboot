package org.wooteco.pre.convenienceStore.service;

import org.wooteco.pre.convenienceStore.domain.product.ProductFactory;
import org.wooteco.pre.convenienceStore.domain.promotion.Promotion;
import org.wooteco.pre.convenienceStore.domain.promotion.PromotionFactory;
import org.wooteco.pre.convenienceStore.util.FileReaderUtil;

import java.util.List;

public class StoreService {
    private static final String promotionPath = "src/main/resources/promotions.md";
    private static final String productPath = "src/main/resources/products.md";

    public void makeStore() {
        List<Promotion> promotions = createPromotion();
        createProductStorage(promotions);
    }

    private List<Promotion> createPromotion() {
        List<String> promotionData = FileReaderUtil.readFile(promotionPath);
        promotionData.removeFirst();
        return PromotionFactory.createPromotions(promotionData);
    }

    private void createProductStorage(final List<Promotion> promotions) {
        List<String> productData = FileReaderUtil.readFile(productPath);
        productData.removeFirst();
        ProductFactory.createProductStorage(productData, promotions.stream());
    }
}
