package org.wooteco.pre.convenienceStore.view;

import camp.nextstep.edu.missionutils.Console;
import org.wooteco.pre.convenienceStore.validator.InputValidator;

public class InputView {
    private static final String INPUT_ORDER = "구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])";

    private static final InputView INPUT_VIEW = new InputView();

    private InputView() {}

    public static InputView getInstance() {
        return INPUT_VIEW;
    }

    public String readOrder() {
        System.out.println(INPUT_ORDER);
        String input = Console.readLine();
        InputValidator.validateString(input);
        return input;
    }
}
