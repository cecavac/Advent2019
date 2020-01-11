package com.celecavac.advent2019;

import android.util.Log;

import java.util.HashMap;

public class Content {
    private HashMap<MyPoint, Long> map = new HashMap<MyPoint, Long>();
    private long initialElement;

    public Content(long initialElement) {
        this.initialElement = initialElement;
    }

    public Long getElement(int i, int j) {
        MyPoint location = new MyPoint(i, j);

        if (!map.containsKey(location)) {
            return initialElement;
        } else {
            return map.get(location);
        }
    }

    public void putElement(int i, int j, Long color) {
        MyPoint location = new MyPoint(i, j);
        map.put(location, color);
    }

    public int getSize() {
        return map.size();
    }

    public HashMap<MyPoint, Long> getMap() {
        return map;
    }

    public int getElementOccurance(long element) {
        int result = 0;

        for (HashMap.Entry<MyPoint, Long> entry : map.entrySet()) {
            if (entry.getValue() == element)
                result++;
        }

        return  result;
    }

    public void print() {
        int minI = Integer.MAX_VALUE;
        int minJ = Integer.MAX_VALUE;
        int maxI = Integer.MIN_VALUE;
        int maxJ = Integer.MIN_VALUE;

        for (HashMap.Entry<MyPoint, Long> entry : map.entrySet()) {
                int i = entry.getKey().getI();
                int j = entry.getKey().getJ();

                minI = Math.min(minI, i);
                minJ = Math.min(minJ, j);
                maxI = Math.max(maxI, i);
                maxJ = Math.max(maxJ, j);
        }

        for (int j = minJ; j <= maxJ; j++) {
            String row = "";
            for (int i = minI; i <= maxI; i++) {
                long element = getElement(i, j);
                switch ((int) element) {
                    case 0:
                        row += " ";
                        break;
                    case 1:
                        row += "#";
                        break;
                    case 2:
                        row += "*";
                        break;
                    case 3:
                        row += "_";
                        break;
                    case 4:
                        row += "O";
                        break;

                }

            }
            Log.e("Row", row);
        }
    }
}
