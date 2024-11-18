package org.wooteco.pre.racingCar.util;

public class InputParser {
    private static final String DELIMITER = ",";
    private static final String NUMBER_ERROR = "시도 횟수는 숫자여야합니다.";
    public static String[] splitCarNames(String carNames) {
        return carNames.split(DELIMITER);
    }

    public static int parseCount(String count) {
        try {
            return Integer.parseInt(count);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NUMBER_ERROR);
        }
    }
}
