package com.celecavac.advent2019;

public class MyPoint {
    int x, y, sequence;

    public MyPoint(int x, int y, int sequence) {
        this.x = x;
        this.y = y;
        this.sequence = sequence;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSequence() {
        return sequence;
    }

    @Override
    public int hashCode() {
        return x * 100000 + y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        MyPoint p = (MyPoint) obj;

        return x == p.x && y == p.y;
    }
}
