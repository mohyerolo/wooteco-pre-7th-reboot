package org.wooteco.pre.convenienceStore.domain.order;

import java.util.List;

public class Receipt {
    private List<OrderItem> orderItems;
    private List<OrderItem> freeItems;

    public Receipt(final List<OrderItem> orderItems, final List<OrderItem> freeItems) {
        this.orderItems = orderItems;
        this.freeItems = freeItems;
    }

}
