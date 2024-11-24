package org.wooteco.pre.convenienceStore.exception;

public class CustomIllegalException extends IllegalArgumentException {
    private static final String ERROR_TEMPLATE = "[Error] %s. 다시 입력해 주세요.";

    public CustomIllegalException(final String message) {
        super(String.format(ERROR_TEMPLATE, message));
    }
}
