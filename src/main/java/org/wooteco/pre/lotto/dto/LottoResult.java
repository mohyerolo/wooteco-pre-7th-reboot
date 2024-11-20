package org.wooteco.pre.lotto.dto;

import org.wooteco.pre.lotto.constants.Match;

import java.util.Map;

public class LottoResult {
    private final Map<Match, Integer> results;
    private final double returnRate;

    public LottoResult(final Map<Match, Integer> results, final double returnRate) {
        this.results = results;
        this.returnRate = returnRate;
    }

    public Map<Match, Integer> getResults() {
        return results;
    }

    public double getReturnRate() {
        return returnRate;
    }
}
