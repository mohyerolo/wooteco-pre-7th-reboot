package org.wooteco.pre.lotto.service;

import java.util.List;

public interface LottoGenerator {
    default List<Integer> generateLottoNumbers(final int min, final int max, final int count) {
        throw new UnsupportedOperationException("이 메서드는 지원되지 않습니다.");
    }

    default List<Integer> generateLottoNumbers(final String numbers, final int min, final int max, final int count) {
        throw new UnsupportedOperationException("이 메서드는 지원되지 않습니다.");
    }
}
