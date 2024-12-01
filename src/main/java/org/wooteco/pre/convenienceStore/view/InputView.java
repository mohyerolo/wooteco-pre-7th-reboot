package org.wooteco.pre.convenienceStore.view;

import camp.nextstep.edu.missionutils.Console;
import org.wooteco.pre.convenienceStore.dto.UpdateDto;
import org.wooteco.pre.convenienceStore.validator.InputValidator;

import static org.wooteco.pre.convenienceStore.constants.InputMessage.*;

public class InputView {
    private static final InputView INPUT_VIEW = new InputView();

    private InputView() {
    }

    public static InputView getInstance() {
        return INPUT_VIEW;
    }

    public String readOrder() {
        System.out.println(INPUT_ORDER.getMessage());
        return readInput();
    }

    public String readFreeTake(final UpdateDto dto) {
        System.out.printf(String.format(INPUT_FREE.getMessage(), dto.getProductName(), dto.getQuantity()) + "%n");
        return readInput();
    }

    public String readNoPromotionOK(final UpdateDto dto) {
        System.out.printf(String.format(INPUT_NO_PROMOTION.getMessage(), dto.getProductName(), dto.getQuantity()) + "%n");
        return readInput();
    }

    public String readRestart() {
        System.out.println(INPUT_RESTART.getMessage());
        return readInput();
    }

    public String readMembership() {
        System.out.println(INPUT_MEMBERSHIP.getMessage());
        return readInput();
    }

    private String readInput() {
        String input = Console.readLine();
        InputValidator.validateString(input);
        return input;
    }
}
