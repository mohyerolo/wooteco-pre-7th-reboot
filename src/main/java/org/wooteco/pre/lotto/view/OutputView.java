package org.wooteco.pre.lotto.view;

import org.wooteco.pre.lotto.dto.LottoDto;

import java.util.List;

public class OutputView {
    public void printLottoTickets(List<LottoDto> lottos) {
        System.out.printf((DisplayMessage.OUTPUT_LOTTO_TICKETS_NUM.getMessage()) + "%n", lottos.size());
        StringBuilder sb = new StringBuilder();
        lottos.forEach(lotto -> sb.append(lotto.getNumbers()).append('\n'));
        System.out.print(sb);
    }
}
