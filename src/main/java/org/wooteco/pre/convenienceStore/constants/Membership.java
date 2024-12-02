package org.wooteco.pre.convenienceStore.constants;

public enum Membership {
    NONE(0),
    DEFAULT(30);

    private static final int MAX_DISCOUNT = 8000;

    private final int discountRate;

    Membership(final int discountRate) {
        this.discountRate = discountRate;
    }

    public int applyMembershipDiscount(final int price) {
        int discount = discountRate * price;
        return Math.min(discount, MAX_DISCOUNT);
    }
}
