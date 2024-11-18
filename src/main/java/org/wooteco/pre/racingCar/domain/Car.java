package org.wooteco.pre.racingCar.domain;

public class Car {
    private static final int MOVE_MIN_NUM = 4;

    private final String name;
    private int moveCnt;

    public Car(final String name) {
        this.name = name;
        this.moveCnt = 0;
    }

    public void moveOrNot(final int num) {
        if (num >= MOVE_MIN_NUM) {
            moveCnt++;
        }
    }

    public boolean isWinner(int highestMoveCnt) {
        return moveCnt == highestMoveCnt;
    }

    public String getName() {
        return name;
    }

    public int getMoveCnt() {
        return moveCnt;
    }
}
