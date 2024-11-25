package org.wooteco.pre.convenienceStore.dto;

import org.wooteco.pre.convenienceStore.domain.product.Product;

public record ProductDto(String name, int price, int stock, String promotionName) {
    private static final String NO_PROMOTION_NAME = "null";

    public static ProductDto from(final Product product) {
        return new ProductDto(product.getName(), product.getPrice(), product.getStock(), product.getPromotion().getName());
    }

    public boolean isPromotionExists() {
        return !promotionName.equals(NO_PROMOTION_NAME);
    }

    public boolean isStockOut() {
        return stock == 0;
    }
}
