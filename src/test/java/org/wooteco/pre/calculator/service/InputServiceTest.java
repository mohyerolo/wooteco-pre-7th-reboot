package org.wooteco.pre.calculator.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.wooteco.pre.calculator.domain.Numbers;

class InputServiceTest {

    private final InputService inputService = new InputService();

    @Test
    void 기본문자_구분성공() {
        Assertions.assertThat(inputService.createInput("1,2,3"))
                .isInstanceOf(Numbers.class);
    }

    @Test
    void 커스텀문자_구분성공2() {
        Assertions.assertThat(inputService.createInput("//;\\n1,2;3:4"))
                .extracting("numbers")
                .isEqualTo(new String[]{"1","2","3","4"});
    }
}
