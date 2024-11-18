package org.wooteco.pre.racingCar.dto;

import org.wooteco.pre.racingCar.domain.Car;

public record CarDto(String carName, String movement) {
    private static final String MOVEMENT_EXPRESSION = "-";
    private static final String NO_MOVE_EXPRESSION = "";

    public static CarDto from(final Car car) {
        return new CarDto(car.getName(), parseMove(car.getMoveCnt()));
    }

    private static String parseMove(final int moveCnt) {
        if (moveCnt > 0) {
            return MOVEMENT_EXPRESSION.repeat(moveCnt);
        }
        return NO_MOVE_EXPRESSION;
    }
}
