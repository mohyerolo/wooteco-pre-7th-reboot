package org.wooteco.pre.racingCar.view;

import org.wooteco.pre.racingCar.dto.CarDto;

import java.util.List;

public class OutputView {
    private static final String OUTPUT_EXECUTE_RESULT = "실행 결과";
    private static final String TEMPLATE = "최종 우승자 : %s";
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

    public void printExecuteMessage() {
        System.out.printf("%n" + OUTPUT_EXECUTE_RESULT);
    }

    public void printWinners(final List<String> winners) {
        System.out.printf((TEMPLATE) + "%n", String.join(WINNER_DELIMITER, winners));
    }

}
