package org.wooteco.pre.racingCar.service;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomNumGenerator implements MoveGenerator {
    @Override
    public int generateNumber(final int min, final int max) {
        return Randoms.pickNumberInRange(min, max);
    }
}
