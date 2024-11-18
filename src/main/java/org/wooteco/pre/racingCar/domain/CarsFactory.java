package org.wooteco.pre.racingCar.domain;

import org.wooteco.pre.racingCar.util.InputParser;
import org.wooteco.pre.racingCar.validator.CarValidator;

import java.util.Arrays;
import java.util.List;

public class CarsFactory {
    public static List<Car> createCars(final String carNames) {
        String[] splitCarNames = InputParser.splitCarNames(carNames);
        validateCar(splitCarNames);
        return createEachCar(splitCarNames);
    }

    private static void validateCar(final String[] splitCarNames) {
        CarValidator.validateBlank(splitCarNames);
        CarValidator.validateDuplicate(splitCarNames);
    }

    private static List<Car> createEachCar(final String[] splitCarNames) {
        return Arrays.stream(splitCarNames)
                .map(Car::new)
                .toList();
    }
}
