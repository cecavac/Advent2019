package com.celecavac.advent2019;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Weights {
    private HashMap<MyPoint, Integer> map = new HashMap<>();

    public void put(MyPoint point, int value) {
        map.put(point, value);
    }

    public Integer get(MyPoint point) {
                if (map.containsKey(point)) {
            return map.get(point);
        } else {
            return Integer.MAX_VALUE;
        }
    }

    public int getIndex(MyPoint point) {
        int z = 0;

        for (Map.Entry<MyPoint, Integer> entry: map.entrySet()) {
            if (point.equals(entry.getKey()))
                return z;

            z++;
        }

        return -1;
    }

    public Set<Map.Entry<MyPoint, Integer>> set() {
        return map.entrySet();
    }

    public int size() {
        return map.size();
    }
}
