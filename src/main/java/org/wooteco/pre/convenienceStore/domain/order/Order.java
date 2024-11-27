package org.wooteco.pre.convenienceStore.domain.order;

import org.wooteco.pre.convenienceStore.constants.Membership;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Order {
    private final List<OrderItem> orderItems = new ArrayList<>();
    private final Membership membership;

    public Order(final Membership membership) {
        this.membership = membership;
    }

    public void addOrUpdate(final OrderItem orderItem, int stock) {
        findSameProduct(orderItem)
                .ifPresentOrElse(
                        existingItem -> existingItem.addQuantity(stock, orderItem.getQuantity()),
                        () -> orderItems.add(orderItem)
                );
    }

    private Optional<OrderItem> findSameProduct(OrderItem newItem) {
        return orderItems.stream()
                .filter(orderItem -> orderItem.isSameProduct(newItem))
                .findFirst();
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Membership getMembership() {
        return membership;
    }
}
