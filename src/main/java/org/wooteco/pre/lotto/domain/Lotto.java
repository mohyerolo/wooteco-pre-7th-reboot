package org.wooteco.pre.lotto.domain;

import org.wooteco.pre.lotto.service.LottoGenerator;

import java.util.List;

public class Lotto {
    private static final int LOTTO_MIN = 1;
    private static final int LOTTO_MAX = 45;
    private static final int LOTTO_COUNT = 6;

    private final List<Integer> numbers;

    private Lotto(final List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static Lotto generatorFrom(final LottoGenerator lottoGenerator) {
        return new Lotto(LottoFactory.createLotto(lottoGenerator, LOTTO_MIN, LOTTO_MAX, LOTTO_COUNT));
    }

    public static Lotto numbersFrom(final String numbers) {
        return new Lotto(LottoFactory.createLotto(numbers, LOTTO_MIN, LOTTO_MAX, LOTTO_COUNT));
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
