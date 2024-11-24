package org.wooteco.pre.convenienceStore.domain.promotion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wooteco.pre.convenienceStore.util.FileReaderUtil;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PromotionFactoryTest {
    private static final String promotionPath = "src/main/resources/promotions.md";
    private static final int PROMOTION_FILE_LENGTH = 3;
    private List<String> promotions;

    @BeforeEach
    void setup() {
        promotions = FileReaderUtil.readFile(promotionPath);
        promotions.removeFirst();
    }

    @Test
    void 파일_행만큼_생성_성공() {
        assertThat(PromotionFactory.createPromotions(promotions).size()).isEqualTo(PROMOTION_FILE_LENGTH);
    }

    @Test
    void 프로모션_이름_제대로_생성() {
        assertThat(PromotionFactory.createPromotions(promotions).getFirst().isSamePromotion("탄산2+1")).isTrue();
    }
}
