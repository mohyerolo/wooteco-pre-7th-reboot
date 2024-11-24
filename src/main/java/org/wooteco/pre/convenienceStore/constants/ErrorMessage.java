package org.wooteco.pre.convenienceStore.constants;

public enum ErrorMessage {
    INPUT_TYPE_ERROR("올바르지 않은 형식으로 입력했습니다"),
    NON_EXIST_PRODUCT("존재하지 않는 상품입니다"),
    EXCEEDED_STOCK("재고 수량을 초과하여 구매할 수 없습니다");

    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
