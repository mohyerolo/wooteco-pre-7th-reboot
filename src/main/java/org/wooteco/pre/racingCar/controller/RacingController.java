package org.wooteco.pre.racingCar.controller;

import org.wooteco.pre.racingCar.domain.Cars;
import org.wooteco.pre.racingCar.dto.CarDto;
import org.wooteco.pre.racingCar.service.GameService;
import org.wooteco.pre.racingCar.util.InputParser;
import org.wooteco.pre.racingCar.view.InputView;
import org.wooteco.pre.racingCar.view.OutputView;

import java.util.List;

public class RacingController {
    private final InputView inputView;
    private final OutputView outputView;
    private final GameService gameService;

    public RacingController(final InputView inputView, final OutputView outputView, final GameService gameService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.gameService = gameService;
    }

    public void start() {
        Cars cars = Cars.from(inputView.readCarNames());
        int count = InputParser.parseCount(inputView.readTryCount());
        startGameAsCount(cars, count);
        presentWinner(cars);
    }

    private void startGameAsCount(final Cars cars, final int count) {
        outputView.printExecuteMessage();
        for (int i = 0; i < count; i++) {
            gameService.playGame(cars);
            outputView.printProgress(parseDto(cars));
        }
    }

    private List<CarDto> parseDto(final Cars cars) {
        return cars.getCars().stream()
                .map(CarDto::from)
                .toList();
    }

    private void presentWinner(final Cars cars) {
        List<String> winners = gameService.findWinners(cars);
        outputView.printWinners(winners);
    }
}
