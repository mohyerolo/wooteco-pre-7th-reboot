package org.wooteco.pre.lotto.service;

import org.junit.jupiter.api.Test;
import org.wooteco.pre.lotto.constants.Match;
import org.wooteco.pre.lotto.domain.Lotto;
import org.wooteco.pre.lotto.domain.LottoTickets;
import org.wooteco.pre.lotto.domain.WinningLottos;
import org.wooteco.pre.lotto.dto.LottoResult;

import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static org.assertj.core.api.Assertions.assertThat;

class LottoServiceTest {
    private final LottoService lottoService = new LottoService();

    @Test
    void 결과_계산() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    LottoTickets lottoTickets = LottoTickets.from(3000, new RandomLottoGenerator());
                    WinningLottos winningLottos = new WinningLottos(Lotto.of(new StringLottoGenerator(), "2,3,5,6,1,40"), 4);
                    LottoResult lottoResult = lottoService.checkLottoResult(lottoTickets, winningLottos);
                    assertThat(lottoResult.getResults().get(Match.FIVE_BONUS)).isEqualTo(1);
                    assertThat(lottoResult.getResults().get(Match.THREE)).isEqualTo(1);
                    assertThat(lottoResult.getResults().get(Match.FOUR)).isEqualTo(0);
                },
                List.of(1, 2, 3, 4, 5, 6),
                List.of(3, 4, 5, 6, 7, 8),
                List.of(6, 7, 8, 9, 10, 11)
        );
    }
}
