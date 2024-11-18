package org.wooteco.pre.racingCar.service;

import org.wooteco.pre.racingCar.domain.Car;
import org.wooteco.pre.racingCar.domain.Cars;

import java.util.List;

public class GameService {
    private final MoveGenerator moveGenerator;

    public GameService(final MoveGenerator moveGenerator) {
        this.moveGenerator = moveGenerator;
    }

    public void playGame(Cars cars) {
        cars.playGame(moveGenerator);
    }

    public List<String> findWinners(Cars cars) {
        return cars.findWinners()
                .stream()
                .map(Car::getName)
                .toList();
    }

}
