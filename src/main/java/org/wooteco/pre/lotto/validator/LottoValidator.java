package org.wooteco.pre.lotto.validator;

import org.wooteco.pre.lotto.domain.Lotto;
import org.wooteco.pre.lotto.exception.CustomIllegalException;

import java.util.Arrays;
import java.util.List;

public class LottoValidator {
    private static final String UNIT_ERROR = "로또 구입금액은 %d원 단위여야 합니다";
    private static final String NUM_REGEX = "^[0-9]*$";
    private static final String NUM_ERROR = "로또 번호는 숫자여야 합니다";
    private static final String RANGE_ERROR_TEMPLATE = "번호는 %d와 %d 사이여야 합니다";
    private static final String SIZE_ERROR = "%d개의 번호가 필요합니다";
    private static final String NUM_DUPLICATED_ERROR = "번호가 중복되면 안됩니다";

    public static void purchasedAmountUnit(final int purchasedAmount, final int purchaseUnit) {
        if (purchasedAmount % purchaseUnit != 0) {
            throw new CustomIllegalException(String.format(UNIT_ERROR, purchaseUnit));
        }
    }

    public static void validateAllNum(final String[] numbers) {
        if (isAnyNotNum(numbers)) {
            throw new CustomIllegalException(NUM_ERROR);
        }
    }

    public static void validateSize(final int size) {
        if (size != Lotto.LOTTO_COUNT) {
            throw new CustomIllegalException(String.format(SIZE_ERROR, Lotto.LOTTO_COUNT));
        }
    }

    public static void validateRange(final List<Integer> numbers) {
        if (isAnyNumRangeUnAble(numbers)) {
            throw new CustomIllegalException(String.format(RANGE_ERROR_TEMPLATE, Lotto.LOTTO_MIN, Lotto.LOTTO_MAX));
        }
    }

    public static void validateNonDuplicate(final List<Integer> numbers) {
        if (numbers.size() != getDistinctNumSize(numbers)) {
            throw new CustomIllegalException(NUM_DUPLICATED_ERROR);
        }
    }

    private static boolean isAnyNotNum(final String[] numbers) {
        return Arrays.stream(numbers)
                .anyMatch(number -> !number.matches(NUM_REGEX));
    }

    private static boolean isAnyNumRangeUnAble(final List<Integer> numbers) {
        return numbers.stream()
                .anyMatch(LottoValidator::isRangeUnable);
    }

    private static boolean isRangeUnable(final int number) {
        return number < Lotto.LOTTO_MIN || number > Lotto.LOTTO_MAX;
    }

    private static int getDistinctNumSize(final List<Integer> numbers) {
        return numbers.stream().distinct().toList().size();
    }
}
