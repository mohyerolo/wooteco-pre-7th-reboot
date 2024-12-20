package org.wooteco.pre.convenienceStore.domain.order;

import org.wooteco.pre.convenienceStore.constants.Membership;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Order {
    private final List<OrderItem> orderItems = new ArrayList<>();
    private Membership membership;

    public Order(final Membership membership) {
        this.membership = membership;
    }

    public void addOrUpdate(final OrderItem orderItem, final int stock) {
        findSameProduct(orderItem)
                .ifPresentOrElse(
                        existingItem -> existingItem.addQuantity(stock, orderItem.getQuantity()),
                        () -> orderItems.add(orderItem)
                );
    }

    public List<OrderItem> getHavingPromotionItem() {
        return orderItems.stream()
                .filter(OrderItem::isExistPromotion)
                .toList();
    }

    public boolean isStillExist() {
        return orderItems.stream()
                .anyMatch(OrderItem::exist);
    }

    public void applyMembership() {
        membership = Membership.DEFAULT;
    }

    public List<OrderItem> getOrderItems() {
        return Collections.unmodifiableList(orderItems);
    }

    public Membership getMembership() {
        return membership;
    }

    private Optional<OrderItem> findSameProduct(final OrderItem newItem) {
        return orderItems.stream()
                .filter(orderItem -> orderItem.isSameProduct(newItem))
                .findFirst();
    }
}
