package org.wooteco.pre.lotto.validator;

import org.wooteco.pre.lotto.exception.CustomIllegalException;

public class ViewValidator {
    private static final String BLANK_ERROR = "입력값이 비어있습니다.";
    private static final String NOT_NUM_ERROR = "해당 부분에는 숫자 필요합니다";
    private static final String NUM_REGEX = "^[0-9]*$";

    public static void validateBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new CustomIllegalException(BLANK_ERROR);
        }
    }

    public static void validateNum(String input) {
        if (!input.matches(NUM_REGEX)) {
            throw new CustomIllegalException(NOT_NUM_ERROR);
        }
    }
}
