package org.wooteco.pre.lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.wooteco.pre.lotto.service.RandomLottoGenerator;

import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketsFactoryTest {

    @Test
    void 로또_발행_테스트() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    List<Lotto> lottos = LottoTicketsFactory.createLottos(3000, 1000, new RandomLottoGenerator());
                    assertThat(lottos.size()).isEqualTo(3);
                    assertThat(lottos.getLast().getNumbers()).containsAll(List.of(1, 3, 5, 14, 22, 45));
                },
                List.of(7, 11, 30, 40, 42, 43),
                List.of(2, 13, 22, 32, 38, 45),
                List.of(1, 3, 5, 14, 22, 45)
        );
    }
}
