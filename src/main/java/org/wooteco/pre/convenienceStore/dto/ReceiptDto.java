package org.wooteco.pre.convenienceStore.dto;

import org.wooteco.pre.convenienceStore.domain.order.Receipt;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ReceiptDto {
    private final List<OrderItemDto> orderItems;
    private final List<OrderItemDto> freeItems;
    private final int promotionDiscount;
    private final int membershipDiscount;

    public ReceiptDto(final List<OrderItemDto> orderItems, final List<OrderItemDto> freeItems, final int promotionDiscount, final int membershipDiscount) {
        this.orderItems = orderItems;
        this.freeItems = freeItems;
        this.promotionDiscount = promotionDiscount;
        this.membershipDiscount = membershipDiscount;
    }

    public static ReceiptDto from(final Receipt receipt) {
        List<OrderItemDto> orderItems = receipt.getOrderItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
        List<OrderItemDto> freeItems = receipt.getFreeItems().stream().map(OrderItemDto::new).collect(Collectors.toList());

        return new ReceiptDto(orderItems, freeItems, receipt.getPromotionDiscount(), receipt.getMembershipDiscount());
    }

    public int getTotalQuantity() {
        return orderItems.stream()
                .mapToInt(OrderItemDto::getQuantity)
                .sum();
    }

    public int getTotalPrice() {
        return orderItems.stream()
                .mapToInt(OrderItemDto::getTotalPrice)
                .sum();
    }

    public List<OrderItemDto> getOrderItems() {
        return Collections.unmodifiableList(orderItems);
    }

    public List<OrderItemDto> getFreeItems() {
        return Collections.unmodifiableList(freeItems);
    }

    public int getPromotionDiscount() {
        return promotionDiscount;
    }

    public int getMembershipDiscount() {
        return membershipDiscount;
    }

}