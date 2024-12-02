package org.wooteco.pre.convenienceStore.constants;

public enum OutputMessage {
    GREETINGS("안녕하세요. W편의점입니다."),
    PRESENT_PRODUCTS("현재 보유하고 있는 상품입니다."),
    PRODUCT_TEMPLATE("- %s %,d원 %s"),
    PRODUCT_STOCK_TEMPLATE("%,d개"),
    PRODUCT_STOCK_OUT("재고없음"),
    PRODUCT_PROMOTION_TEMPLATE("%s %s"),

    RECEIPT_START("===========W 편의점============="),
    RECEIPT_COL("상품명\t\t수량\t금액"),
    RECEIPT_PRODUCT("%s\t\t%d\t%,d"),
    RECEIPT_PROMOTION("===========증\t정============="),
    RECEIPT_PROMOTION_PRODUCT("%s\t\t%d"),
    RECEIPT_DIVIDER("=============================="),
    RECEIPT_TOTAL("총구매액\t\t%d\t%,d"),
    RECEIPT_PROMOTION_DISCOUNT("행사할인\t\t\t-%,d"),
    RECEIPT_MEMBERSHIP_DISCOUNT("멤버십할인\t\t\t-%,d"),
    RECEIPT_REAL_AMOUNT("내실돈\t\t\t %,d");

    private final String message;

    OutputMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
