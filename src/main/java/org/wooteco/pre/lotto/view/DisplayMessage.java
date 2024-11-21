package org.wooteco.pre.lotto.view;

public enum DisplayMessage {
    INPUT_MONEY("구입금액을 입력해 주세요"),
    INPUT_WIN_NUMBERS("당첨 번호를 입력해 주세요."),
    INPUT_BONUS_NUMBERS("보너스 번호를 입력해 주세요"),

    OUTPUT_LOTTO_TICKETS_NUM("%d개를 구매했습니다."),
    OUTPUT_WINNING_STATISTICS("당첨 통계"),
    OUTPUT_DIVIDER("---"),
    MATCH_COUNT("%d개 일치"),
    MATCH_BONUS("%d개 일치, 보너스 볼 일치"),
    MATCH_RESULT(" (%,d원) - %d개"),
    OUTPUT_TOTAL_RETURN("총 수익률은 %.1f%%입니다.");

    private final String message;

    DisplayMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
