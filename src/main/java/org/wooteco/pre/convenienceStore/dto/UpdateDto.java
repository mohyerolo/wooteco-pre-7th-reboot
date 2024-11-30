package org.wooteco.pre.convenienceStore.dto;

import org.wooteco.pre.convenienceStore.domain.order.UpdateOrderItem;

public class UpdateDto {
    private final String productName;
    private final int quantity;

    public UpdateDto(final UpdateOrderItem item) {
        this.productName = item.getOrderItem().getProduct().getName();
        this.quantity = item.getQuantity();
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }
}
