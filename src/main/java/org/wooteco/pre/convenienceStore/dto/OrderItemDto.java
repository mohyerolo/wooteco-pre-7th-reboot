package org.wooteco.pre.convenienceStore.dto;

import org.wooteco.pre.convenienceStore.domain.order.OrderItem;

public class OrderItemDto {
    private final String name;
    private final int quantity;
    private final int totalPrice;

    public OrderItemDto(final OrderItem orderItem) {
        this.name = orderItem.getProduct().getName();
        this.quantity = orderItem.getQuantity();
        this.totalPrice = orderItem.calcTotalPrice();
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
