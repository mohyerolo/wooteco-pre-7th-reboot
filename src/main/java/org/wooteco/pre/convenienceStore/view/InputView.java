package org.wooteco.pre.convenienceStore.view;

import camp.nextstep.edu.missionutils.Console;
import org.wooteco.pre.convenienceStore.dto.UpdateDto;
import org.wooteco.pre.convenienceStore.validator.InputValidator;

public class InputView {
    private static final String INPUT_ORDER = "구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])";
    private static final String INPUT_FREE = "현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)";
    private static final String INPUT_NO_PROMOTION = "현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)";
    private static final InputView INPUT_VIEW = new InputView();

    private InputView() {}

    public static InputView getInstance() {
        return INPUT_VIEW;
    }

    public String readOrder() {
        System.out.println(INPUT_ORDER);
        return readInput();
    }

    public String readFreeTake(final UpdateDto dto) {
        System.out.printf(String.format(INPUT_FREE, dto.getProductName(), dto.getQuantity()) + "%n");
        return readInput();
    }

    public String readNoPromotion(final UpdateDto dto) {
        System.out.printf(String.format(INPUT_NO_PROMOTION, dto.getProductName(), dto.getQuantity()) + "%n");
        return readInput();
    }

    private String readInput() {
        String input = Console.readLine();
        InputValidator.validateString(input);
        return input;
    }
}
