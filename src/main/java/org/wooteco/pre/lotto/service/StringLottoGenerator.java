package org.wooteco.pre.lotto.service;

import org.wooteco.pre.lotto.util.LottoParser;
import org.wooteco.pre.lotto.validator.LottoValidator;

import java.util.List;

public class StringLottoGenerator implements LottoGenerator {
    @Override
    public List<Integer> generateLottoNumbers(String numbers, int min, int max, int count) {
        String[] splitNumbers = LottoParser.splitLottoNumbers(numbers);
        LottoValidator.validateAllNum(splitNumbers);

        List<Integer> lottoNumbers = LottoParser.parseNumbers(splitNumbers);
        validateLottoNumbers(lottoNumbers, min, max, count);
        return lottoNumbers;
    }

    private void validateLottoNumbers(final List<Integer> numbers, final int min, final int max, final int count) {
        LottoValidator.validateNonDuplicate(numbers);
        LottoValidator.validateRange(numbers, min, max);
        LottoValidator.validateSize(numbers.size(), count);
    }
}
