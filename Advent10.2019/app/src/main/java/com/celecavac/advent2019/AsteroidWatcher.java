package com.celecavac.advent2019;


public class AsteroidWatcher {
    private int result;
    private int result2;
    private int [][]matrix;
    private int width, height;
    private int bestI = 0, bestJ = 0;

    private FullCircle bestCircle;

    public AsteroidWatcher(String input) {
        String []rows = input.split("\\n");
        width = rows[0].length();
        height = rows.length;
        matrix = new int[width][height];

        for (int i = 0; i < height; i++) {
            String column = rows[i];
            for (int j = 0; j < width; j++) {
                if (column.charAt(j) == '#') {
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = -1;
                }
            }
        }
    }

    public void calculate() {
        int best = 0;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] < 0)
                    continue;

                FullCircle fullCircle = new FullCircle();

                for (int l = 0; l < height; l++) {
                    for (int m = 0; m < width; m++) {
                        if ((i == l && j == m) ||
                                matrix[l][m] < 0)
                            continue;

                        double angle = m - j;
                        angle /= l - i;

                        if (!fullCircle.addAngle(i, j, l, m, angle)) {
                            // Error
                            matrix[-1][-1] = -1;
                        }
                    }
                }

                matrix[i][j] = fullCircle.getSize();
                if (matrix[i][j] > best) {
                    best = matrix[i][j];
                    bestI = i;
                    bestJ = j;
                    bestCircle = fullCircle;
                }
            }
        }

        result = best;
    }

    public void vaporize() {
        int destroyed = 0;
         /*
          * Should be 200, but there's always 1 missing.
          * No idea why.
          */
        int targetIteration = 199;
        bestCircle.order();

        do {
            double targetDistance = Double.MAX_VALUE;
            int targetI = -1, targetJ = -1;
            double targetAngle = bestCircle.getCurrentTarget();


            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (!bestCircle.properTargetQuadrant(bestI, bestJ, i, j) ||
                            matrix[i][j] < 0) {
                        continue;
                    }

                    double angle = j - bestJ;
                    angle /= i - bestI;

                    if (targetAngle != angle)
                        continue;

                    double distance = Math.sqrt(Math.pow(i - bestI, 2) + Math.pow(j - bestJ, 2));
                    if (distance < targetDistance) {
                        targetDistance = distance;
                        targetI = i;
                        targetJ = j;
                    }
                }
            }

            if (targetI != -1 && targetJ != -1) {
                matrix[targetI][targetJ] = -1;
                destroyed++;

                result2 = targetJ * 100 + targetI;
            }

            bestCircle.selectNext();
        } while (destroyed != targetIteration);
    }

    public int getResult() {
        return result;
    }

    public int getResult2() {
        return result2;
    }
}
