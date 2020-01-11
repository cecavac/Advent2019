package com.celecavac.advent2019;

import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Robot extends Thread {
    private Executer executer;
    BlockingQueue<Long> input;
    BlockingQueue<Long> output;

    public long result;

    public Robot(String program) {
        input = new ArrayBlockingQueue<Long>(10000);
        output = new ArrayBlockingQueue<Long>(10000);
        executer = new Executer(program, input, output);
    }

    public void run() {
        executer.start();

        try {

            while(true) {
                program();
                String console = readInput();
                if (console == null)
                    break;
                Log.e("#######", "console: " + console);
            }



        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String readInput() throws InterruptedException {
        String console = "";

        while (true) {
            long element = 0;
            try {
                element = output.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (element > 255) {
                result = element;
                return null;
            }


            char c = (char) element;
            if (c == '\n') {
                break;
            } else {
                console += c;
            }
        }

        return console;
    }

    protected void program() throws InterruptedException {

        sendInstruction("NOT A J\n");

        sendInstruction("NOT C T\n");
        sendInstruction("AND D T\n");
        sendInstruction("OR T J\n");

        sendInstruction("WALK\n");
    }

    protected void sendInstruction(String instruction) throws InterruptedException {
        for (int i = 0; i < instruction.length(); i++) {
            input.put((long) instruction.charAt(i));
        }
    }

    public long getResult() {
        return result;
    }
}
