package org.wooteco.pre.racingCar.controller;

import org.wooteco.pre.racingCar.domain.Car;
import org.wooteco.pre.racingCar.service.GameService;
import org.wooteco.pre.racingCar.view.InputView;
import org.wooteco.pre.racingCar.view.OutputView;

import java.util.List;

public class RacingController {
    private final OutputView outputView;
    private final GameService gameService;

    public RacingController(final OutputView outputView, final GameService gameService) {
        this.outputView = outputView;
        this.gameService = gameService;
    }

    public void start() {
        String carNames = InputView.read();
        List<Car> cars = gameService.makeCars(carNames);

    }
}
