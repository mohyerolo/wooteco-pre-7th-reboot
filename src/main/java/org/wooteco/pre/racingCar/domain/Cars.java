package org.wooteco.pre.racingCar.domain;

import org.wooteco.pre.racingCar.service.MoveGenerator;

import java.util.List;

public class Cars {
    private static final int MIN_NUM = 0;
    private static final int MAX_NUM = 9;

    private final List<Car> cars;

    private Cars(final List<Car> cars) {
        this.cars = cars;
    }

    public static Cars from(final String carNames) {
        return new Cars(CarsFactory.createCars(carNames));
    }

    public void playGame(final MoveGenerator moveGenerator) {
        for (Car car : cars) {
            int randomNum = moveGenerator.generateNumber(MIN_NUM, MAX_NUM);
            car.moveOrNot(randomNum);
        }
    }

    public List<Car> findWinners() {
        int highestMoveCnt = getWinningMoveCnt();
        return cars.stream()
                .filter(car -> car.isWinner(highestMoveCnt))
                .toList();
    }

    public List<Car> getCars() {
        return cars;
    }

    private int getWinningMoveCnt() {
        return cars.stream()
                .mapToInt(Car::getMoveCnt)
                .max()
                .orElse(0);
    }
}
