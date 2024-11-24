package org.wooteco.pre.convenienceStore.domain.promotion;

import java.util.List;

public class PromotionFactory {
    private static final String DELIMITER = ",";

    public static List<Promotion> createPromotions(final List<String> promotionData) {
        return promotionData.stream()
                .map(data -> Promotion.from(splitPromotion(data)))
                .toList();
    }

    private static String[] splitPromotion(final String data) {
        return data.split(DELIMITER);
    }
}
