package org.wooteco.pre.lotto.domain;

public class WinningLottos {
    private final Lotto winningLotto;
    private final int bonusNum;

    public WinningLottos(final Lotto winningLotto, int bonusNum) {
        this.winningLotto = winningLotto;
        this.bonusNum = bonusNum;
    }

    public int checkLottosMatchCount(final Lotto purchasedLotto) {
        return winningLotto.getMatchNumCount(purchasedLotto);
    }

    public boolean isBonusNumMatch(final Lotto purchasedLotto) {
        return purchasedLotto.isContainingNum(bonusNum);
    }
}
