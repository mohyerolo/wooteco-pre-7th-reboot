package org.wooteco.pre.lotto.exception;

public class CustomIllegalException extends IllegalArgumentException {
    private static final String ERROR_TEMPLATE = "[ERROR] %s. 다시 입력해주세요.";

    public CustomIllegalException(String message) {
        super(String.format(ERROR_TEMPLATE, message));
    }
}
