package com.celecavac.advent2019;

public class RoutingData {
    private int []d;
    private int []t;

    public RoutingData(int size) {
        d = new int[size];
        t = new int[size];
    }

    public int[] getD() {
        return d;
    }

    public int[] getT() {
        return t;
    }
}
