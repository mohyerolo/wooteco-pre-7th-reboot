package org.wooteco.pre.convenienceStore.domain.order;

public class UpdateOrderItem {
    private final OrderItem orderItem;
    private final boolean addable;
    private final int quantity;

    private UpdateOrderItem(OrderItem orderItem, boolean addable, int quantity) {
        this.orderItem = orderItem;
        this.addable = addable;
        this.quantity = quantity;
    }

    public UpdateOrderItem from(final OrderItem orderItem) {

    }
}
