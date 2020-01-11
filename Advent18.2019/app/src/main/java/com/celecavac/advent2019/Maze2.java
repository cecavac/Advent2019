package com.celecavac.advent2019;

import java.util.HashSet;

public class Maze2 {
    private int result = 0;

    protected char [][]matrix;
    protected int height, width;

    public Maze2(String input) {
        String []lines = input.split("\\n");

        height = lines.length;
        width = lines[0].length();
        matrix = new char[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                char c = lines[i].charAt(j);
                matrix[i][j] = c;
            }
        }
    }

    public void run() {
        for (int z = 0; z < 4; z++) {
            char [][]modifiedMatrix = new char[height][width];
            int positionI = -1, positionJ = -1;
            int enterance = 0;

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (matrix[i][j] != '@') {
                        modifiedMatrix[i][j] = matrix[i][j];
                    } else {
                        if (enterance != z) {
                            modifiedMatrix[i][j] = '#';
                        } else {
                            positionI = i;
                            positionJ = j;
                            modifiedMatrix[i][j] = '@';
                        }

                        enterance++;
                    }
                }
            }

            int startI = -1, endI = -1, startJ = -1, endJ = -1;
            switch (z) {
                case 0:
                    startI = 0;
                    endI = positionI + 1;
                    startJ = 0;
                    endJ = positionJ + 1;
                    break;
                case 1:
                    startI = 0;
                    endI = positionI + 1;
                    startJ = positionJ;
                    endJ = width;
                    break;
                case 2:
                    startI = positionI;
                    endI = height;
                    startJ = 0;
                    endJ = positionJ + 1;
                    break;
                case 3:
                    startI = positionI;
                    endI = height;
                    startJ = positionJ;
                    endJ = width;
                    break;
            }

            HashSet<Character> doorSet = new HashSet<>();

            // Clear other quadrants
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (i >= startI && i <endI && j >= startJ && j < endJ) {
                        continue;
                    } else {
                        // collect keys from other quadrants
                        char c = modifiedMatrix[i][j];
                        if (isKey(c)) {
                            // Convert key to door
                            doorSet.add(Character.toUpperCase(c));
                        }

                        // Treat all the elements as walls
                        modifiedMatrix[i][j] = '#';
                    }
                }
            }

            // Open all doors linked to keys from other quadrants
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    char c = modifiedMatrix[i][j];
                    if (isDoor(c) && doorSet.contains(c)) {
                        modifiedMatrix[i][j] = '.';
                    }
                }
            }

            String subInput = "";
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    subInput += modifiedMatrix[i][j];
                }

                if (i != height - 1)
                    subInput += "\n";
            }

            Maze maze = new Maze(subInput);
            maze.run();
            result += maze.getResult();
        }
    }

    private boolean isDoor(char c) {
        return c >= 'A' && c <= 'Z';
    }

    private boolean isKey(char c) {
        return c >= 'a' && c <= 'z';
    }

    public int getResult() {
        return result;
    }
}
