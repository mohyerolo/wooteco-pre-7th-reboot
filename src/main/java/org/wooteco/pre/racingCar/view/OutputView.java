package org.wooteco.pre.racingCar.view;

import org.wooteco.pre.racingCar.dto.CarDto;

import java.util.List;

public class OutputView {
    private static final String DIVIDE_OBJECT = " : ";

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

}
