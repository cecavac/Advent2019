package com.celecavac.advent2019;

public class Packet {
    private long address, x,y;

    public Packet(long address) {
        this.address = address;
        this.x = x;
    }

    public long getAddress() {
        return address;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public void setAddress(long address) {
        this.address = address;
    }

    public void setX(long x) {
        this.x = x;
    }

    public void setY(long y) {
        this.y = y;
    }
}
