package org.wooteco.pre.convenienceStore.domain.promotion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class PromotionTest {
    private Promotion availablePromotion;

    @BeforeEach
    void setup() {
        String startDate = "2024-11-19";
        String endDate = String.valueOf(LocalDate.now());
        String[] data = new String[]{"탄산2+1", "2", "1", startDate, endDate};
        availablePromotion = Promotion.from(data);
    }

    @Test
    void 날짜_지나서_불가능() {
        String unableStartDate = "2024-10-03";
        String unableEndDate = "2024-11-03";
        String[] data = new String[]{"탄산2+1", "2", "1", unableStartDate, unableEndDate};
        Promotion endedPromotion = Promotion.from(data);
        Assertions.assertFalse(endedPromotion.isAvailable());
    }

    @Test
    void 날짜_안지나서_가능() {
        Assertions.assertTrue(availablePromotion.isAvailable());
    }

    @Test
    void 같은_프로모션() {
        Assertions.assertTrue(availablePromotion.isSamePromotion("탄산2+1"));
    }

    @Test
    void 다른_프로모션() {
        Assertions.assertFalse(availablePromotion.isSamePromotion("할인"));
    }
}
