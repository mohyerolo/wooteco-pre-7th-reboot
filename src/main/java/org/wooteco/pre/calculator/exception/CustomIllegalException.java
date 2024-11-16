package org.wooteco.pre.calculator.exception;

public class CustomIllegalException extends IllegalArgumentException {
    private static final String MESSAGE_TEMPLATE = "[ERROR] %s";

    public CustomIllegalException(String message) {
        super(String.format(MESSAGE_TEMPLATE, message));
    }
}
