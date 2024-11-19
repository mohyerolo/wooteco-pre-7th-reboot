package org.wooteco.pre.lotto.domain;

import org.wooteco.pre.lotto.service.LottoGenerator;
import org.wooteco.pre.lotto.validator.LottoValidator;

import java.util.List;
import java.util.stream.IntStream;

public class LottoTicketsFactory {
    public static List<Lotto> createLottos(final int purchasedAmount, final int purchaseUnit, final LottoGenerator lottoGenerator) {
        LottoValidator.purchasedAmountUnit(purchasedAmount, purchaseUnit);
        return issueLottos(purchasedAmount / purchaseUnit, lottoGenerator);
    }

    private static List<Lotto> issueLottos(final int ticketNum, final LottoGenerator lottoGenerator) {
        return IntStream.range(0, ticketNum)
                .mapToObj(i -> issueLotto(lottoGenerator))
                .toList();
    }

    private static Lotto issueLotto(final LottoGenerator lottoGenerator) {
        return Lotto.generatorFrom(lottoGenerator);
    }
}
