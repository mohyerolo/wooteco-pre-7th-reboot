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

    public List<String> findWinners(final List<Car> cars) {
        int highestMoveCnt = getWinningMoveCnt(cars);
        return cars.stream()
                .filter(car -> car.getMoveCnt() == highestMoveCnt)
                .map(Car::getName)
                .toList();
    }

    private void validateCar(final String[] splitCarNames) {
        CarValidator.validateBlank(splitCarNames);
        CarValidator.validateDuplicate(splitCarNames);
    }

    private int getWinningMoveCnt(final List<Car> cars) {
        return cars.stream()
                .mapToInt(Car::getMoveCnt)
                .max()
                .orElse(0);
    }
}
