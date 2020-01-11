package com.celecavac.advent2019;

import java.util.LinkedList;

public class RecursiveBugs {
    private long result;
    private LinkedList<int[][]> list = new LinkedList<int[][]>();

    public RecursiveBugs(String input) {
        int [][]matrix = new int[5][5];
        list.add(matrix);

        String []lines = input.split("\\n");
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length(); j++) {
                if (lines[i].charAt(j) == '#') {
                    matrix[i][j] = 1;
                }
            }
        }
    }

    public int cellTick(int content, int surroundingBugs) {
        if (content == 1) {
            // bug
            if (surroundingBugs != 1) {
                return 0;
            } else {
                return 1;
            }
        } else {
            // empty space
            if (surroundingBugs == 1 || surroundingBugs == 2) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public void tickOuterCorners(int [][]matrixPrev, int [][]matrix, int [][]newMatrix) {
        int i, j;
        int surroundingBugs;

        i = 0;
        j = 0;
        surroundingBugs = matrix[i][j + 1] + matrix[i + 1][j];
        if (matrixPrev != null)
            surroundingBugs += matrixPrev[1][2] + matrixPrev[2][1];
        newMatrix[i][j] = cellTick(matrix[i][j], surroundingBugs);

        i = 0;
        j = 4;
        surroundingBugs = matrix[i][j - 1] + matrix[i + 1][j];
        if (matrixPrev != null)
            surroundingBugs += matrixPrev[1][2] + matrixPrev[2][3];
        newMatrix[i][j] = cellTick(matrix[i][j], surroundingBugs);

        i = 4;
        j = 0;
        surroundingBugs = matrix[i - 1][j] + matrix[i][j + 1];
        if (matrixPrev != null)
            surroundingBugs += matrixPrev[2][1] + matrixPrev[3][2];
        newMatrix[i][j] = cellTick(matrix[i][j], surroundingBugs);

        i = 4;
        j = 4;
        surroundingBugs = matrix[i - 1][j] + matrix[i][j - 1];
        if (matrixPrev != null)
            surroundingBugs += matrixPrev[2][3] + matrixPrev[3][2];
        newMatrix[i][j] = cellTick(matrix[i][j], surroundingBugs);
    }

    public void tickInnerCorners(int [][]matrix, int [][]newMatrix) {
        int i, j;
        int surroundingBugs;

        i = 1;
        j = 1;
        surroundingBugs = matrix[i - 1][j] + matrix[i + 1][j] +
                matrix[i][j - 1] + matrix[i][j + 1];
        newMatrix[i][j] = cellTick(matrix[i][j], surroundingBugs);

        i = 1;
        j = 3;
        surroundingBugs = matrix[i - 1][j] + matrix[i + 1][j] +
                matrix[i][j - 1] + matrix[i][j + 1];
        newMatrix[i][j] = cellTick(matrix[i][j], surroundingBugs);

        i = 3;
        j = 1;
        surroundingBugs = matrix[i - 1][j] + matrix[i + 1][j] +
                matrix[i][j - 1] + matrix[i][j + 1];
        newMatrix[i][j] = cellTick(matrix[i][j], surroundingBugs);

        i = 3;
        j = 3;
        surroundingBugs = matrix[i - 1][j] + matrix[i + 1][j] +
                matrix[i][j - 1] + matrix[i][j + 1];
        newMatrix[i][j] = cellTick(matrix[i][j], surroundingBugs);
    }

    public void tickOuterLines(int [][]matrix, int [][]newMatrix, int [][]matrixPrev) {
        int z;
        int surroundingBugs;

        for (int j = 1; j < 4; j++) {
            // up
            z = 0;
            surroundingBugs = matrix[z][j - 1] + matrix[z][j + 1] + matrix[z + 1][j];
            if (matrixPrev != null) {
                surroundingBugs += matrixPrev[1][2];
            }
            newMatrix[z][j] = cellTick(matrix[z][j], surroundingBugs);

            // down
            z = 4;
            surroundingBugs = matrix[z][j - 1] + matrix[z][j + 1] + matrix[z - 1][j];
            if (matrixPrev != null) {
                surroundingBugs += matrixPrev[3][2];
            }
            newMatrix[z][j] = cellTick(matrix[z][j], surroundingBugs);
        }

        for (int i = 1; i < 4; i++) {
            // left
            z = 0;
            surroundingBugs = matrix[i - 1][z] + matrix[i + 1][z] + matrix[i][z + 1];
            if (matrixPrev != null) {
                surroundingBugs += matrixPrev[2][1];
            }
            newMatrix[i][z] = cellTick(matrix[i][z], surroundingBugs);

            // right
            z = 4;
            surroundingBugs = matrix[i - 1][z] + matrix[i + 1][z] + matrix[i][z - 1];
            if (matrixPrev != null) {
                surroundingBugs += matrixPrev[2][3];
            }
            newMatrix[i][z] = cellTick(matrix[i][z], surroundingBugs);
        }
    }

    public void tickRemainingInner(int [][]matrix, int [][]newMatrix, int [][]matrixNext) {
        int i, j;
        int surroundingBugs;

        // up
        i = 1;
        j = 2;
        surroundingBugs = matrix[i][j - 1] + matrix[i][j + 1] + matrix[i - 1][j];
        if (matrixNext != null) {
            for (int z = 0; z < 5; z++) {
                surroundingBugs += matrixNext[0][z];
            }
        }
        newMatrix[i][j] = cellTick(matrix[i][j], surroundingBugs);

        // down
        i = 3;
        j = 2;
        surroundingBugs = matrix[i][j - 1] + matrix[i][j + 1] + matrix[i + 1][j];
        if (matrixNext != null) {
            for (int z = 0; z < 5; z++) {
                surroundingBugs += matrixNext[4][z];
            }
        }
        newMatrix[i][j] = cellTick(matrix[i][j], surroundingBugs);

        // left
        i = 2;
        j = 1;
        surroundingBugs = matrix[i - 1][j] + matrix[i + 1][j] + matrix[i][j - 1];
        if (matrixNext != null) {
            for (int z = 0; z < 5; z++) {
                surroundingBugs += matrixNext[z][0];
            }
        }
        newMatrix[i][j] = cellTick(matrix[i][j], surroundingBugs);

        // right
        i = 2;
        j = 3;
        surroundingBugs = matrix[i - 1][j] + matrix[i + 1][j] + matrix[i][j + 1];
        if (matrixNext != null) {
            for (int z = 0; z < 5; z++) {
                surroundingBugs += matrixNext[z][4];
            }
        }
        newMatrix[i][j] = cellTick(matrix[i][j], surroundingBugs);
    }



    public void tick() {
        LinkedList<int[][]> newList = new LinkedList<int[][]>();
        list.addFirst(new int[5][5]);
        list.addLast(new int[5][5]);

        for (int i = 0; i < list.size(); i++)
            newList.add(new int [5][5]);

        for (int z = 0; z < list.size(); z++) {
            int [][]matrixPrev = null;
            int [][]matrix = list.get(z);
            int [][]newMatrix = newList.get(z);
            int [][]matrixNext = null;

            if (z > 0)
                matrixPrev = list.get(z - 1);

            if (z < list.size() - 1)
                matrixNext = list.get(z + 1);

            tickOuterCorners(matrixPrev, matrix, newMatrix);
            tickOuterLines(matrix, newMatrix, matrixPrev);
            tickInnerCorners(matrix, newMatrix);
            tickRemainingInner(matrix, newMatrix, matrixNext);
        }

        list = newList;
    }

    public void run() {
       for (int i = 0; i < 200; i++)
           tick();

        for (int z = 0; z < list.size(); z++) {
            int [][]matrix = list.get(z);
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    result += matrix[i][j];
                }
            }
        }
    }

    public long getResult() {
        return result;
    }
}
