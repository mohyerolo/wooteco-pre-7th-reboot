package org.wooteco.pre.racingCar.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String BLANK_ERROR = "입력값은 비어있을 수 없습니다.";

    public String read() {
        String input = Console.readLine();
        validateBlank(input);
        return input;
    }

    private void validateBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(BLANK_ERROR);
        }
    }
}
