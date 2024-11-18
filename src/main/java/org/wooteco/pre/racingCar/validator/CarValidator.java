package org.wooteco.pre.racingCar.validator;

import java.util.Arrays;

public class CarValidator {
    private static final String BLANK_ERROR = "존재하지 않는 자동차 이름이 있습니다.";
    private static final String DUPLICATED_ERROR = "자동차 이름에 중복이 존재합니다.";

    public static void validateBlank(final String[] splitCarNames) {
        if (isAnyMatchBlank(splitCarNames)) {
            throw new IllegalArgumentException(BLANK_ERROR);
        }
    }

    public static void validateDuplicate(final String[] splitCarNames) {
        int distinctSize = getDistinctSize(splitCarNames);
        if (splitCarNames.length != distinctSize) {
            throw new IllegalArgumentException(DUPLICATED_ERROR);
        }
    }

    private static boolean isAnyMatchBlank(final String[] splitCarNames) {
        return Arrays.stream(splitCarNames)
                .anyMatch(String::isBlank);
    }

    private static int getDistinctSize(final String[] splitCarNames) {
        return Arrays.stream(splitCarNames).distinct().toList().size();
    }
}
