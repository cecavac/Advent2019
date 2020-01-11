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
public class Advent8 {

    public static void main(String[] args) {
        task();
    }

    private static void task() {
        int min = Integer.MAX_VALUE;
        Layer selectedLayer = null;
        int width = 25;
        int height = 6;
        int size = height * width;
        Image image = new Image(width, height);
        
        String input = Inputs.Input1;

        while (input.length() >= size) {
            int []data = new int[size];
            String dataString = input.substring(0, size);
            input = input.substring(size);

            for (int i = 0; i < size; i++) {
                char c = dataString.charAt(i);
                data[i] = c - '0';
            }
            
            Layer layer = new Layer(width, height, data);
            int numberOfZeros = layer.getNumberOfZeros();
            if (numberOfZeros < min) {
                min = numberOfZeros;
                selectedLayer = layer;
            }

            image.addLayer(layer);
        }
        
        System.out.println("Checksum: " + selectedLayer.getChecksum());
        
        int []imageData = image.renderData();
        System.out.println("Image data: ");
        for (int i = 0; i < imageData.length; i++) {
            if (imageData[i] == 0) {
                System.out.print(' ');
            } else {
                System.out.print('*');
            }
            
            if ((i + 1) % width == 0)
                System.out.println();
        }
        
    }
    
}
