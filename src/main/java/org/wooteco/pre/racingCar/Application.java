package org.wooteco.pre.racingCar;

import org.wooteco.pre.racingCar.controller.RacingController;
import org.wooteco.pre.racingCar.service.GameService;
import org.wooteco.pre.racingCar.service.RandomNumGenerator;
import org.wooteco.pre.racingCar.view.InputView;
import org.wooteco.pre.racingCar.view.OutputView;

public class Application {
    public static void main(String[] args) {
        RacingController racingController = new RacingController(new InputView(), new OutputView(), new GameService(new RandomNumGenerator()));
        racingController.start();
    }
}
