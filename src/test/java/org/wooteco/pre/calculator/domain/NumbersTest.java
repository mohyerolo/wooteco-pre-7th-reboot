package org.wooteco.pre.calculator.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class NumbersTest {

    @Test
    void 합계_구하기() {
        Numbers numbers = new Numbers(new String[]{"1", "2", "3"});
        Assertions.assertThat(numbers.sum()).isEqualTo(6);
    }

    @Test
    void 중간에_문자있어서_실패() {
        Numbers numbers = new Numbers(new String[]{"1", "2a", "3"});
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(numbers::sum);
    }

    @Test
    void 합계_구하기_빈칸_실패() {
        Numbers numbers = new Numbers(new String[]{"1", "", "3"});
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(numbers::sum);
    }

    @Test
    void 음수_실패() {
        Numbers numbers = new Numbers(new String[]{"1", "-1", "3"});
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(numbers::sum);
    }

    @Test
    void 양수가_아니라_실패() {
        Numbers numbers = new Numbers(new String[]{"1", "0", "3"});
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(numbers::sum);
    }
}
