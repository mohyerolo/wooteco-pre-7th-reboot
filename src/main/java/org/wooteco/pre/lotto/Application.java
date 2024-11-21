package org.wooteco.pre.lotto;

import org.wooteco.pre.lotto.controller.LottoController;
import org.wooteco.pre.lotto.service.LottoService;
import org.wooteco.pre.lotto.view.InputView;
import org.wooteco.pre.lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController(new InputView(), new OutputView(), new LottoService());
        lottoController.start();
    }
}
