package org.wooteco.pre.racingCar.controller;

import org.wooteco.pre.racingCar.domain.Car;
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
        List<Car> cars = gameService.makeCars(inputView.read());
        int count = InputParser.parseCount(inputView.read());
        startGameAsCount(cars, count);
        presentWinner(cars);
    }

    private void startGameAsCount(final List<Car> cars, final int count) {
        for (int i = 0; i < count; i++) {
            gameService.playGame(cars);
            outputView.printProgress(parseDto(cars));
        }
    }

    private List<CarDto> parseDto(final List<Car> cars) {
        return cars.stream()
                .map(CarDto::from)
                .toList();
    }

    private void presentWinner(final List<Car> cars) {
        List<String> winners = gameService.findWinners(cars);
        outputView.printWinners(winners);
    }
}
