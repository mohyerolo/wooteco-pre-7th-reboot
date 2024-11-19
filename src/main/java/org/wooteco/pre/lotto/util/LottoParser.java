package org.wooteco.pre.lotto.util;

import java.util.Arrays;
import java.util.List;

public class LottoParser {
    private static final String DELIMITER = ",";

    public static String[] splitLottoNumbers(final String numbers) {
        return numbers.split(DELIMITER);
    }

    public static List<Integer> parseNumbers(final String[] numbers) {
        return Arrays.stream(numbers).map(Integer::parseInt).toList();
    }
}
