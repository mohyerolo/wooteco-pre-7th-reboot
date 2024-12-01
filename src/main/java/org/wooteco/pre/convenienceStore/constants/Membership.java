package org.wooteco.pre.convenienceStore.constants;

public enum Membership {
    NONE(0),
    DEFAULT(30);

    private final int discountRate;

    Membership(final int discountRate) {
        this.discountRate = discountRate;
    }

    private int calcDiscountPrice(final int price) {
        return discountRate * price;
    }
}
