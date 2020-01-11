/*
 * The MIT License
 *
 * Copyright 2019 Pseudomonas.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.celecavac.advent2019;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cecavac
 */

public class Executer extends Thread {
    private int result;
    private int []positions;
    private boolean errorEncountered = false;

    private BlockingQueue userInput;
    private BlockingQueue outputQueue;

    public Executer(String input, BlockingQueue userInput, BlockingQueue outputQueue) {
        String elements[] = input.split(",");
        /* Buffer of 3 elements removes the need to worry about corner cases due to
        different instructions length */
        positions = new int[elements.length + 3];

        for (int i = 0; i < elements.length; i++)
            positions[i] = Integer.parseInt(elements[i]);

        this.userInput = userInput;
        this.outputQueue = outputQueue;
    }

    private int getUserInput() {
        try {
            return (Integer) userInput.take();
        } catch (InterruptedException ex) {
            Logger.getLogger(Executer.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    private int getParameter(int ip, int parameter) {
        int firstByte = positions[ip];
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
        
        if (mode == 0) {
            return positions[ip + parameter];
        } else {
            return ip + parameter;
        }
    }
    
    public void run() {

            program:
            for (int ip = 0;;) {
                try {
                    int opCode = (positions[ip] % 100);
                    int parameter1 = getParameter(ip, 1);
                    int parameter2 = getParameter(ip, 2);
                    int parameter3 = getParameter(ip, 3);
                    
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
                            positions[-1] = 0;
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Executer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

    }

    public int getResult() {
        try {
            return (Integer) outputQueue.take();
        } catch (InterruptedException ex) {
            Logger.getLogger(Executer.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public boolean isErrorEncountered() {
        return errorEncountered;
    }
}