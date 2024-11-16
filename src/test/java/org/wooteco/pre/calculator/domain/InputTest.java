package org.wooteco.pre.calculator.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

class InputTest {

    @ParameterizedTest
    @NullAndEmptySource
    void 입력_빈값_검증_에러(String input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Input(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"asdf", " sd", "sdf sdf "})
    void 입력_빈값_없어서_성공(String input) {
        assertThatNoException()
                .isThrownBy(() -> new Input(input));
    }
}
