package com.celecavac.advent2019;

import androidx.annotation.NonNull;

public class Cell {
    private String name;
    private boolean north, south, west, east;
    private String item;

    public Cell(String name, boolean north, boolean south, boolean west, boolean east, String item) {
        this.name = name;

        this.north = north;
        this.south = south;
        this.west = west;
        this.east = east;

        this.item = item;
    }

    public boolean getNorth() {
        return north;
    }

    public boolean getSouth() {
        return south;
    }

    public boolean getWest() {
        return west;
    }

    public boolean getEast() {
        return east;
    }

    public String getItem() {
        return item;
    }

    public String getName() {
        return name;
    }

    @NonNull
    @Override
    public String toString() {
        String ret = name;

        ret += "\n" + north + " " + south + " " +  west + " " + east;
        ret += "\n" + item;

        return ret;
    }
}
