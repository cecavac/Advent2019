package com.celecavac.advent2019;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

public class Maze2 extends Maze {
    private static final int ALLOWED_DEPTH = 30;
    private LinkedList<Integer> labelIndexes = new LinkedList<>();
    private LinkedList<Integer[]> labelDistances = new LinkedList<>();
    private int extra = 0;

    public Maze2 (String input) {
        super(input);
    }

    @Override
    public void run() {
        collectLabels();
        collectLabelIndexes();
        fillWeightMatrix();

        for (int i = 0; i < labelIndexes.size(); i++) {
            labelDistances.add(dijkstra(labelIndexes.get(i), height * width));
        }

        activatePortals();

        int start = -1;
        int target = -1;
        for (int i = 0; i < labelIndexes.size(); i++) {
            if (labels.get(i).getLabel().equals("AA"))
                start = labelIndexes.get(i);

            if (labels.get(i).getLabel().equals("ZZ"))
                target = labelIndexes.get(i);
        }

        Integer []d = dijkstra(start, height * width * ALLOWED_DEPTH);
        result = d[target];
    }

    protected void fillWeightMatrix() {
        int size = height * width;

        for (int i = 0; i < size; i++) {
            int i1 = i / width;
            int j1 = i % width;

            for (int z = 0; z < 4; z++) {
                int i2 = i1;
                int j2 = j1;

                switch (z) {
                    case 0:
                        i2--;
                        break;
                    case 1:
                        i2++;
                        break;
                    case 2:
                        j2--;
                        break;
                    case 3:
                        j2++;
                        break;
                }

                if (i2 < 0 || i2 >= height || j2 < 0 || j2 >= width)
                    continue;


                int j = i2 * width + j2;
                int distance = Math.abs(i1 - i2) + Math.abs(j1 - j2);
                if (distance == 1 && matrix[i1][j1] == '.' && matrix[i2][j2] == '.') {
                    weights.put(new MyPoint(i, j), 1);
                }
            }
        }
    }

    protected void activatePortals() {
        int size = height * width;

        for (int i = 0; i < labels.size(); i++) {
            for (int j = 0; j < labels.size(); j++) {
                Label l1 = labels.get(i);
                Label l2 = labels.get(j);

                if (i == j ||
                        l1.getLabel().equals("AA") ||
                        l1.getLabel().equals("ZZ") ||
                        l2.getLabel().equals("AA") ||
                        l2.getLabel().equals("ZZ"))
                    continue;

                int weightIndex1 = l1.getI() * width + l1.getJ();
                int weightIndex2 = l2.getI() * width + l2.getJ() + size;

                if (l1.getLabel().equals(l2.getLabel()) && l1.isInner() && l2.isOuter()) {
                    weights.put(new MyPoint(weightIndex1, weightIndex2), 1);
                    weights.put(new MyPoint(weightIndex2, weightIndex1), 1);
                }
            }
        }

        activatePortals(ALLOWED_DEPTH);
    }

    protected void activatePortals(int depth) {
        if (--depth == 0)
            return;

        int realDepth = ALLOWED_DEPTH - depth;
        int size = height * width;

        for (int i = 0; i < labels.size(); i++) {
            for (int j = 0; j < labels.size(); j++) {
                Label l1 = labels.get(i);
                Label l2 = labels.get(j);

                if (i == j ||
                        l1.getLabel().equals("AA") ||
                        l1.getLabel().equals("ZZ") ||
                        l2.getLabel().equals("AA") ||
                        l2.getLabel().equals("ZZ"))
                    continue;

                int weightIndex1 = l1.getI() * width + l1.getJ() + size * realDepth;
                int weightIndex2 = l2.getI() * width + l2.getJ() + size * (realDepth + 1);

                if (l1.getLabel().equals(l2.getLabel()) && l1.isInner() && l2.isOuter()) {
                    weights.put(new MyPoint(weightIndex1, weightIndex2), 1);
                    weights.put(new MyPoint(weightIndex2, weightIndex1), 1);
                }
            }
        }

        for (int i = 0; i < labels.size(); i++) {
            for (int j = 0; j < labels.size(); j++) {
                Label l1 = labels.get(i);
                Label l2 = labels.get(j);

                if (i == j ||
                        l1.getLabel().equals("AA") ||
                        l1.getLabel().equals("ZZ") ||
                        l2.getLabel().equals("AA") ||
                        l2.getLabel().equals("ZZ"))
                    continue;

                int weightIndex1 = l1.getI() * width + l1.getJ() + size * realDepth;
                int weightIndex2 = l2.getI() * width + l2.getJ() + size * realDepth;

                weights.put(new MyPoint(weightIndex1, weightIndex2),
                        labelDistances.get(i)[l2.getI() * width + l2.getJ()]);
            }
        }

        activatePortals(depth);
    }

    protected void collectLabelIndexes () {
        for (Label label: labels) {
            labelIndexes.add(label.getI() * width + label.getJ());
        }
    }

    protected Integer[] dijkstra(int startIndex, int size) {
        HashSet<Integer> processedIndexes = new HashSet<Integer>();
        Integer []d = new Integer[size];
        Integer []t = new Integer[size];

        /* Initialize data for shortest path search */
        processedIndexes.add(startIndex);

        for (int i = 0; i < size; i++) {
            d[i] = Integer.MAX_VALUE;
            t[i] = -1;
        }

        for (Map.Entry<MyPoint, Integer> entry: weights.set()) {
            MyPoint point = entry.getKey();

            if (point.i != startIndex)
                continue;

            d[point.j] = weights.get(new MyPoint(startIndex, point.j));
            if (d[point.j] != Integer.MAX_VALUE) {
                t[point.j] = startIndex;
            } else {
                t[point.j] = -1;
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

        return d;
    }
}
