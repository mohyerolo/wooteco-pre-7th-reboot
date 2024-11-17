package org.wooteco.pre.calculator.domain;

import org.wooteco.pre.calculator.exception.CustomIllegalException;

import java.util.Arrays;

public class Numbers {
    private static final String NON_POSITIVE_ERROR = "양수가 아닌 문자가 있습니다. ";
    private final String[] numbers;

    public Numbers(final String[] numbers) {
        this.numbers = numbers;
    }

    public int sum() {
        return Arrays.stream(numbers)
                .mapToInt(number -> {
                    int num = parseNumber(number);
                    checkPositive(num);
                    return num;
                })
                .sum();
    }

    private int parseNumber(String num) {
        try {
            return Integer.parseInt(num);
        } catch (NumberFormatException e) {
            throw new CustomIllegalException(NON_POSITIVE_ERROR + num);
        }
    }

    private void checkPositive(int num) {
        if (num <= 0) {
            throw new CustomIllegalException(NON_POSITIVE_ERROR + num);
        }
    }
}
