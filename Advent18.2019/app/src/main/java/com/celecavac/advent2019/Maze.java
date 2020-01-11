package com.celecavac.advent2019;

import android.util.Log;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

public class Maze {
    //private int collectedKeys = 0;
    private static final int ALPHABET_SIZE = 'Z' - 'A' + 1;
    private RoutingData []keyRoutingData = new RoutingData[ALPHABET_SIZE];
    private int []keyDependencies = new int[ALPHABET_SIZE];

    private HashMap<Character, Integer> keyOffsets = new HashMap<>();

    protected int result = 0;
    protected char [][]matrix;
    protected int height, width;
    private int positionI, positionJ;

    protected Weights weights;

    public Maze (String input) {
        String []lines = input.split("\\n");

        height = lines.length;
        width = lines[0].length();
        matrix = new char[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                char c = lines[i].charAt(j);
                matrix[i][j] = c;

                if (c == '@') {
                    positionI = i;
                    positionJ = j;
                }

                if (isKey(c))
                    keyOffsets.put(c, i * width + j);

            }
        }
    }

    public void run () {
        fillWeightMatrixUnrestricted();
        for (Map.Entry<Character, Integer> entry: keyOffsets.entrySet()) {
            Character c = entry.getKey();
            Integer value = entry.getValue();

            int keyIndex = keyIndex(c);
            keyRoutingData[keyIndex] = dijkstra(value);

            int startIndex = positionI * width + positionJ;
            keyDependencies[keyIndex] = buildDependency(keyRoutingData[keyIndex], startIndex, value);
        }

        move();
    }

    public void print(Integer collectedKeys) {
        for (int i = 0; i < height; i++) {
            String line = "";
            for (int j = 0; j < width; j++) {
                char c = matrix[i][j];
                if (c == '.' || c == '#') {
                    line += matrix[i][j];
                    continue;
                }

                if (i == positionI && j == positionJ) {
                    line += "@";
                    continue;
                }

                if (c == '@') {
                    line += ".";
                    continue;
                }

                if (isKey(c)) {
                    if (isKeyCollected(c, collectedKeys)) {
                        line += ".";
                    } else {
                        line += c;
                    }
                    continue;
                }

                if (isDoor(c)) {
                    if (isDoorOpened(c, collectedKeys)) {
                        line += ".";
                    } else {
                        line += c;
                    }
                    continue;
                }
            }
            Log.e("#####", line);
        }
        Log.e("######", "========================");
    }

    private void move() {
        LinkedList<Character> keyCandidates = new LinkedList<>();

        fillWeightMatrixRestricted(0);
        int startIndex = positionI * width + positionJ;
        RoutingData routingData = dijkstra(startIndex);

        int bestDistance = Integer.MAX_VALUE;
        int d[] = routingData.getD();

        for (Map.Entry<Character, Integer> entry: keyOffsets.entrySet()) {
            char c = entry.getKey();

            if (isKeyCollected(c, 0) ||
                    !isKeyReachable(c, 0))
                continue;

            keyCandidates.add(c);
        }

        for (Character c: keyCandidates) {
            Integer collectedKeys = new Integer(0);

            int offset = keyOffsets.get(c);
            int distance =  d[offset];
            collectedKeys = collectKey(c, collectedKeys);

            distance += moveFromKeyToKey(collectedKeys, keyOffsets.size() - 1,
                    offset / width, offset % width);
            bestDistance = Math.min(bestDistance, distance);
        }

        result = bestDistance;
    }

    private HashMap<Long, Integer> distCache = new HashMap<>();

    private int moveFromKeyToKey(Integer collectedKeys, int depth, int i, int j) {
        if (depth == 0)
            return 0;

        LinkedList<Character> keyCandidates = new LinkedList<>();
        for (Map.Entry<Character, Integer> entry: keyOffsets.entrySet()) {
            char c = entry.getKey();

            if (isKeyCollected(c, collectedKeys) ||
                    !isKeyReachable(c, collectedKeys))
                continue;

            keyCandidates.add(c);
        }

        char currentKey = matrix[i][j];
        int keyIndex = keyIndex(currentKey);
        RoutingData routingData = keyRoutingData[keyIndex];
        int bestDistance = Integer.MAX_VALUE;
        int d[] = routingData.getD();



        long hash = collectedKeys * 1000 + keyIndex;
        if (distCache.containsKey(hash))
            return distCache.get(hash);

        for (Character c: keyCandidates) {
            Integer collectedKeysCopy = new Integer(collectedKeys);

            int offset = keyOffsets.get(c);
            int distance =  d[offset];
            collectedKeysCopy = collectKey(c, collectedKeysCopy);

            distance += moveFromKeyToKey(collectedKeysCopy, depth - 1,
                    offset / width, offset % width);
            bestDistance = Math.min(bestDistance, distance);
        }

        distCache.put(hash, bestDistance);
        return bestDistance;
    }

    private int buildDependency(RoutingData routingData, int startIndex, int targetIndex) {
        int dependency = 0;
        int []t = routingData.getT();

        int currentIndex = startIndex;

        while (currentIndex != targetIndex) {
            currentIndex = t[currentIndex];
            int i = currentIndex / width;
            int j = currentIndex % width;
            if (isDoor(matrix[i][j])) {
                int doorIndex = doorIndex(matrix[i][j]);
                int keyDoorBit = keyDoorBit(doorIndex);
                dependency |= keyDoorBit;
            }

        }

        return dependency;
    }

    private boolean isDoor(char c) {
        return c >= 'A' && c <= 'Z';
    }

    private boolean isKey(char c) {
        return c >= 'a' && c <= 'z';
    }

    private boolean isKeyReachable(char c, Integer collectedKeys) {
        int keyIndex = keyIndex(c);

        int dependencies = keyDependencies[keyIndex];

        return (dependencies & collectedKeys) == dependencies;
    }

    private int doorIndex(Character c) {
        int charValue = c.charValue();
        charValue -= 'A';

        return charValue;
    }

    private int keyIndex(Character c) {
        int charValue = c.charValue();
        charValue -= 'a';

        return charValue;
    }

    private int keyDoorBit(int index) {
        return  1 << index;
    }

    private int collectKey(char c, Integer collectedKeys) {
        int keyIndex = keyIndex(c);
        int keyDoorBit = keyDoorBit(keyIndex);

        collectedKeys |= keyDoorBit;
        return collectedKeys;
    }

    private boolean isKeyCollected(char c, Integer collectedKeys) {
        int keyIndex = keyIndex(c);
        int keyDoorBit = keyDoorBit(keyIndex);

        return (collectedKeys & keyDoorBit) != 0;
    }

    private boolean isDoorOpened(char c, Integer collectedKeys) {
        int doorIndex = doorIndex(c);
        int keyDoorBit = keyDoorBit(doorIndex);

        return (collectedKeys & keyDoorBit) != 0;
    }



    protected void fillWeightMatrixUnrestricted() {
        weights = new Weights();
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
                if (matrix[i1][j1] != '#' && matrix[i2][j2] != '#') {
                    weights.put(new MyPoint(i, j), 1);
                }
            }
        }
    }

    private boolean isAccessible(char c, Integer collectedKeys) {
        if (c == '.' || c == '@' || isKey(c))
            return true;

        if (c == '#')
            return false;

        if (isDoor(c))
            return isDoorOpened(c, collectedKeys);

        throw new NullPointerException();
    }

    protected void fillWeightMatrixRestricted(Integer collectedKeys) {
        weights = new Weights();
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
                if (isAccessible(matrix[i1][j1], collectedKeys) && isAccessible(matrix[i2][j2], collectedKeys)) {
                    weights.put(new MyPoint(i, j), 1);
                }
            }
        }
    }

    protected RoutingData dijkstra(int startIndex) {
        HashSet<Integer> processedIndexes = new HashSet<Integer>();
        int size = height * width;
        RoutingData routingData = new RoutingData(size);
        int []d = routingData.getD();
        int []t = routingData.getT();

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

        return routingData;
    }

    public int getResult() {
        return result;
    }
}
