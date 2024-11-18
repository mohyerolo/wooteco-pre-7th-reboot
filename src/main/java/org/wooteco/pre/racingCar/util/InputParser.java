package org.wooteco.pre.racingCar.util;

public class InputParser {
    private static final String DELIMITER = ",";

    public static String[] splitCarNames(String carNames) {
        return carNames.split(DELIMITER);
    }

}
