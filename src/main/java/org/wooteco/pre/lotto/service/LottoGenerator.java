package org.wooteco.pre.lotto.service;

import java.util.List;

public interface LottoGenerator {
    List<Integer> generateLottoNumbers(final int min, final int max, final int count);
}
