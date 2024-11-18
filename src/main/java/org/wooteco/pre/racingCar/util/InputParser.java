package org.wooteco.pre.racingCar.util;

import java.util.Arrays;

public class InputParser {
    private static final String DELIMITER = ",";
    private static final String BLANK_ERROR = "존재하지 않는 자동차 이름이 있습니다.";

    public static String[] splitCarNames(String carNames) {
        String[] splitCarNames = carNames.split(DELIMITER);
        if (validateBlank(splitCarNames)) {
            throw new IllegalArgumentException(BLANK_ERROR);
        }
         return splitCarNames;
    }

    private static boolean validateBlank(String[] splitCarNames) {
        return Arrays.stream(splitCarNames)
                .anyMatch(String::isBlank);
    }
}
