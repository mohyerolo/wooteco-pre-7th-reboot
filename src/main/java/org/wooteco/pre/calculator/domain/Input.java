package org.wooteco.pre.calculator.domain;

public class Input {
    private final String delimiter;
    private final Numbers numbers;

    public Input(final String delimiter, final Numbers numbers) {
        this.delimiter = delimiter;
        this.numbers = numbers;
    }
}
