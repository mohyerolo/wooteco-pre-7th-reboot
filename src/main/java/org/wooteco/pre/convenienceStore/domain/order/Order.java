package org.wooteco.pre.convenienceStore.domain.order;

import org.wooteco.pre.convenienceStore.constants.Membership;

import java.util.List;

public class Order {
    private final List<OrderItem> orderItems;
    private final Membership membership;

    public Order(final List<OrderItem> orderItems, final Membership membership) {
        this.orderItems = orderItems;
        this.membership = membership;
    }

}
