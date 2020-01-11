package com.celecavac.advent2019;

import java.util.HashSet;
import java.util.LinkedList;

public class Maze {
    protected long result;
    protected char [][]matrix;
    protected int height, width;

    protected Weights weights = new Weights();
    protected LinkedList<Label> labels = new LinkedList<>();

    public Maze (String input) {
        String []lines = input.split("\\n");

        height = lines.length;
        width = lines[0].length();
        matrix = new char[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j] = lines[i].charAt(j);
            }
        }
    }

    public void run () {
        collectLabels();
        activatePortals();
        fillWeightMatrix();
        dijkstra();
    }

    private boolean isChar(char c) {
        return c >= 'A' && c <= 'Z';
    }

    protected void collectLabels() {
        char c1, c2;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                boolean outer = i == 2 ||
                        j == 2 ||
                        i + 3 == height ||
                        j + 3 == width;

                if (matrix[i][j] == '.') {
                    c1 = matrix[i][j + 1];
                    c2 = matrix[i][j + 2];
                    if (isChar(c1) && isChar(c2))
                        labels.add(new Label("" + c1 + c2, i, j, outer));

                    c1 = matrix[i][j - 2];
                    c2 = matrix[i][j - 1];
                    if (isChar(c1) && isChar(c2))
                        labels.add(new Label("" + c1 + c2, i, j, outer));

                    c1 = matrix[i + 1][j];
                    c2 = matrix[i + 2][j];
                    if (isChar(c1) && isChar(c2))
                        labels.add(new Label("" + c1 + c2, i, j, outer));

                    c1 = matrix[i - 2][j];
                    c2 = matrix[i - 1][j];
                    if (isChar(c1) && isChar(c2))
                        labels.add(new Label("" + c1 + c2, i, j, outer));
                }
            }
        }
    }

    protected void fillWeightMatrix() {
        int size = height * width;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int i1 = i / width;
                int j1 = i % width;

                int i2 = j / width;
                int j2 = j % width;

                int distance = Math.abs(i1 - i2) + Math.abs(j1 - j2);

                if (distance == 1 && matrix[i1][j1] == '.' && matrix[i2][j2] == '.') {
                    weights.put(new MyPoint(i, j), 1);
                }
            }
        }
    }

    protected void activatePortals() {
        for (int i = 0; i < labels.size(); i++) {
            for (int j = 0; j < labels.size(); j++) {
                if (i == j)
                    continue;

                Label l1 = labels.get(i);
                Label l2 = labels.get(j);

                int weightIndex1 = l1.getI() * width + l1.getJ();
                int weightIndex2 = l2.getI() * width + l2.getJ();

                if (l1.getLabel().equals(l2.getLabel()))
                    weights.put(new MyPoint(weightIndex1, weightIndex2), 1);
            }
        }
    }

    protected void dijkstra() {
        HashSet<Integer> processedIndexes = new HashSet<Integer>();
        int size = height * width;
        int []d = new int[size];
        int []t = new int[size];

        int startIndex = -1;
        int targetIndex = -1;

        /* Find start and target array indexes */
        for (Label label: labels) {
            if (label.getLabel().equals("AA"))
                startIndex = label.getI() * width + label.getJ();

            if (label.getLabel().equals("ZZ"))
                targetIndex = label.getI() * width + label.getJ();

            if (startIndex != -1 && targetIndex != -1)
                break;
        }

        /* Initialize data for shortest path search */
        processedIndexes.add(startIndex);

        for (int i = 0; i < size; i++) {
            d[i] = weights.get(new MyPoint(startIndex, i));
            if (d[i] != Integer.MAX_VALUE) {
                t[i] = startIndex;
            } else {
                t[i] = -1;
            }
        }

        mainLoop: for (int i = 0; i < size - 1; i++) {
            /* Find next minimal distance */
            int minDistance = Integer.MAX_VALUE;
            int minDistanceIndex = -1;
            for (int j = 0; j < size; j++) {
                if (processedIndexes.contains(j))
                    continue;

                if (d[j] < minDistance) {
                    minDistance = d[j];
                    minDistanceIndex = j;
                }
            }
            if (minDistanceIndex == -1)
                break mainLoop;

            processedIndexes.add(minDistanceIndex);

            /* Relax the distance */
            for (int j = 0; j < size; j++) {
                if (processedIndexes.contains(j))
                    continue;

                if (j == minDistanceIndex)
                    continue;

                int relaxedDistance = Integer.MAX_VALUE;
                if (d[minDistanceIndex] != Integer.MAX_VALUE &&
                        weights.get(new MyPoint(minDistanceIndex, j)) != Integer.MAX_VALUE) {
                    relaxedDistance = d[minDistanceIndex] + weights.get(new MyPoint(minDistanceIndex, j));
                }

                if (relaxedDistance < d[j]) {
                    d[j] = relaxedDistance;
                    t[j] = minDistanceIndex;
                }
            }
        }

        result = d[targetIndex];
    }

    public long getResult() {
        return result;
    }
}
