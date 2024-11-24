package org.wooteco.pre.convenienceStore.domain.promotion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoPromotionTest {

    @Test
    void 프로모션_안됨_확인() {
        assertFalse(new NoPromotion().isAvailable());
    }

}
