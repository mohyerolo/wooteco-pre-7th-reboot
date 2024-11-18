package org.wooteco.pre.racingCar.controller;

import org.wooteco.pre.racingCar.domain.Car;
import org.wooteco.pre.racingCar.service.GameService;
import org.wooteco.pre.racingCar.service.MoveGenerator;
import org.wooteco.pre.racingCar.util.InputParser;
import org.wooteco.pre.racingCar.view.InputView;
import org.wooteco.pre.racingCar.view.OutputView;

import java.util.List;

public class RacingController {
    private final OutputView outputView;
    private final GameService gameService;
    private final MoveGenerator moveGenerator;

    public RacingController(final OutputView outputView, final GameService gameService, final MoveGenerator moveGenerator) {
        this.outputView = outputView;
        this.gameService = gameService;
        this.moveGenerator = moveGenerator;
    }

    public void start() {
        List<Car> cars = gameService.makeCars(InputView.read());
        int count = InputParser.parseCount(InputView.read());
        startGameAsCount(cars, count);
    }

    private void startGameAsCount(final List<Car> cars, final int count) {
        for (int i = 0; i < count; i++) {
            gameService.playGame(cars, moveGenerator);
        }
    }
}
