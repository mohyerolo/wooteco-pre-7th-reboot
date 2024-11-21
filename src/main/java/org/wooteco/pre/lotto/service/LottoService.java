package org.wooteco.pre.lotto.service;

import org.wooteco.pre.lotto.constants.Match;
import org.wooteco.pre.lotto.domain.LottoTickets;
import org.wooteco.pre.lotto.domain.WinningLottos;
import org.wooteco.pre.lotto.dto.LottoResult;

import java.util.Map;

public class LottoService {

    public LottoResult checkLottoResult(final LottoTickets lottoTickets, final WinningLottos winningLottos) {
        Map<Match, Integer> matchCountData = Match.initilizeMatchMap();
        lottoTickets.compareWinningLotto(matchCountData, winningLottos, Match.getMinMatchCount());
        double returnRate = calcReturnRate(matchCountData, lottoTickets);
        return new LottoResult(matchCountData, returnRate);
    }

    private double calcReturnRate(final Map<Match, Integer> matchCountData, final LottoTickets lottoTickets) {
        int totalPrize = sumTotalPrize(matchCountData);
        return lottoTickets.calcReturnRate(totalPrize);
    }

    private int sumTotalPrize(final Map<Match, Integer> matchCountData) {
        return matchCountData.entrySet().stream()
                .mapToInt(entry ->
                        entry.getKey().getPrize() * entry.getValue())
                .sum();
    }

}
