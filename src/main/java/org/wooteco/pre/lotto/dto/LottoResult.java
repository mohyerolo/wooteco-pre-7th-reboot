package org.wooteco.pre.lotto.dto;

import org.wooteco.pre.lotto.constants.Match;

import java.util.Map;

public record LottoResult(Map<Match, Integer> results, double returnRate) {
}
