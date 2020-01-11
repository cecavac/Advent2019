package com.celecavac.advent2019;

import java.util.HashMap;

public class PaintField {
    private HashMap<MyPoint, Long> map = new HashMap<MyPoint, Long>();
    private long initialColor;

    public PaintField(long initialColor) {
        this.initialColor = initialColor;
    }

    public Long getColor(int i, int j) {
        MyPoint location = new MyPoint(i, j);

        if (!map.containsKey(location)) {
            return initialColor;
        } else {
            return map.get(location);
        }
    }

    public void putColor(int i, int j, Long color) {
        MyPoint location = new MyPoint(i, j);
        map.put(location, color);
    }

    public int getSize() {
        return map.size();
    }

    public HashMap<MyPoint, Long> getMap() {
        return map;
    }
}
