package org.wooteco.pre.lotto.domain;

import org.wooteco.pre.lotto.constants.Match;
import org.wooteco.pre.lotto.service.LottoGenerator;

import java.util.List;
import java.util.Map;

public class LottoTickets {
    private static final int purchaseUnit = 1000;

    private final List<Lotto> purchasedLottos;

    private LottoTickets(final List<Lotto> purchasedLottos) {
        this.purchasedLottos = purchasedLottos;
    }

    public static LottoTickets from(final int purchasedAmount, final LottoGenerator lottoGenerator) {
        List<Lotto> lottos = LottoTicketsFactory.createLottos(purchasedAmount, purchaseUnit, lottoGenerator);
        return new LottoTickets(lottos);
    }

    public void compareWinningLotto(final Map<Match, Integer> matchCountData, final WinningLottos winningLottos, final int minMatchCount) {
        for (Lotto lotto : purchasedLottos) {
            int count = winningLottos.checkLottosMatchCount(lotto);
            boolean containBonus = isContainBonus(count, winningLottos, lotto);
            if (count >= minMatchCount) {
                Match match = Match.from(count, containBonus);
                matchCountData.put(match, matchCountData.get(match) + 1);
            }
        }
    }

    public double calcReturnRate(final int totalPrize) {
        return (double) totalPrize / (purchasedLottos.size() * purchaseUnit) * 100;
    }

    public List<Lotto> getPurchasedLottos() {
        return purchasedLottos;
    }

    private boolean isContainBonus(final int count, final WinningLottos winningLottos, final Lotto lotto) {
        if (count != Match.FIVE_BONUS.getMatchCount()) {
            return false;
        }
        return winningLottos.isBonusNumMatch(lotto);
    }
}
