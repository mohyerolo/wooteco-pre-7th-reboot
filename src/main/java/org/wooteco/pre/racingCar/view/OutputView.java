package org.wooteco.pre.racingCar.view;

import org.wooteco.pre.racingCar.dto.CarDto;

import java.util.List;

public class OutputView {
    private static final String DIVIDE_OBJECT = " : ";
    private static final String WINNER_DELIMITER = ",";

    public void printProgress(final List<CarDto> cars) {
        StringBuilder sb = new StringBuilder();
        for (CarDto car : cars) {
            sb.append(car.carName())
                    .append(DIVIDE_OBJECT)
                    .append(car.movement())
                    .append('\n');
        }
        System.out.println(sb);
    }

    public void printWinners(final List<String> winners) {
        System.out.println(String.join(WINNER_DELIMITER, winners));
    }

}
