package org.wooteco.pre.lotto.view;

import camp.nextstep.edu.missionutils.Console;
import org.wooteco.pre.lotto.validator.ViewValidator;

public class InputView {
    public int readPurchasedAmount() {
        System.out.println(DisplayMessage.INPUT_MONEY.getMessage());
        String amount = Console.readLine();
        ViewValidator.validateBlank(amount);
        ViewValidator.validateNum(amount);
        return Integer.parseInt(amount);
    }

    public String readWinLottoNumbers() {
        System.out.printf("%n" + (DisplayMessage.INPUT_WIN_NUMBERS.getMessage()) + "%n");
        String numbers = Console.readLine();
        ViewValidator.validateBlank(numbers);
        return numbers;
    }

    public int readBonusNumber() {
        System.out.printf("%n" + (DisplayMessage.INPUT_BONUS_NUMBERS.getMessage()) + "%n");
        String bonus = Console.readLine();
        ViewValidator.validateBlank(bonus);
        ViewValidator.validateNum(bonus);
        return Integer.parseInt(bonus);
    }
}
