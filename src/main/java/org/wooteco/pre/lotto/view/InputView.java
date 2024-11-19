package org.wooteco.pre.lotto.view;

import camp.nextstep.edu.missionutils.Console;
import org.wooteco.pre.lotto.validator.ViewValidator;

public class InputView {
    public int readPurchasedAmount() {
        System.out.println(DisplayMessage.INPUT_MONEY);
        String amount = Console.readLine();
        ViewValidator.validateBlank(amount);
        ViewValidator.validateNumError(amount);
        return Integer.parseInt(amount);
    }

}
