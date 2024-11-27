package org.wooteco.pre.convenienceStore.domain.product;

import org.wooteco.pre.convenienceStore.dao.ProductDao;
import org.wooteco.pre.convenienceStore.domain.promotion.NoPromotion;
import org.wooteco.pre.convenienceStore.domain.promotion.Promotion;

import java.util.List;

public class ProductFactory {
    private static final String DELIMITER = ",";

    public static void createProductStorage(final ProductDao productDao, final List<String> productData, final List<Promotion> promotions) {
        productData.forEach(data -> {
            String[] splitData = splitPromotion(data);
            productDao.addProduct(Product.of(splitData, createPromotion(splitData[3], promotions)));
        });
    }

    private static String[] splitPromotion(final String data) {
        return data.split(DELIMITER);
    }

    private static Promotion createPromotion(final String promotionName, final List<Promotion> promotions) {
        return promotions.stream()
                .filter(promotion -> promotion.isSamePromotion(promotionName))
                .findFirst()
                .orElseGet(NoPromotion::new);
    }
}
