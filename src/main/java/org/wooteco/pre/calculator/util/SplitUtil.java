package org.wooteco.pre.calculator.util;

import org.wooteco.pre.calculator.exception.CustomIllegalException;

import static org.wooteco.pre.calculator.constant.SplitMessage.*;

public class SplitUtil {
    public static String splitDelimiter(final String input) {
        String str = extractCustomDelimiters(input);
        return String.format(DELIMITER_REG_EXP, str);
    }

    public static String[] splitNumbers(final String input, final String delimiter) {
        String number = getNumbers(input);
        return number.split(delimiter);
    }

    private static String extractCustomDelimiters(final String input) {
        StringBuilder sb = new StringBuilder(DEFAULT_DELIMITER);
        String str = input;
        while (str.startsWith(CUSTOM_START)) {
            int endIndex = str.indexOf(CUSTOM_END);
            String custom = getCustomDelimiter(str, endIndex);
            sb.append('|').append(custom);
            str = str.substring(endIndex + CUSTOM_END.length());
        }
        return sb.toString();
    }

    private static String getCustomDelimiter(final String input, final int endIndex) {
        int startIndex = CUSTOM_START.length();
        validateInput(input, startIndex, endIndex);
        return checkMetaCharacters(input.substring(startIndex, endIndex));
    }

    private static void validateInput(final String input, final int startIndex, final int endIndex) {
        if (endIndex == -1) {
            throw new CustomIllegalException(CUSTOM_REG_ERROR);
        }
        validateCustomDelimiterBlank(input.substring(startIndex, endIndex));
    }

    private static void validateCustomDelimiterBlank(final String input) {
        if (input.isBlank()) {
            throw new CustomIllegalException(CUSTOM_REG_ERROR);
        }
    }

    private static String checkMetaCharacters(final String splitter) {
        return splitter.replaceAll("([\\^$.?*+()\\[\\]{}|\\\\-])", "\\\\$1");
    }

    private static String getNumbers(final String input) {
        String str = input;
        while (str.startsWith(CUSTOM_START)) {
            int endIndex = str.indexOf(CUSTOM_END) + CUSTOM_END.length();
            str = str.substring(endIndex);
        }
        return str;
    }

}
