package org.wooteco.pre.convenienceStore.domain.order;

import org.wooteco.pre.convenienceStore.constants.UpdateItemType;

public class UpdateOrderItem {
    private final OrderItem orderItem;
    private final UpdateItemType updateType;
    private final int quantity;

    private UpdateOrderItem(OrderItem orderItem, UpdateItemType updateType, int quantity) {
        this.orderItem = orderItem;
        this.updateType = updateType;
        this.quantity = quantity;
    }

    public static UpdateOrderItem unavailableOf(final OrderItem orderItem, final int quantity) {
        return new UpdateOrderItem(orderItem, UpdateItemType.UNAVAILABLE_PROMOTION, quantity);
    }

    public static UpdateOrderItem addableOf(final OrderItem orderItem, final int quantity) {
        return new UpdateOrderItem(orderItem, UpdateItemType.ADDABLE_ITEM, quantity);
    }

    public boolean isAddable() {
        return updateType.equals(UpdateItemType.ADDABLE_ITEM);
    }

    public void increaseQuantity() {
        orderItem.addQuantity(quantity);
    }

    public void decreaseQuantity() {
        orderItem.decreaseQuantity(quantity);
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public int getQuantity() {
        return quantity;
    }
}
