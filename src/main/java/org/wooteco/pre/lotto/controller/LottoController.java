package org.wooteco.pre.lotto.controller;

import org.wooteco.pre.lotto.domain.Lotto;
import org.wooteco.pre.lotto.domain.LottoTickets;
import org.wooteco.pre.lotto.dto.LottoDto;
import org.wooteco.pre.lotto.service.RandomLottoGenerator;
import org.wooteco.pre.lotto.service.StringLottoGenerator;
import org.wooteco.pre.lotto.view.InputView;
import org.wooteco.pre.lotto.view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        LottoTickets lottoTickets = issueLottoTickets();
        outputView.printLottoTickets(makeDto(lottoTickets));
        Lotto winningLotto = getWinningLotto();
    }

    private LottoTickets issueLottoTickets() {
        return executeWithRetry(() -> {
            int purchasedAmount = inputView.readPurchasedAmount();
            return LottoTickets.from(purchasedAmount, new RandomLottoGenerator());
        });
    }

    private List<LottoDto> makeDto(LottoTickets lottoTickets) {
        return lottoTickets.getLottos().stream()
                .map(LottoDto::new)
                .toList();
    }

    private Lotto getWinningLotto() {
        return executeWithRetry(() -> {
            String numbers = inputView.readWinLottoNumbers();
            return Lotto.of(new StringLottoGenerator(), numbers);
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
