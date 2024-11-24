package org.wooteco.pre.convenienceStore.domain.promotion;

import camp.nextstep.edu.missionutils.DateTimes;
import org.wooteco.pre.convenienceStore.util.InputParser;

import java.time.LocalDate;

public class Promotion {
    private final String name;
    private final int buy;
    private final int free;
    private final LocalDate startDate;
    private final LocalDate endDate;

    Promotion(final String name, final int buy, final int free, final LocalDate startDate, final LocalDate endDate) {
        this.name = name;
        this.buy = buy;
        this.free = free;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Promotion from(final String[] promotionData) {
        String name = promotionData[0];
        int buy = InputParser.parseStringToInt(promotionData[1]);
        int free = InputParser.parseStringToInt(promotionData[2]);
        LocalDate start = InputParser.parseStringToDate(promotionData[3]);
        LocalDate end = InputParser.parseStringToDate(promotionData[4]);
        return new Promotion(name, buy, free, start, end);
    }

    public boolean isAvailable() {
        LocalDate now = DateTimes.now().toLocalDate();
        return (now.isAfter(startDate) || now.isEqual(startDate)) && (now.isBefore(endDate) || now.isEqual(endDate));
    }

    public boolean isSamePromotion(final String promotionName) {
        return name.equals(promotionName);
    }

    public String getName() {
        return name;
    }
}
