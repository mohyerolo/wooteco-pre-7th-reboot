package org.wooteco.pre.lotto.domain;

import org.wooteco.pre.lotto.service.LottoGenerator;

import java.util.List;

public class Lotto {
    public static final int LOTTO_MIN = 1;
    public static final int LOTTO_MAX = 45;
    public static final int LOTTO_COUNT = 6;

    private final List<Integer> numbers;

    private Lotto(final List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static Lotto generatorFrom(final LottoGenerator lottoGenerator) {
        return new Lotto(lottoGenerator.generateLottoNumbers());
    }

    public static Lotto of(final LottoGenerator lottoGenerator, String numbers) {
        return new Lotto(lottoGenerator.generateLottoNumbers(numbers));
    }

    public int getMatchNumCount(final Lotto lotto) {
        return (int) numbers.stream().filter(lotto.numbers::contains).count();
    }

    public boolean isContainingNum(final int num) {
        return numbers.contains(num);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
