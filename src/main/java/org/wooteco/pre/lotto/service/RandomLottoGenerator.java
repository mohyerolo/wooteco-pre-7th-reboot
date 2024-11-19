package org.wooteco.pre.lotto.service;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class RandomLottoGenerator implements LottoGenerator {
    @Override
    public List<Integer> generateLottoNumbers(int min, int max, int count) {
        return Randoms.pickUniqueNumbersInRange(min, max, count);
    }
}
