package com.celecavac.advent2019;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Beamer {
    private int result = 0;
    private int result2;
    private HashMap<MyPoint, Long> map;

    private BlockingQueue inputQ = new ArrayBlockingQueue<Long>(10000);
    private BlockingQueue outputQ = new ArrayBlockingQueue<Long>(10000);
    private Executer executer = new Executer(Inputs.Input1, inputQ, outputQ);

    private MyPoint minPoint, leftMaxPoint, rightMaxPoint;
    private Line l1, l2;

    private int beamI, beamJ;

    public void run() throws InterruptedException {
        int iterations = 50;
        int width = 100;
        int height = 100;

        visualize(iterations, 0, 0, false);
        print();

        aproximate(iterations);
        print();

        fitShip(width, height);
        print();

        findBest(width, height);
        drawShip(width, height);
        print();
    }

    private void visualize(int iterations, int startI, int startJ, boolean earlyStop) throws InterruptedException {
        map = new HashMap<MyPoint, Long>();

        for (int i = startI; i < startI + iterations; i++) {
            for (int j = startJ; j < startJ + iterations; j++) {
                inputQ = new ArrayBlockingQueue<Long>(10000);
                outputQ = new ArrayBlockingQueue<Long>(10000);
                executer = new Executer(Inputs.Input1, inputQ, outputQ);

                inputQ.put((long) i);
                inputQ.put((long) j);
                executer.run();

                MyPoint point = new MyPoint(i, j);
                long value = (long) outputQ.take();

                if (value == 1L && !earlyStop)
                    result++;

                map.put(point, value);
            }
        }

        if (earlyStop)
            return;

        outerLoop: for (int i = 1; i < startI + iterations; i++) {
            for (int j = startJ; j < startJ + iterations; j++) {
                if (getElement(i,j) == 1L) {
                    minPoint = new MyPoint(i, j);
                    break outerLoop;
                }
            }
        }

        long oldValue = -1;
        for (int j = 7850;;j++) {
            int i = 10004;

            inputQ = new ArrayBlockingQueue<Long>(10000);
            outputQ = new ArrayBlockingQueue<Long>(10000);
            executer = new Executer(Inputs.Input1, inputQ, outputQ);

            inputQ.put((long) i);
            inputQ.put((long) j);
            executer.run();

            MyPoint point = new MyPoint(i, j);
            long value = (long) outputQ.take();

            if (value == 1L && oldValue == 0L)
                leftMaxPoint = point;

            if (value == 0L && oldValue == 1L) {
                rightMaxPoint = point;
                break;
            }

            oldValue = value;
        }

        l1 = new Line(minPoint.getJ(), minPoint.getI(), leftMaxPoint.getJ(), leftMaxPoint.getI());
        l2 = new Line(minPoint.getJ(), minPoint.getI(), rightMaxPoint.getJ(), rightMaxPoint.getI());
    }

    private void aproximate(int iterations) {
        map = new HashMap<MyPoint, Long>();
        for (int i = 0; i < iterations; i++) {
            int j1 = l1.getX(i);
            MyPoint p1 = new MyPoint(i, j1);
            map.put(p1, 1L);

            int j2 = l2.getX(i);
            MyPoint p2 = new MyPoint(i, j2);
            map.put(p2, 1L);
        }
    }


    public int getResult() {
        return result;
    }

    public int getResult2() {
        return result2;
    }

    public Long getElement(int i, int j) {
        MyPoint location = new MyPoint(i, j);

        if (!map.containsKey(location)) {
            return -1L;
        } else {
            return map.get(location);
        }
    }

    public void print() {
        int minI = Integer.MAX_VALUE;
        int minJ = Integer.MAX_VALUE;
        int maxI = Integer.MIN_VALUE;
        int maxJ = Integer.MIN_VALUE;

        for (Map.Entry<MyPoint, Long> entry : map.entrySet()) {
            if (entry.getValue() == 1L) {
                int i = entry.getKey().getI();
                int j = entry.getKey().getJ();

                minI = Math.min(minI, i);
                minJ = Math.min(minJ, j);
                maxI = Math.max(maxI, i);
                maxJ = Math.max(maxJ, j);
            }
        }

        for (int i = minI - 1; i <= maxI + 1; i++) {
            int minC = Integer.MAX_VALUE;
            int maxC = Integer.MIN_VALUE;
            String row = "";
            for (int j = minJ - 1; j <= maxJ + 1; j++) {
                long element = getElement(i, j);
                if (element == 1L) {
                    row += "#";
                    minC = Math.min(minC, j);
                    maxC = Math.max(maxC, j);
                } else if (element == 2L) {
                    row += "O";
                } else {
                    row += ".";
                }
            }
            Log.e("Beam", row + "  i: " + i + " " + minC + "-" + maxC);
        }
    }

    public void fitShip(int width, int height) throws InterruptedException {
        int xC = Line.getXC(l2, l1, width, height);
        int xB = Line.getXB(xC, width);
        int yB = Line.getYB(l2, l1, width, height);
        int yC = Line.getYC(yB, height);

        int xA = xC;
        int yA = yB;

        result2 = xA * 10000 + yA;

        visualize(width + 20, yA - 10, xA - 10, true);
    }

    public void findBest(int width, int height) {
        int minI = Integer.MAX_VALUE;
        int minJ = Integer.MAX_VALUE;
        int maxI = Integer.MIN_VALUE;
        int maxJ = Integer.MIN_VALUE;

        for (Map.Entry<MyPoint, Long> entry : map.entrySet()) {
            if (entry.getValue() == 1L) {
                int i = entry.getKey().getI();
                int j = entry.getKey().getJ();

                minI = Math.min(minI, i);
                minJ = Math.min(minJ, j);
                maxI = Math.max(maxI, i);
                maxJ = Math.max(maxJ, j);
            }
        }

        outerLoop: for (int i = minI - 1; i <= maxI + 1; i++) {
            int lockJ = 0;
            for (int j = maxJ + 1; j >minJ; j--) {
                long element = getElement(i, j);
                if (element == 1L) {
                    lockJ = j;
                    break;
                }
            }

            for (int z = i; z <= maxI + 1; z++) {
                int rowOccurance = 0;
                for (int j = lockJ; j > minJ; j--) {
                    long element = getElement(z, j);
                    if (element == 1L) {
                        rowOccurance++;
                    }
                }
                if (rowOccurance < width)
                    continue outerLoop;

                if (z == i + height - 1) {
                    beamI = i;
                    beamJ = (lockJ - height + 1);
                    result2 = beamI * 10000 + beamJ;
                    return;
                }
            }
        }
    }

    private void drawShip(int width, int height) {
        MyPoint bestPoint = null;
        int distance = Integer.MAX_VALUE;

        for (int i = beamI; i < beamI + height; i++)
            for (int j = beamJ; j < beamJ + width; j++) {
                MyPoint point = new MyPoint(i ,j);
                int d = j + i;
                if (distance > d) {
                    bestPoint = point;
                    distance = d;
                }
                map.put(point, 2L);
            }

        result2 = bestPoint.getI() * 10000 + bestPoint.getJ();
    }
}
