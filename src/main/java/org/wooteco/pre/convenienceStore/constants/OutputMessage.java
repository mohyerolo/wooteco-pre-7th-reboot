package org.wooteco.pre.convenienceStore.constants;

public enum OutputMessage {
    GREETINGS("안녕하세요. W편의점입니다."),
    PRESENT_PRODUCTS("현재 보유하고 있는 상품입니다."),
    PRODUCT_TEMPLATE("- %s %,d원 %s"),
    PRODUCT_STOCK_TEMPLATE("%,d개"),
    PRODUCT_STOCK_OUT("재고없음"),
    PRODUCT_PROMOTION_TEMPLATE("%s %s");

    private final String message;

    OutputMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
