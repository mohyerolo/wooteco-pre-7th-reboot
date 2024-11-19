package org.wooteco.pre.lotto.service;

import org.wooteco.pre.lotto.util.LottoParser;
import org.wooteco.pre.lotto.validator.LottoValidator;

import java.util.List;

public class StringLottoGenerator implements LottoGenerator {
    @Override
    public List<Integer> generateLottoNumbers(String numbers) {
        String[] splitNumbers = LottoParser.splitLottoNumbers(numbers);
        LottoValidator.validateAllNum(splitNumbers);

        List<Integer> lottoNumbers = LottoParser.parseNumbers(splitNumbers);
        validateLottoNumbers(lottoNumbers);
        return lottoNumbers;
    }

    private void validateLottoNumbers(final List<Integer> numbers) {
        LottoValidator.validateNonDuplicate(numbers);
        LottoValidator.validateRange(numbers);
        LottoValidator.validateSize(numbers.size());
    }
}
