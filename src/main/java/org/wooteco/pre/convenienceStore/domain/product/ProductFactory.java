package org.wooteco.pre.convenienceStore.domain.product;

import org.wooteco.pre.convenienceStore.dao.ProductDao;
import org.wooteco.pre.convenienceStore.domain.promotion.NoPromotion;
import org.wooteco.pre.convenienceStore.domain.promotion.Promotion;

import java.util.List;
import java.util.stream.Stream;

public class ProductFactory {
    private static final String DELIMITER = ",";

    private static final ProductDao productDao = ProductDao.getInstance();

    public static void createProductStorage(final List<String> productData, final Stream<Promotion> promotions) {
        productData.forEach(data -> {
            String[] splitData = splitPromotion(data);
            productDao.addProduct(Product.of(splitData, createPromotion(splitData[3], promotions)));
        });
    }

    private static String[] splitPromotion(final String data) {
        return data.split(DELIMITER);
    }

    private static Promotion createPromotion(final String promotionName, final Stream<Promotion> promotions) {
        return promotions
                .filter(promotion -> promotion.isSamePromotion(promotionName))
                .findFirst()
                .orElseGet(NoPromotion::new);
    }
}
