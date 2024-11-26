package org.wooteco.pre.convenienceStore.validator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderItemValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"[sdf", "sdf]", "[", "{ㄴㅇㄹ}"})
    void 주문형식_대괄호_에러(final String input) {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> OrderItemValidator.validateOrder(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"[sdf]", "[as sdf]", "[ sdf]"})
    void 주문형식_대괄호_있음(final String input) {
        Assertions.assertThatNoException()
                .isThrownBy(() -> OrderItemValidator.validateOrder(input));
    }

}
