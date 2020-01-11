package com.celecavac.advent2019;

import android.util.Log;

public class Executer {
    private int result;
    private int []positions;
    private boolean errorEncountered = false;

    private int []userInput;
    private int userInputIndex = 0;

    public Executer(String input, int []userInput) {
        String elements[] = input.split(",");
        /* Buffer of 3 elements removes the need to worry about corner cases due to
        different instructions length */
        positions = new int[elements.length + 3];

        for (int i = 0; i < elements.length; i++)
            positions[i] = Integer.parseInt(elements[i]);

        this.userInput = userInput;
    }

    private int getUserInput() {
        return userInput[userInputIndex++];
    }

    public void run() {

            program:
            for (int ip = 0;;) {
                int firstByte = positions[ip];
                int mode1 = (positions[ip] / 100);
                mode1 %= 10;
                int mode2 = (positions[ip] / 1000);
                mode2 %= 10;
                int mode3 = (positions[ip] / 10000);
                mode3 %= 10;

                int opCode = (positions[ip] % 100);
                int parameter1;
                int parameter2;
                int parameter3;

                if (mode1 == 0) {
                    parameter1 = positions[ip + 1];
                } else {
                    parameter1 = ip + 1;
                }

                if (mode2 == 0) {
                    parameter2 = positions[ip + 2];
                } else {
                    parameter2 = ip + 2;
                }

                if (mode3 == 0) {
                    parameter3 = positions[ip + 3];
                } else {
                    parameter3 = ip + 3;
                }

                switch (opCode) {
                    case 1:
                        // ADD
                        positions[parameter3] = positions[parameter1] + positions[parameter2];
                        ip += 4;
                        break;

                    case 2:
                        // MUL
                        positions[parameter3] = positions[parameter1] * positions[parameter2];
                        ip += 4;
                        break;

                    case 3:
                        // IN
                        positions[parameter1] = getUserInput();
                        ip += 2;
                        break;

                    case 4:
                        // OUT
                        result = positions[parameter1];
                        Log.e("Output", "result: " + result);
                        ip += 2;
                        break;

                    case 5:
                        // JIT
                        if (positions[parameter1] != 0) {
                            ip = positions[parameter2];
                        } else {
                            ip += 3;
                        }
                        break;

                    case 6:
                        // JIF
                        if (positions[parameter1] == 0) {
                            ip = positions[parameter2];
                        } else {
                            ip += 3;
                        }
                        break;

                    case 7:
                        // LT
                        if (positions[parameter1] < positions[parameter2]) {
                            positions[parameter3] = 1;
                        } else {
                            positions[parameter3] = 0;
                        }
                        ip += 4;

                        break;

                    case 8:
                        // EQ
                        if (positions[parameter1] == positions[parameter2]) {
                            positions[parameter3] = 1;
                        } else {
                            positions[parameter3] = 0;
                        }
                        ip += 4;
                        break;

                    case 99:
                        break program;

                    default:
                        /* Error */
                        positions[Integer.MAX_VALUE] = 0;
                }
            }

    }

    public int getIntResult() {
        return result;
    }

    public String getResult() {
        return Integer.toString(result);
    }

    public boolean isErrorEncountered() {
        return errorEncountered;
    }
}
