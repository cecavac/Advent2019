package com.celecavac.advent2019;

import android.util.Log;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Robot {
    private PaintField paintField;
    private Direction direction = Direction.UP;
    private int positionI = 0, positionJ = 0;

    private BlockingQueue inputQ = new ArrayBlockingQueue<Long>(10000);
    private BlockingQueue outputQ = new ArrayBlockingQueue<Long>(10000);
    private Executer executer = new Executer(Inputs.Input1, inputQ, outputQ);

    public Robot(int initialColor) {
        paintField = new PaintField(initialColor);
    }

    public void run() throws InterruptedException {
        executer.start();

        while (!executer.isHalted()) {
            long color = paintField.getColor(positionI, positionJ);
            inputQ.add(color);

            long newColor = (long) outputQ.take();
            long turn = (long) outputQ.take();

            paintField.putColor(positionI, positionJ, newColor);
            direction = Direction.Turn(direction, turn);
            move();
        }
    }

    private void move() {
        switch (direction) {
            case LEFT:
                positionJ--;
                break;
            case RIGHT:
                positionJ++;
                break;
            case UP:
                positionI--;
                break;
            case DOWN:
                positionI++;
                break;
        }
    }

    public int getResult() {
        return paintField.getSize();
    }

    public void print() {
        int minI = Integer.MAX_VALUE;
        int minJ = Integer.MAX_VALUE;
        int maxI = Integer.MIN_VALUE;
        int maxJ = Integer.MIN_VALUE;

        for (Map.Entry<MyPoint, Long> entry : paintField.getMap().entrySet()) {
            if (entry.getValue() == 1) {
                int i = entry.getKey().getI();
                int j = entry.getKey().getJ();

                minI = Math.min(minI, i);
                minJ = Math.min(minJ, j);
                maxI = Math.max(maxI, i);
                maxJ = Math.max(maxJ, j);
            }
        }

        for (int i = minI; i <= maxI; i++) {
            String row = "";
            for (int j = minJ; j <= maxJ; j++) {
                long color = paintField.getColor(i, j);
                if (color == 0) {
                    row += ".";
                } else {
                    row += "#";
                }
            }
            Log.e("Registration ID", row);
        }
    }
}
