package org.wooteco.pre.convenienceStore.validator;

import org.wooteco.pre.convenienceStore.constants.ErrorMessage;
import org.wooteco.pre.convenienceStore.exception.CustomIllegalException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class InputValidator {
    private static final String INT_REGEX = "^[1-9]\\d*$";
    private static final String STRING_EXP = "(?!^\\s*$).+";
    private static final String ANSWER_EXP = "Y|N";

    public static void validateNumber(final String input) {
        if (!input.matches(INT_REGEX)) {
            throw new CustomIllegalException(ErrorMessage.INPUT_TYPE_ERROR.getMessage());
        }
    }

    public static void validateDate(final String input) {
        try {
            LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            throw new CustomIllegalException(ErrorMessage.INPUT_TYPE_ERROR.getMessage());
        }
    }

    public static void validateString(final String input) {
        if (!input.matches(STRING_EXP)) {
            throw new CustomIllegalException(ErrorMessage.INPUT_TYPE_ERROR.getMessage());
        }
    }

    public static void validateAnswer(final String input) {
        if (!input.matches(ANSWER_EXP)) {
            throw new CustomIllegalException(ErrorMessage.INPUT_TYPE_ERROR.getMessage());
        }
    }
}
