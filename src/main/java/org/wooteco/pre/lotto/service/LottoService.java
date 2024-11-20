package org.wooteco.pre.lotto.service;

import org.wooteco.pre.lotto.constants.Match;
import org.wooteco.pre.lotto.domain.Lotto;
import org.wooteco.pre.lotto.domain.LottoTickets;
import org.wooteco.pre.lotto.domain.WinningLottos;
import org.wooteco.pre.lotto.dto.LottoResult;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoService {

    public LottoResult checkLottoResult(final LottoTickets lottoTickets, final WinningLottos winningLottos) {
        Map<Match, Integer> matchCountData = initializeMatchMap();
        List<Lotto> purchasedLottos = lottoTickets.getLottos();
        int minMatchCount = Match.getMinMatchCount();
        for (Lotto lotto : purchasedLottos) {
            int count = winningLottos.checkLottosMatchCount(lotto);
            boolean containBonus = false;
            if (count == 5) {
                containBonus = winningLottos.isBonusNumMatch(lotto);
            }
            if (count >= minMatchCount) {
                Match match = Match.from(count, containBonus);
                matchCountData.put(match, matchCountData.get(match) + 1);
            }
        }

        int totalPrize = matchCountData.entrySet().stream()
                .mapToInt(entry ->
                        entry.getKey().getPrize() * entry.getValue())
                .sum();
        double returnRate = lottoTickets.calcReturnRate(totalPrize);
        return new LottoResult(matchCountData, returnRate);
    }

    private Map<Match, Integer> initializeMatchMap() {
        Map<Match, Integer> matchCountData = new EnumMap<>(Match.class);
        Arrays.stream(Match.values()).forEach(match -> matchCountData.put(match, 0));
        return matchCountData;
    }

}
