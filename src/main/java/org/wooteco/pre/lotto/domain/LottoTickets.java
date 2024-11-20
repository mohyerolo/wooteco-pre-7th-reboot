package org.wooteco.pre.lotto.domain;

import org.wooteco.pre.lotto.service.LottoGenerator;

import java.util.List;

public class LottoTickets {
    private static final int purchaseUnit = 1000;

    private final List<Lotto> lottos;

    private LottoTickets(final List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static LottoTickets from(final int purchasedAmount, final LottoGenerator lottoGenerator) {
        List<Lotto> lottos = LottoTicketsFactory.createLottos(purchasedAmount, purchaseUnit, lottoGenerator);
        return new LottoTickets(lottos);
    }

    public double calcReturnRate(final int totalPrize) {
        return (double) totalPrize / (lottos.size() * purchaseUnit) * 100;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
