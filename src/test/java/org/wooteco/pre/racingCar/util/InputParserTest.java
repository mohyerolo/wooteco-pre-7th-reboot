package org.wooteco.pre.racingCar.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

class InputParserTest {

    @Test
    void 이름_분리_성공() {
        assertThatNoException().isThrownBy(() -> InputParser.splitCarNames("car,car2,car3"));
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {
            "car,,car3", ",car2,car3", " ,car2,car3", "car, ,car3"
    })
    void 이름_분리_검증_실패(String input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> InputParser.splitCarNames(input));
    }

}
