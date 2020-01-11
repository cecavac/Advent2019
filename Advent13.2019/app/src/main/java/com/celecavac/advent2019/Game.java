package com.celecavac.advent2019;

import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Game {
    private int result;
    private int result2;
    private Content content = new Content(0);

    private BlockingQueue inputQ = new ArrayBlockingQueue<Long>(10000);
    private BlockingQueue outputQ = new ArrayBlockingQueue<Long>(10000);
    private Executer executer;

    public Game(String input) {
        executer = new Executer(input, inputQ, outputQ);
    }

    public void run () {

        executer.run();

        try {
            while (!outputQ.isEmpty()) {
                long i = (long) outputQ.take();
                long j = (long) outputQ.take();
                long element = (long) outputQ.take();

                content.putElement((int) i, (int) j, element);
            }

            result = content.getElementOccurance(2);
            content.print();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run2 () {
        int paddle = 0;
        int ball = 0;

        executer.start();

        try {
            do {
                executer.waitForInput();
                while (!outputQ.isEmpty()) {
                    long i = (long) outputQ.take();
                    long j = (long) outputQ.take();
                    long element = (long) outputQ.take();

                    if (i == -1L && j == 0L) {
                        result2 = (int) element;
                        //Log.e("Game", "Total score: " + result2);
                    } else {
                        content.putElement((int) i, (int) j, element);
                    }

                    if (element == 3) {
                        paddle = (int) i;
                    } else if (element == 4) {
                        ball = (int) i;
                    }
                }

                moveJoystick(ball, paddle);

                result = content.getElementOccurance(2);
                //content.print();
            } while (result != 0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void moveJoystick(int ball, int paddle) throws InterruptedException {
        if (paddle == ball) {
            inputQ.put(0L);
        } else if (paddle < ball) {
            inputQ.put(1L);
        } else {
            inputQ.put(-1L);
        }
    }

    public int getResult() {
        return result;
    }

    public int getResult2() {
        return result2;
    }
}
