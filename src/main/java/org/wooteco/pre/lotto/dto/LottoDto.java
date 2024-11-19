package org.wooteco.pre.lotto.dto;

import org.wooteco.pre.lotto.domain.Lotto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LottoDto {
    private final List<Integer> numbers;

    public LottoDto(final Lotto lotto) {
        this.numbers = new ArrayList<>(lotto.getNumbers());
        numbers.sort(Comparator.naturalOrder());
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
