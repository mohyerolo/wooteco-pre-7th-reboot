package org.wooteco.pre.convenienceStore.dto;

import org.wooteco.pre.convenienceStore.constants.OutputMessage;
import org.wooteco.pre.convenienceStore.domain.product.Product;

public record ProductDto(String name, int price, int stock, String promotionName) {
    private static final String NO_PROMOTION_NAME = "null";
    private static final int STOCK_OUT = 0;

    public static ProductDto from(final Product product) {
        return new ProductDto(product.getName(), product.getPrice(), product.getStock(), product.getPromotion().getName());
    }

    public static ProductDto stockOutFrom(final ProductDto productDto) {
        return new ProductDto(productDto.name, productDto.price, STOCK_OUT, NO_PROMOTION_NAME);
    }

    public boolean isPromotionExists() {
        return !promotionName.equals(NO_PROMOTION_NAME);
    }

    public boolean isStockOut() {
        return stock == STOCK_OUT;
    }
}
