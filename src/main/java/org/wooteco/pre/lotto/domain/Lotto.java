package org.wooteco.pre.lotto.domain;

import org.wooteco.pre.lotto.service.LottoGenerator;

import java.util.List;

public class Lotto {
    private static final int min = 1;
    private static final int max = 45;
    private static final int count = 6;

    private final List<Integer> numbers;

    private Lotto(final List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static Lotto from(final LottoGenerator lottoGenerator) {
        return new Lotto(lottoGenerator.generateLottoNumbers(min, max, count));
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
