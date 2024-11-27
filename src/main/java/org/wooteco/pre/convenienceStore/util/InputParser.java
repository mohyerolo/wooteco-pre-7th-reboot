package org.wooteco.pre.convenienceStore.util;

import org.wooteco.pre.convenienceStore.validator.InputValidator;

import java.time.LocalDate;

public class InputParser {
    private static final String DELIMITER = ",";

    public static int parseStringToInt(final String input) {
        InputValidator.validateNumber(input);
        return Integer.parseInt(input);
    }

    public static LocalDate parseStringToDate(final String input) {
        InputValidator.validateDate(input);
        return LocalDate.parse(input);
    }

    public static String[] splitOrderInput(final String input) {
        return input.split(DELIMITER);
    }
}
