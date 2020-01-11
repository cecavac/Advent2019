package com.celecavac.advent2019;

import java.util.HashSet;

public class Bugs {
    private long result;
    private int [][]matrix = new int [5][5];

    public Bugs(String input) {
        String []lines = input.split("\\n");
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length(); j++) {
                if (lines[i].charAt(j) == '#') {
                    matrix[i][j] = 1;
                }
            }
        }
    }

    public void tick() {
        int [][]newMatrix = new int [5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int surroundingBugs = 0;

                // bug up
                if (i > 0 && matrix[i - 1][j] == 1)
                    surroundingBugs++;

                // bug left
                if (j > 0 && matrix[i][j - 1] == 1)
                    surroundingBugs++;

                // bug down
                if (i < 4 && matrix[i + 1][j] == 1)
                    surroundingBugs++;

                // bug right
                if (j < 4 && matrix[i][j + 1] == 1)
                    surroundingBugs++;


                if (matrix[i][j] == 1) {
                    // bug
                    if (surroundingBugs != 1) {
                        newMatrix[i][j] = 0;
                    } else {
                        newMatrix[i][j] = 1;
                    }
                } else {
                    // empty space
                    if (surroundingBugs == 1 || surroundingBugs == 2) {
                        newMatrix[i][j] = 1;
                    } else {
                        newMatrix[i][j] = 0;
                    }
                }
            }
        }

        matrix = newMatrix;
    }

    public int hashBugs() {
        int hash = 0;
        int value = 1;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == 1) {
                    hash |= value;
                }

                value <<= 1;
            }
        }

        return hash;
    }

    public void run() {
        int size, oldSize;
        HashSet<Integer> set = new HashSet<Integer>();
        set.add(hashBugs());

        do {
            oldSize = set.size();
            tick();
            set.add(hashBugs());
            size = set.size();
        } while (size != oldSize);

        result = hashBugs();
    }

    public long getResult() {
        return result;
    }
}
