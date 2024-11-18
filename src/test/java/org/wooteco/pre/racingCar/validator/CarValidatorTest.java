package org.wooteco.pre.racingCar.validator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.wooteco.pre.racingCar.util.InputParser;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class CarValidatorTest {

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {
            "car,,car3", ",car2,car3", " ,car2,car3", "car, ,car3"
    })
    void 이름_분리_검증_실패(String input) {
        String[] splitCarNames = InputParser.splitCarNames(input);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> CarValidator.validateBlank(splitCarNames));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "car,car,car3", "car,car2,car2", "car,car2,car"
    })
    void 이름_분리_중복_실패(String input) {
        String[] splitCarNames = InputParser.splitCarNames(input);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> CarValidator.validateDuplicate(splitCarNames));
    }
}
