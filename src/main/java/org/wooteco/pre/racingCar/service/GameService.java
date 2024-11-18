package org.wooteco.pre.racingCar.service;

import org.wooteco.pre.racingCar.domain.Car;
import org.wooteco.pre.racingCar.util.InputParser;
import org.wooteco.pre.racingCar.validator.CarValidator;

import java.util.Arrays;
import java.util.List;

public class GameService {
    private static final int MIN_NUM = 0;
    private static final int MAX_NUM = 9;

    private final MoveGenerator moveGenerator;

    public GameService(final MoveGenerator moveGenerator) {
        this.moveGenerator = moveGenerator;
    }

    public List<Car> makeCars(final String carNames) {
        String[] splitCarNames = InputParser.splitCarNames(carNames);
        validateCar(splitCarNames);
        return Arrays.stream(splitCarNames)
                .map(Car::new)
                .toList();
    }

    public void playGame(final List<Car> cars) {
        for (Car car : cars) {
            int randomNum = moveGenerator.generateNumber(MIN_NUM, MAX_NUM);
            car.moveOrNot(randomNum);
        }
    }

    private void validateCar(final String[] splitCarNames) {
        CarValidator.validateBlank(splitCarNames);
        CarValidator.validateDuplicate(splitCarNames);
    }
}
