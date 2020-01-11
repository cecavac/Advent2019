package com.celecavac.advent2019;

public class MyPoint {
    int i, j;

    public MyPoint(int i, int y) {
        this.i = i;
        this.j = y;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    @Override
    public int hashCode() {
        return i * 100000 + j;
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

        return i == p.i && j == p.j;
    }

    public int distance(MyPoint point) {
        return Math.abs(i - point.i) + Math.abs(j - point.j);
    }
}