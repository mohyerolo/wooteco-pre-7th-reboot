package org.wooteco.pre.lotto.controller;

import org.wooteco.pre.lotto.domain.LottoTickets;
import org.wooteco.pre.lotto.service.LottoGenerator;
import org.wooteco.pre.lotto.view.InputView;
import org.wooteco.pre.lotto.view.OutputView;

import java.util.function.Supplier;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoGenerator lottoGenerator;

    public LottoController(final InputView inputView, final OutputView outputView, final LottoGenerator lottoGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoGenerator = lottoGenerator;
    }

    public void start() {
        LottoTickets lottoTickets = issueLottoTickets();

    }

    private LottoTickets issueLottoTickets() {
        return executeWithRetry(() -> {
            int purchasedAmount = inputView.readPurchasedAmount();
            return LottoTickets.from(purchasedAmount, lottoGenerator);
        });
    }

    private <T> T executeWithRetry(Supplier<T> action) {
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
