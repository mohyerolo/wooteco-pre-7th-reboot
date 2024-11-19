package org.wooteco.pre.lotto.controller;

import org.wooteco.pre.lotto.domain.LottoTickets;
import org.wooteco.pre.lotto.dto.LottoDto;
import org.wooteco.pre.lotto.service.LottoGenerator;
import org.wooteco.pre.lotto.view.InputView;
import org.wooteco.pre.lotto.view.OutputView;

import java.util.List;
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
        outputView.printLottoTickets(makeDto(lottoTickets));
    }

    private LottoTickets issueLottoTickets() {
        return executeWithRetry(() -> {
            int purchasedAmount = inputView.readPurchasedAmount();
            return LottoTickets.from(purchasedAmount, lottoGenerator);
        });
    }

    private List<LottoDto> makeDto(LottoTickets lottoTickets) {
        return lottoTickets.getLottos().stream()
                .map(LottoDto::new)
                .toList();
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
