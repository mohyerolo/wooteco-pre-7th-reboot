package org.wooteco.pre.lotto.view;

import camp.nextstep.edu.missionutils.Console;
import org.wooteco.pre.lotto.validator.ViewValidator;

public class InputView {
    public int readPurchasedAmount() {
        System.out.println(DisplayMessage.INPUT_MONEY.getMessage());
        String amount = Console.readLine();
        ViewValidator.validateBlank(amount);
        ViewValidator.validateNumError(amount);
        return Integer.parseInt(amount);
    }

    public String readWinLottoNumbers() {
        System.out.println(DisplayMessage.INPUT_WIN_NUMBERS.getMessage());
        String numbers = Console.readLine();
        ViewValidator.validateBlank(numbers);
        return numbers;
    }
}
