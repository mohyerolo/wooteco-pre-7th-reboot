package org.wooteco.pre.convenienceStore.validator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest {
    @ParameterizedTest
    @ValueSource(strings = {"Y", "N"})
    void YOrN_성공(final String input) {
        Assertions.assertThatNoException()
                .isThrownBy(() -> InputValidator.validateAnswer(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "", "as", "YN", "YY", "NN"})
    void YOrN_실패(final String input) {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> InputValidator.validateAnswer(input));
    }
}
