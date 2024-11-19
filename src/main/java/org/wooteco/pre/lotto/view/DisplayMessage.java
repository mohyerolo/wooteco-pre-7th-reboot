package org.wooteco.pre.lotto.view;

public enum DisplayMessage {
    INPUT_MONEY("구입금액을 입력해 주세요"),
    INPUT_WIN_NUMBERS("당첨 번호를 입력해 주세요."),
    INPUT_BONUS_NUMBERS("보너스 번호를 입력해 주세요"),

    OUTPUT_LOTTO_TICKETS_NUM("%d개를 구매했습니다."),
    OUTPUT_WINNING_STATISTICS("당첨 통계"),
    OUTPUT_DIVIDER("---"),
    OUTPUT_TOTAL_RETURN("총 수익률은 %.f입니다");

    private final String message;

    DisplayMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
