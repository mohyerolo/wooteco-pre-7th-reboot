package org.wooteco.pre.convenienceStore.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderItemParserTest {

    @ParameterizedTest
    @ValueSource(strings = {"[]", "[ ]", "[  ]", "[asdf]", "[asd ]", "[ass+s]"})
    void 하이픈으로_분리_후_필드부족(final String input) {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> OrderItemParser.splitOrderItem(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"[asd-2]", "[ asd-2]", "[asd -10]", "[asd dfs-9]"})
    void 하이픈으로_분리_성공(final String input) {
        Assertions.assertThatNoException()
                .isThrownBy(() -> OrderItemParser.splitOrderItem(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"[-2]", "[ -2]", "[  -10]"})
    void 분리_후_이름쪽에서_문제_실패(final String input) {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> OrderItemParser.splitOrderItem(input));
    }

}
