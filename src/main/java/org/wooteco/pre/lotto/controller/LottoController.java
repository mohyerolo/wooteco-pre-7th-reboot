package org.wooteco.pre.lotto.controller;

import org.wooteco.pre.lotto.domain.Lotto;
import org.wooteco.pre.lotto.domain.LottoTickets;
import org.wooteco.pre.lotto.domain.WinningLottos;
import org.wooteco.pre.lotto.dto.LottoDto;
import org.wooteco.pre.lotto.dto.LottoResult;
import org.wooteco.pre.lotto.service.LottoService;
import org.wooteco.pre.lotto.service.RandomLottoGenerator;
import org.wooteco.pre.lotto.service.StringLottoGenerator;
import org.wooteco.pre.lotto.validator.LottoValidator;
import org.wooteco.pre.lotto.view.InputView;
import org.wooteco.pre.lotto.view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(final InputView inputView, final OutputView outputView, final LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void start() {
        LottoTickets lottoTickets = issueLottoTickets();
        outputView.printLottoTickets(makeDto(lottoTickets));

        WinningLottos winningLottos = receiveWinningLottos();
        LottoResult lottoResult = lottoService.checkLottoResult(lottoTickets, winningLottos);
        outputView.printLottoResult(lottoResult);
    }

    private LottoTickets issueLottoTickets() {
        return executeWithRetry(() -> {
            int purchasedAmount = inputView.readPurchasedAmount();
            return LottoTickets.from(purchasedAmount, new RandomLottoGenerator());
        });
    }

    private List<LottoDto> makeDto(LottoTickets lottoTickets) {
        return lottoTickets.getPurchasedLottos().stream()
                .map(LottoDto::new)
                .toList();
    }

    private WinningLottos receiveWinningLottos() {
        Lotto winningLotto = getWinningLotto();
        int bonusNum = getBonusNum(winningLotto);
        return new WinningLottos(winningLotto, bonusNum);
    }

    private Lotto getWinningLotto() {
        return executeWithRetry(() -> {
            String numbers = inputView.readWinLottoNumbers();
            return Lotto.of(new StringLottoGenerator(), numbers);
        });
    }

    private int getBonusNum(final Lotto winningLotto) {
        return executeWithRetry(() -> {
            int bonusNumber = inputView.readBonusNumber();
            LottoValidator.validateRange(bonusNumber);
            LottoValidator.validateNotContainNum(winningLotto, bonusNumber);
            return bonusNumber;
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
