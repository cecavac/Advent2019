package com.celecavac.advent2019;

import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Navigator {
    private int result;
    private long result2;
    private Direction direction;

    private BlockingQueue inputQ = new ArrayBlockingQueue<Long>(10000);
    private BlockingQueue outputQ = new ArrayBlockingQueue<Long>(10000);
    private Executer executer;

    private char[][]camera = new char[51][51];

    public Navigator(String input) {
        executer = new Executer(input, inputQ, outputQ);
    }

    public void scan () {
        int i = 0, j = 0;

        executer.run();

        try {
            while (!outputQ.isEmpty()) {
                long element = (long) outputQ.take();
                char pixel = (char) element;

                if (pixel == '\n') {
                    j = 0;
                    i++;
                } else {
                    camera[i][j++] = pixel;
                }
            }

            printCamera();
            findIntersections();
            printCamera();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        simulateWalk();
    }

    public void run () throws InterruptedException {
        executer.start();
        move();
        long element = 0;
        while (element < 256)
            element = (long) outputQ.take();
        result2 = element;
    }

    private void move() throws InterruptedException {
        /* Crafted by hand after inspecting simulateWalk() output */

        // main:
        inputQ.put((long) 'A');
        inputQ.put((long) ',');
        inputQ.put((long) 'B');
        inputQ.put((long) ',');
        inputQ.put((long) 'A');
        inputQ.put((long) ',');
        inputQ.put((long) 'C');
        inputQ.put((long) ',');
        inputQ.put((long) 'C');
        inputQ.put((long) ',');
        inputQ.put((long) 'A');
        inputQ.put((long) ',');
        inputQ.put((long) 'B');
        inputQ.put((long) ',');
        inputQ.put((long) 'C');
        inputQ.put((long) ',');
        inputQ.put((long) 'B');
        inputQ.put((long) ',');
        inputQ.put((long) 'B');
        inputQ.put((long) '\n');

        // A:
        inputQ.put((long) 'L');
        inputQ.put((long) ',');
        inputQ.put((long) '8');
        inputQ.put((long) ',');
        inputQ.put((long) 'R');
        inputQ.put((long) ',');
        inputQ.put((long) '1');
        inputQ.put((long) '0');
        inputQ.put((long) ',');
        inputQ.put((long) 'L');
        inputQ.put((long) ',');
        inputQ.put((long) '8');
        inputQ.put((long) ',');
        inputQ.put((long) 'R');
        inputQ.put((long) ',');
        inputQ.put((long) '8');
        inputQ.put((long) '\n');

        // B:
        inputQ.put((long) 'L');
        inputQ.put((long) ',');
        inputQ.put((long) '1');
        inputQ.put((long) '2');
        inputQ.put((long) ',');
        inputQ.put((long) 'R');
        inputQ.put((long) ',');
        inputQ.put((long) '8');
        inputQ.put((long) ',');
        inputQ.put((long) 'R');
        inputQ.put((long) ',');
        inputQ.put((long) '8');
        inputQ.put((long) '\n');

        // C:
        inputQ.put((long) 'L');
        inputQ.put((long) ',');
        inputQ.put((long) '8');
        inputQ.put((long) ',');
        inputQ.put((long) 'R');
        inputQ.put((long) ',');
        inputQ.put((long) '6');
        inputQ.put((long) ',');
        inputQ.put((long) 'R');
        inputQ.put((long) ',');
        inputQ.put((long) '6');
        inputQ.put((long) ',');
        inputQ.put((long) 'R');
        inputQ.put((long) ',');
        inputQ.put((long) '1');
        inputQ.put((long) '0');
        inputQ.put((long) ',');
        inputQ.put((long) 'L');
        inputQ.put((long) ',');
        inputQ.put((long) '8');
        inputQ.put((long) '\n');

        inputQ.put((long) 'n');
        inputQ.put((long) '\n');
    }


    private void findIntersections() {
        for (int i = 1; i < 50; i++) {
            for (int j = 1; j < 50; j++) {
                if (    camera[i + 0][j + 0] == '#' &&
                        camera[i + 0][j + 1] == '#' &&
                        camera[i + 1][j + 0] == '#' &&
                        camera[i + 0][j - 1] == '#' &&
                        camera[i - 1][j + 0] == '#') {
                    result += i * j;
                    //camera[i][j] = 'O';
                }
            }
        }
    }

    public int getResult() {
        return result;
    }

    public long getResult2() {
        return result2;
    }

    private void printCamera() {
        Log.e("Camera output", "=======================================================");
        for (int i = 0; i < 51; i++) {
            String row = "";
            for (int j = 0; j < 51; j++)
                if (camera[i][j] != 0)
                    row += camera[i][j];
            row += " :" + i;
            Log.e("Row", row);
        }
    }

    private int positionI, positionJ;
    private void simulateWalk() {
        int startI = 0, startJ = 0;
        mainLoop: for (int i = 0; i < 51; i++) {
            for (int j = 0; j < 51; j++)
                if (camera[i][j] == '^') {
                    startI = i;
                    startJ = j;
                    break mainLoop;
                }
        }

        ArrayList<Integer> instructions = new ArrayList<Integer>();

        positionI = startI;
        positionJ = startJ;
        // hardcoded the first instruction, based on output observation
        direction = Direction.LEFT;
        instructions.add(-1);

        boolean done;
        do {
            walkAndTurn(instructions);

            done = direction == Direction.LEFT &&
                    camera[positionI][positionJ - 1] == '.' &&
                    camera[positionI - 1][positionJ] == '.' &&
                    camera[positionI + 1][positionJ] == '.';
        } while (!done);

        printCamera();

        String path = "";
        for (Integer i: instructions) {
            if (i > 0) {
                path += "," + i;
            } else if (i == -1) {
                path += ",L";
            } else if (i == -2) {
                path += ",R";
            } else {
                path += "?";
            }
        }
        Log.e("FULL PATH", path);
    }

    private void walkAndTurn (ArrayList<Integer> instructions) {
        int step = 0;

        switch (direction) {
            case LEFT:
                while (positionJ > 0 && camera[positionI][positionJ - 1] == '#') {
                    camera[positionI][positionJ] = '#';
                    positionJ--;
                    camera[positionI][positionJ] = '<';
                    step++;
                }
                break;
            case RIGHT:
                while (positionJ < 50 && camera[positionI][positionJ + 1] == '#') {
                    camera[positionI][positionJ] = '#';
                    positionJ++;
                    camera[positionI][positionJ] = '>';
                    step++;
                }
                break;
            case UP:
                while (positionI > 0 && camera[positionI - 1][positionJ] == '#') {
                    camera[positionI][positionJ] = '#';
                    positionI--;
                    camera[positionI][positionJ] = '^';
                    step++;
                }
                break;
            case DOWN:
                while (positionI < 50 && camera[positionI + 1][positionJ] == '#') {
                    camera[positionI][positionJ] = '#';
                    positionI++;
                    camera[positionI][positionJ] = 'V';
                    step++;
                }
                break;
        }

        instructions.add(step);

        switch (direction) {
            case LEFT:
                if (positionI > 0 && camera[positionI - 1][positionJ] == '#'){
                    instructions.add(-2);
                    direction = Direction.UP;
                    camera[positionI][positionJ] = '^';
                } else if (positionI < 50 && camera[positionI + 1][positionJ] == '#'){
                    instructions.add(-1);
                    direction = Direction.DOWN;
                    camera[positionI][positionJ] = 'V';
                }
                break;
            case RIGHT:
                if (positionI > 0 && camera[positionI - 1][positionJ] == '#'){
                    instructions.add(-1);
                    direction = Direction.UP;
                    camera[positionI][positionJ] = '^';
                } else if (positionI < 50 && camera[positionI + 1][positionJ] == '#'){
                    instructions.add(-2);
                    direction = Direction.DOWN;
                    camera[positionI][positionJ] = 'V';
                }
                break;
            case UP:
                if (positionJ > 0 && camera[positionI][positionJ - 1] == '#'){
                    instructions.add(-1);
                    direction = Direction.LEFT;
                    camera[positionI][positionJ] = '<';
                } else if (positionJ < 50 && camera[positionI][positionJ + 1] == '#'){
                    instructions.add(-2);
                    direction = Direction.RIGHT;
                    camera[positionI][positionJ] = '>';
                }
                break;
            case DOWN:
                if (positionJ > 0 && camera[positionI][positionJ - 1] == '#'){
                    instructions.add(-2);
                    direction = Direction.LEFT;
                    camera[positionI][positionJ] = '>';
                } else if (positionJ < 50 && camera[positionI][positionJ + 1] == '#'){
                    instructions.add(-1);
                    direction = Direction.RIGHT;
                    camera[positionI][positionJ] = '<';
                }
                break;
        }
    }
}
