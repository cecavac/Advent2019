package com.celecavac.advent2019;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Executer extends Thread {
    private long []positions;
    private long relativeBase = 0;

    private BlockingQueue userInput;
    private BlockingQueue outputQueue;

    public Executer(String input, BlockingQueue userInput, BlockingQueue outputQueue) {
        String elements[] = input.split(",");
        positions = new long[elements.length * 1000];

        for (int i = 0; i < elements.length; i++)
            positions[i] = Long.parseLong(elements[i]);

        this.userInput = userInput;
        this.outputQueue = outputQueue;
    }

    private long getUserInput() {
        try {
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
                            positions[parameter1] = getUserInput();
                            ip += 2;
                            break;
                            
                        case 4:
                            // OUT
                            outputQueue.put(positions[parameter1]);
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
                            break program;
                            
                        default:
                            /* Error */
                            positions[-1] = 0;
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Executer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }

    public long getResult() {
        try {
            return (Long) outputQueue.take();
        } catch (InterruptedException ex) {
            Logger.getLogger(Executer.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
}