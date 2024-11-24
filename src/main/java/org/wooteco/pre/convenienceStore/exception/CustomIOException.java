package org.wooteco.pre.convenienceStore.exception;

public class CustomIOException extends IllegalArgumentException {
    private static final String PREFIX = "[ERROR] ";
    public CustomIOException(String message) {
        super(PREFIX + message);
    }
}
