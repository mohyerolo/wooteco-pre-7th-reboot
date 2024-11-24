package org.wooteco.pre.convenienceStore.domain.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wooteco.pre.convenienceStore.domain.promotion.NoPromotion;
import org.wooteco.pre.convenienceStore.domain.promotion.Promotion;

import java.time.LocalDate;

class ProductTest {

    private String[] data;
    private Promotion promotion;

    @BeforeEach
    void setup() {
        data = new String[]{"콜라","1000","10","탄산2+1"};
        String[] data = new String[]{"탄산2+1", "2", "1", "2024-11-19", String.valueOf(LocalDate.now())};
        promotion = Promotion.from(data);
    }

    @Test
    void 상품_생성_성공() {
        Assertions.assertThatNoException()
                .isThrownBy(() -> Product.of(data, promotion));
    }

    @Test
    void 프로모션_없는_상품_생성_성공() {
        String[] noPromotionData = {"콜라", "1000", "10", "null"};
        Assertions.assertThatNoException()
                .isThrownBy(() -> Product.of(noPromotionData, new NoPromotion()));
    }
}
