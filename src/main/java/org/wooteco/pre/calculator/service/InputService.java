package org.wooteco.pre.calculator.service;

import org.wooteco.pre.calculator.domain.Numbers;
import org.wooteco.pre.calculator.exception.CustomIllegalException;
import org.wooteco.pre.calculator.util.SplitUtil;

public class InputService {
    private static final String BLANK_ERROR = "입력 값은 비어있으면 안됩니다";

    public Numbers createInput(final String input) {
        validateBlank(input);
        String delimiters = makeDelimiterRegExp(input);
        return new Numbers(makeNumbers(input, delimiters));
    }

    private void validateBlank(final String input) {
        if (input == null || input.isBlank()) {
            throw new CustomIllegalException(BLANK_ERROR);
        }
    }

    private String makeDelimiterRegExp(final String input) {
        return SplitUtil.splitDelimiter(input);
    }

    private String[] makeNumbers(final String input, final String delimiters) {
        return SplitUtil.splitNumbers(input, delimiters);
    }
}
