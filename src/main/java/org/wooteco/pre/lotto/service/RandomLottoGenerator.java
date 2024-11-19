package org.wooteco.pre.lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import org.wooteco.pre.lotto.domain.Lotto;

import java.util.List;

public class RandomLottoGenerator implements LottoGenerator {
    @Override
    public List<Integer> generateLottoNumbers() {
        return Randoms.pickUniqueNumbersInRange(Lotto.LOTTO_MIN, Lotto.LOTTO_MAX, Lotto.LOTTO_COUNT);
    }
}
