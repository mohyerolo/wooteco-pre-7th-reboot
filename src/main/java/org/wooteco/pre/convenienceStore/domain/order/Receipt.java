package org.wooteco.pre.convenienceStore.domain.order;

import org.wooteco.pre.convenienceStore.constants.Membership;

import java.util.Collections;
import java.util.List;

public class Receipt {
    private final Membership membership;
    private final List<OrderItem> orderItems;
    private final List<OrderItem> freeItems;

    public Receipt(final Membership membership, final List<OrderItem> orderItems, final List<OrderItem> freeItems) {
        this.membership = membership;
        this.orderItems = orderItems;
        this.freeItems = freeItems;
    }

    public int getPromotionDiscount() {
        return freeItems.stream()
                .mapToInt(OrderItem::calcTotalPrice)
                .sum();
    }

    public int getMembershipDiscount() {
        if (membership.equals(Membership.NONE)) {
            return 0;
        }
        return membership.applyMembershipDiscount(calcRemainPrice());
    }

    private int calcRemainPrice() {
        return orderItems.stream()
                .mapToInt(OrderItem::calcNoPromotionPrice)
                .sum();
    }

    public List<OrderItem> getOrderItems() {
        return Collections.unmodifiableList(orderItems);
    }

    public List<OrderItem> getFreeItems() {
        return Collections.unmodifiableList(freeItems);
    }
}
