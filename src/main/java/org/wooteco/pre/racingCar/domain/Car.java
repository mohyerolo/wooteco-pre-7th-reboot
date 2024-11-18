package org.wooteco.pre.racingCar.domain;

public class Car {
    private final String name;
    private int moveCnt;

    public Car(final String name) {
        this.name = name;
        this.moveCnt = 0;
    }

    public String getName() {
        return name;
    }
}
