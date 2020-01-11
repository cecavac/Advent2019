package com.celecavac.advent2019;

public enum Direction {
    LEFT, RIGHT, UP, DOWN;

    public static Direction Turn(Direction d, long i) {
        switch (d) {
            case LEFT:
                if (i == 0) {
                    return DOWN;
                } else {
                    return UP;
                }
            case RIGHT:
                if (i == 0) {
                    return UP;
                } else {
                    return DOWN;
                }
            case UP:
                if (i == 0) {
                    return LEFT;
                } else {
                    return RIGHT;
                }
            case DOWN:
                if (i == 0) {
                    return RIGHT;
                } else {
                    return LEFT;
                }
            default:
                throw new NullPointerException();
        }
    }
}
