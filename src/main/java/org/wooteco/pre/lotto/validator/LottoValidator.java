package org.wooteco.pre.lotto.validator;

import org.wooteco.pre.lotto.exception.CustomIllegalException;

public class LottoValidator {
    private static final String UNIT_ERROR = "로또 구입금액은 %d원 단위여야 합니다.";

    public static void purchasedAmountUnit(final int purchasedAmount, final int purchaseUnit) {
        if (purchasedAmount % purchaseUnit != 0) {
            throw new CustomIllegalException(String.format(UNIT_ERROR, purchaseUnit));
        }
    }
}
