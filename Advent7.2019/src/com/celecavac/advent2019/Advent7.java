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

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cecavac
 */
public class Advent7 {

    public static void main(String[] args) {
        int []permArray1 = {0,1,2,3,4};
        task(permArray1, "Task1");
        int []permArray2 = {5,6,7,8,9};
        task(permArray2, "Task2");
    }

    private static void task(int []permArray, String name) {
        int max = Integer.MIN_VALUE;
        Permutation permutation = new Permutation(permArray);
        permutation.GetFirst();
        do {
            permArray = permutation.getArray(); 
            int power = thrustersPower(permArray);
            if (max < power)
                max = power;
            permutation.GetNext();
        } while (permutation.HasNext());
        
        System.out.println(name + ": " + max);
    }
    
    private static int thrustersPower(int []phase) {
        BlockingQueue []queue = new BlockingQueue[5];
        Executer []executer = new Executer[5];
        
        for (int i = 0; i < 5; i++) {
            queue[i] = new ArrayBlockingQueue(10000);
            queue[i].add(phase[i]);
        }
        
        try {
            queue[0].put(0);
        } catch (InterruptedException ex) {
            Logger.getLogger(Advent7.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        for (int i = 0; i < 5; i++) {
            executer[i] = new Executer(Inputs.Input1, queue[i], queue[(i + 1) % 5]);
            executer[i].start();
        }

        for (int i = 0; i < 5; i++) 
            try {
                executer[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Advent7.class.getName()).log(Level.SEVERE, null, ex);
            }

        try {
            return (Integer) queue[0].take();
        } catch (InterruptedException ex) {
            Logger.getLogger(Advent7.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
}
