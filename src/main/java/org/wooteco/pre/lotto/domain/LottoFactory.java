package org.wooteco.pre.lotto.domain;

import org.wooteco.pre.lotto.service.LottoGenerator;
import org.wooteco.pre.lotto.util.LottoParser;
import org.wooteco.pre.lotto.validator.LottoValidator;

import java.util.List;

public class LottoFactory {

    public static List<Integer> createLotto(final LottoGenerator lottoGenerator, final int min, final int max, final int count) {
        List<Integer> numbers = lottoGenerator.generateLottoNumbers(min, max, count);
        validateRangeAndSize(numbers, min, max, count);
        return numbers;
    }

    public static List<Integer> createLotto(final String numbers, final int min, final int max, final int count) {
        String[] splitNumbers = LottoParser.splitLottoNumbers(numbers);
        LottoValidator.validateAllNum(splitNumbers);

        List<Integer> lottoNumbers = LottoParser.parseNumbers(splitNumbers);
        LottoValidator.validateNonDuplicate(lottoNumbers);

        validateRangeAndSize(lottoNumbers, min, max, count);
        return lottoNumbers;
    }

    private static void validateRangeAndSize(final List<Integer> numbers, final int min, final int max, final int count) {
        LottoValidator.validateRange(numbers, min, max);
        LottoValidator.validateSize(numbers.size(), count);
    }
}
