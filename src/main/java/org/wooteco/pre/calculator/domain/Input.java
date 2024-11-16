package org.wooteco.pre.calculator.domain;

import org.wooteco.pre.calculator.exception.CustomIllegalException;

public class Input {
    private static final String BLANK_ERROR = "입력 값은 비어있으면 안됩니다";
    private final String input;

    public Input(String input) {
        validateBlank(input);
        this.input = input;
    }

    private void validateBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new CustomIllegalException(BLANK_ERROR);
        }
    }



}
