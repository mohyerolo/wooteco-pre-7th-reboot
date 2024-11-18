package org.wooteco.pre.racingCar.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String INPUT_CAR_NAME = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String INPUT_TRY_COUNT = "시도할 횟수는 몇 회인가요?";
    private static final String BLANK_ERROR = "입력값은 비어있을 수 없습니다.";

    public String readCarNames() {
        System.out.println(INPUT_CAR_NAME);
        return readInput();
    }

    public String readTryCount() {
        System.out.println(INPUT_TRY_COUNT);
        return readInput();
    }

    private String readInput() {
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
