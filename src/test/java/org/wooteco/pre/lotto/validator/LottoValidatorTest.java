package org.wooteco.pre.lotto.validator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class LottoValidatorTest {

    @Test
    void 숫자가_아닌_문자_실패() {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> LottoValidator.validateAllNum(new String[]{"1","2","a","4","5","6"}));
    }

    @Test
    void 전부_숫자_성공() {
        Assertions.assertThatNoException()
                .isThrownBy(() -> LottoValidator.validateAllNum(new String[]{"1","2","3","4","5","6"}));
    }

    @Test
    void 숫자가_N개가_아니어서_실패() {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> LottoValidator.validateSize(5, 6));
    }

    @Test
    void 숫자가_N개_성공() {
        Assertions.assertThatNoException()
                .isThrownBy(() -> LottoValidator.validateSize(6, 6));
    }

    @Test
    void 숫자범위_최소_실패() {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> LottoValidator.validateRange(List.of(1,2,0,3,4,5), 1, 45));
    }

    @Test
    void 숫자범위_최대_실패() {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> LottoValidator.validateRange(List.of(1,2,3,47,4,44), 1, 45));
    }

    @Test
    void 숫자범위_성공() {
        Assertions.assertThatNoException()
                .isThrownBy(() -> LottoValidator.validateRange(List.of(1,2,3,4,45,6), 1, 45));
    }

    @Test
    void 중복_실패() {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> LottoValidator.validateNonDuplicate(List.of(1,2,3,3,4,5)));
    }
}
