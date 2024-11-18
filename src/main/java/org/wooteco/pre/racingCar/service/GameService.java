package org.wooteco.pre.racingCar.service;

import org.wooteco.pre.racingCar.domain.Car;
import org.wooteco.pre.racingCar.util.InputParser;
import org.wooteco.pre.racingCar.validator.CarValidator;

import java.util.Arrays;
import java.util.List;

public class GameService {

    public List<Car> makeCars(final String carNames) {
        String[] splitCarNames = InputParser.splitCarNames(carNames);
        validateCar(splitCarNames);
        return Arrays.stream(splitCarNames)
                .map(Car::new)
                .toList();
    }

    private void validateCar(final String[] splitCarNames) {
        CarValidator.validateBlank(splitCarNames);
        CarValidator.validateDuplicate(splitCarNames);
    }

}
