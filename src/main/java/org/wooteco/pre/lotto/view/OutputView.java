package org.wooteco.pre.lotto.view;

import org.wooteco.pre.lotto.constants.Match;
import org.wooteco.pre.lotto.dto.LottoDto;
import org.wooteco.pre.lotto.dto.LottoResult;

import java.util.List;

public class OutputView {
    public void printLottoTickets(final List<LottoDto> purchasedLottos) {
        System.out.printf("%n" + (DisplayMessage.OUTPUT_LOTTO_TICKETS_NUM.getMessage()) + "%n", purchasedLottos.size());
        StringBuilder sb = new StringBuilder();
        purchasedLottos.forEach(lotto -> sb.append(lotto.getNumbers()).append('\n'));
        System.out.print(sb);
    }

    public void printLottoResult(final LottoResult lottoResult) {
        System.out.printf("%n" + (DisplayMessage.OUTPUT_WINNING_STATISTICS.getMessage()) + "%n");
        System.out.println(DisplayMessage.OUTPUT_DIVIDER.getMessage());
        System.out.print(makeMatchResultSentence(lottoResult));
        System.out.printf((DisplayMessage.OUTPUT_TOTAL_RETURN.getMessage()) + "%n", lottoResult.returnRate());
    }

    private StringBuilder makeMatchResultSentence(final LottoResult lottoResult) {
        StringBuilder sb = new StringBuilder();
        lottoResult.results().forEach((match, value) -> {
            sb.append(makeMatchInfoSentence(match));
            sb.append(makeMatchCountSentence(match, value));
        });
        return sb;
    }

    private String makeMatchInfoSentence(final Match match) {
        if (match.isBonus()) {
            return String.format(DisplayMessage.MATCH_BONUS.getMessage(), match.getMatchCount());
        }
        return String.format(DisplayMessage.MATCH_COUNT.getMessage(), match.getMatchCount());
    }

    private String makeMatchCountSentence(final Match match, final Integer count) {
        return String.format(DisplayMessage.MATCH_RESULT.getMessage() + "%n", match.getPrize(), count);
    }
}
