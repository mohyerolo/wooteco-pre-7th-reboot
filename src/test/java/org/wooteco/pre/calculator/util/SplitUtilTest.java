package org.wooteco.pre.calculator.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.wooteco.pre.calculator.constant.SplitMessage.DEFAULT_DELIMITER;
import static org.wooteco.pre.calculator.constant.SplitMessage.DELIMITER_REG_EXP;

class SplitUtilTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "//\\n1,2,3", "// \\n1,2,3"
    })
    void 비어있는_구분자(final String input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> SplitUtil.splitDelimiter(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "//", "//a1,2a3"
    })
    void 커스텀_종료문자가_없음(final String input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> SplitUtil.splitDelimiter(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1,2,3", "1:2:3", "1,2:3"
    })
    void 기본_구분자만_있음(final String input) {
        assertThat(SplitUtil.splitDelimiter(input))
                .isEqualTo(String.format(DELIMITER_REG_EXP, DEFAULT_DELIMITER));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "///\\n1,2,3#[,|:|/]", "//^\\n1,2,3#[,|:|\\^]", "//a\\n1,2,3#[,|:|a]", "//a\\n//b\\n1a2b3#[,|:|a|b]"
    }, delimiter = '#')
    void 커스텀_있음(final String input, final String result) {
        assertThat(SplitUtil.splitDelimiter(input))
                .isEqualTo(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1,2,3", "1:2,3", "//a\\n1a2,3"
    })
    void 커스텀구분자_제외_뒤의_숫자들만(final String input) {
        String delimiters = SplitUtil.splitDelimiter(input);
        assertThat(SplitUtil.splitNumbers(input, delimiters))
                .containsExactly("1", "2", "3");
    }
}
