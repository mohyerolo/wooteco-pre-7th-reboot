package org.wooteco.pre.lotto.constants;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

public enum Match {
    THREE(3, false, 5000),
    FOUR(4, false, 50_000),
    FIVE(5, false, 1_500_000),
    FIVE_BONUS(5, true, 30_000_000),
    SIX(6, false, 2_000_000_000);

    private static final Map<Integer, Match> MATCH_MAP = new LinkedHashMap<>();
    private final int matchCount;
    private final boolean bonus;
    private final int prize;

    static {
        for (Match match : Match.values()) {
            if (!match.bonus) {
                MATCH_MAP.put(match.matchCount, match);
            }
        }
    }

    Match(final int matchCount, final boolean bonus, final int prize) {
        this.matchCount = matchCount;
        this.bonus = bonus;
        this.prize = prize;
    }

    public static Match from(final int matchCount, final boolean bonus) {
        if (matchCount == 5 && bonus) {
            return Match.FIVE_BONUS;
        }
        return MATCH_MAP.get(matchCount);
    }

    public static Map<Match, Integer> initilizeMatchMap() {
        Map<Match, Integer> matchCountData = new EnumMap<>(Match.class);
        Arrays.stream(values()).forEach(match -> matchCountData.put(match, 0));
        return matchCountData;
    }

    public static int getMinMatchCount() {
        return Arrays.stream(Match.values()).mapToInt(Match::getMatchCount).min().orElse(0);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrize() {
        return prize;
    }
}
