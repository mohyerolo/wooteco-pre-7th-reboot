package org.wooteco.pre.calculator.domain;

import org.wooteco.pre.calculator.exception.CustomIllegalException;

import java.util.Arrays;

public class Numbers {
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
            throw new CustomIllegalException("숫자가 아닌 문자가 들어있습니다.");
        }
    }

    private void checkPositive(int num) {
        if (num <= 0) {
            throw new CustomIllegalException("양수가 아닌 숫자가 있습니다.");
        }
    }
}
