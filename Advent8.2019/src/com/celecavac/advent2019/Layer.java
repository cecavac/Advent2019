/*
 * The MIT License
 *
 * Copyright 2019 Cecavac.
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

/**
 *
 * @author Cecavac
 */
public class Layer {
    int width, height;
    int []data;
    
    public Layer(int width, int height, int []data) {
        this.width = width;
        this.height = height;
        this.data = data;
    }
    
    public int getNumberOfZeros() {
        int zeros = 0;
        
        for (int a : data) {
            if (a == 0)
                zeros++;
        }
        
        return zeros;
    }
    
    public int getChecksum() {
        int ones = 0, twos = 0;
        
        for (int a : data) {
            if (a == 1)
                ones++;
            
            if (a == 2)
                twos++;
        }
        
        return ones * twos;
    }
    
    public int[] getData() {
        return data;
    }
}
