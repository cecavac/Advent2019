package com.celecavac.advent2019;

import android.util.Log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Executer extends Thread {
    protected boolean halted = false;

    protected static boolean systemHalt = false;
    protected static long result;
    protected static long result2;

    protected long []positions;
    protected long relativeBase = 0;

    protected BlockingQueue userInput;
    protected BlockingQueue outputQueue;

    protected Semaphore lock = new Semaphore(0);

    public Executer(String input, BlockingQueue userInput, BlockingQueue outputQueue) {
        String elements[] = input.split(",");
        positions = new long[elements.length * 100];

        for (int i = 0; i < elements.length; i++)
            positions[i] = Long.parseLong(elements[i]);

        this.userInput = userInput;
        this.outputQueue = outputQueue;
    }

    public void waitForInput() {
        try {
            lock.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private long getUserInput(){
        try {
            lock.release();
            return (Long) userInput.take();
        } catch (InterruptedException ex) {
            Logger.getLogger(Executer.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    private int getParameter(int ip, int parameter) {
        int firstByte = (int) positions[ip];
        int mode;
        
        switch (parameter) {
            case 1:
                mode = (firstByte / 100) % 10;
                break;
                
            case 2:
                mode = (firstByte / 1000) % 10;
                break;
                
            case 3:
                mode = (firstByte / 10000) % 10;
                break;
            default:
                return -1;
        }

        switch (mode) {
            case 0:
                // Position
                return (int) positions[ip + parameter];
            case 1:
                // Intermediate
                return ip + parameter;
            case 2:
                // Relative
                return (int) (positions[ip + parameter] + relativeBase);
            default:
                // Error
                return -1;
        }
    }

    protected void input(int parameter1) {
        positions[parameter1] = getUserInput();
    }

    protected void output(int parameter1) throws InterruptedException {
        outputQueue.put(positions[parameter1]);
    }
    
    public void run() {

            program:
            for (int ip = 0;;) {
                try {
                    int opCode = (int) (positions[ip] % 100);
                    int parameter1 = 0, parameter2 = 0, parameter3 = 0;

                    switch (opCode) {
                        case 1:
                            // ADD 3 parameters
                        case 2:
                            // MUL 3 parameters
                        case 7:
                            // LT 3 parameters
                        case 8:
                            // EQ 3 parameters
                            parameter3 = getParameter(ip, 3);
                        case 5:
                            // JIT 2 parameters
                        case 6:
                            // JIF 2 parameters
                            parameter2 = getParameter(ip, 2);
                        case 3:
                            // IN 1 parameter
                        case 4:
                            // OUT 1 parameter
                        case 9:
                            // RB 1 parameter
                            parameter1 = getParameter(ip, 1);
                        case 99:
                            // HALT no parameters
                            break;
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
                            input(parameter1);
                            ip += 2;
                            break;
                            
                        case 4:
                            // OUT
                            output(parameter1);
                            ip += 2;
                            break;
                            
                        case 5:
                            // JIT
                            if (positions[parameter1] != 0) {
                                ip = (int) positions[parameter2];
                            } else {
                                ip += 3;
                            }
                            break;
                            
                        case 6:
                            // JIF
                            if (positions[parameter1] == 0) {
                                ip = (int) positions[parameter2];
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

                        case 9:
                            // RB
                            relativeBase += positions[parameter1];
                            ip += 2;
                            break;
                            
                        case 99:
                            // HALT
                            lock.release();
                            halted = true;
                            break program;
                            
                        default:
                            /* Error */
                            positions[-1] = 0;
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Executer.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (systemHalt)
                    break;
            }
    }

    public boolean isHalted() {
        return halted;
    }

    public static void setResult(long result) {
        Executer.result = result;
    }

    public static long getResult() {
        return result;
    }

    public static long getResult2() {
        return result2;
    }

    public static void setResult2(long result2) {
        Executer.result2 = result2;
    }
}