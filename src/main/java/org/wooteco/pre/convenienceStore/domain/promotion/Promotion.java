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

    public boolean isPromotionExists() {
        return true;
    }

    public int calcAddableQuantity(final int quantity) {
        if (calcRemainQuantity(quantity) == buy) {
            return free;
        }
        return 0;
    }

    public int calcPromotionStock(final int stock, final int order) {
        return calcPromotionSets(stock, order) * (buy + free);
    }

    public int calcPromotionFree(final int stock, final int order) {
        return calcPromotionSets(stock, order) * free;
    }

    public String getName() {
        return name;
    }

    private int calcRemainQuantity(final int quantity) {
        return quantity % (buy + free);
    }

    private int calcPromotionSets(final int stock, final int order) {
        return Math.min(stock, order) / (buy + free);
    }
}
