package com.celecavac.advent2019;

import android.util.Log;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Robot {
    private int result = -1;
    private int result2;
    private HashMap<MyPoint, Long> map = new HashMap<MyPoint, Long>();
    private Direction direction;
    private int positionI = 0, positionJ = 0;
    private MyPoint firstWall;

    private MyPoint startingPoint = new MyPoint(positionI, positionJ);
    private MyPoint oxygenSystem;

    private BlockingQueue inputQ = new ArrayBlockingQueue<Long>(10000);
    private BlockingQueue outputQ = new ArrayBlockingQueue<Long>(10000);
    private Executer executer = new Executer(Inputs.Input1, inputQ, outputQ);

    public void run() throws InterruptedException {
        executer.start();

        explore();
        print();

        shortestPath();
    }

    private void explore() throws InterruptedException {
        firstWall = null;
        direction = Direction.UP;
        findWalls();

        firstWall = null;
        direction = Direction.DOWN;
        findWalls();

        firstWall = null;
        direction = Direction.LEFT;
        findWalls();

        firstWall = null;
        direction = Direction.RIGHT;
        findWalls();
    }

    private void findWalls() throws InterruptedException {
        boolean noWallsHit = true;
        boolean allWallsFound = false;

        do {
            moveCommand();

            long output = (long) outputQ.take();
            switch ((int) output) {
                case 0:
                    noWallsHit = false;
                    allWallsFound = wallDetected();
                    changeDirectionAfterWallHit();
                    break;
                case 1:
                    move();
                    changeDirectionAfterMove(noWallsHit);
                    map.put(new MyPoint(positionI, positionJ), 1L);
                    break;
                case 2:
                    move();
                    changeDirectionAfterMove(noWallsHit);
                    oxygenSystem = new MyPoint(positionI, positionJ);
                    map.put(oxygenSystem, 2L);
                    break;
            }

            //print();
            //Log.e("###########", "NEW ITERATION" + positionI + " " + positionJ);
        } while(!allWallsFound);

    }

    private void changeDirectionAfterMove(boolean noWallsHit) {
        if (noWallsHit)
            return;

        switch (direction) {
            case LEFT:
                direction = Direction.DOWN;
                break;
            case RIGHT:
                direction = Direction.UP;
                break;
            case UP:
                direction = Direction.LEFT;
                break;
            case DOWN:
                direction = Direction.RIGHT;
                break;
        }
    }

    private void changeDirectionAfterWallHit() {
        switch (direction) {
            case LEFT:
                direction = Direction.UP;
                break;
            case RIGHT:
                direction = Direction.DOWN;
                break;
            case UP:
                direction = Direction.RIGHT;
                break;
            case DOWN:
                direction = Direction.LEFT;
                break;
        }
    }

    private void moveCommand() throws InterruptedException {
        switch (direction) {
            case LEFT:
                inputQ.put(3L);
                break;
            case RIGHT:
                inputQ.put(4L);
                break;
            case UP:
                inputQ.put(1L);
                break;
            case DOWN:
                inputQ.put(2L);
                break;
        }
    }

    private boolean wallDetected() {
        MyPoint point = null;

        switch (direction) {
            case LEFT:
                point = new MyPoint(positionI, positionJ - 1);
                map.put(point, 0L);
                break;
            case RIGHT:
                point = new MyPoint(positionI, positionJ + 1);
                map.put(point, 0L);
                break;
            case UP:
                point = new MyPoint(positionI - 1, positionJ);
                map.put(point, 0L);
                break;
            case DOWN:
                point = new MyPoint(positionI + 1, positionJ);
                map.put(point, 0L);
                break;
        }

        if (firstWall == null) {
            firstWall = point;
            return  false;
        }

        if (firstWall.equals(point)) {
            return true;
        } else {
            return false;
        }
    }

    private void move() {
        switch (direction) {
            case LEFT:
                positionJ--;
                break;
            case RIGHT:
                positionJ++;
                break;
            case UP:
                positionI--;
                break;
            case DOWN:
                positionI++;
                break;
        }
    }

    public int getResult() {
        return result;
    }

    public int getResult2() {
        return result2;
    }

    public Long getElement(int i, int j) {
        MyPoint location = new MyPoint(i, j);

        if (!map.containsKey(location)) {
            return -1L;
        } else {
            return map.get(location);
        }
    }

    public void print() {
        int minI = Integer.MAX_VALUE;
        int minJ = Integer.MAX_VALUE;
        int maxI = Integer.MIN_VALUE;
        int maxJ = Integer.MIN_VALUE;

        for (Map.Entry<MyPoint, Long> entry : map.entrySet()) {
            if (entry.getValue() == 0L) {
                int i = entry.getKey().getI();
                int j = entry.getKey().getJ();

                minI = Math.min(minI, i);
                minJ = Math.min(minJ, j);
                maxI = Math.max(maxI, i);
                maxJ = Math.max(maxJ, j);
            }
        }

        for (int i = minI - 1; i <= maxI + 1; i++) {
            String row = "";
            for (int j = minJ - 1; j <= maxJ + 1; j++) {
                long element = getElement(i, j);
                if (i == positionI && j == positionJ) {
                    row += "D";
                } else if (element == -1L) {
                     row += " ";
                } else if (element == 0L) {
                    row += "#";
                } else if (element == 1L) {
                    row += ".";
                } else if (element == 2L) {
                    row += "X";
                } else {
                    row += "*";
                }
            }
            Log.e("Maze", row + "    " + i);
        }
    }

    private boolean isReachable(Long value) {
        return value == 1L || value == 2L;
    }

    private MyPoint[] getReachableArray() {
        int index = 0;
        int reachableSpots = 0;
        MyPoint []reachableArray;

        for (Map.Entry<MyPoint, Long> entry : map.entrySet())
            if (isReachable(entry.getValue()))
                reachableSpots++;

        reachableArray = new MyPoint[reachableSpots];
        for (Map.Entry<MyPoint, Long> entry : map.entrySet())
            if (isReachable(entry.getValue()))
                reachableArray[index++] = entry.getKey();

        return reachableArray;
    }

    private int[][] getWeightMatrix(MyPoint []reachableArray) {
        int size = reachableArray.length;
        int [][]weights = new int[size][size];

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                int distance = reachableArray[i].distance(reachableArray[j]);
                if (distance == 1) {
                    weights[i][j] = distance;
                } else {
                    weights[i][j] = Integer.MAX_VALUE;
                }
            }

        return weights;
    }

    private void dijkstra(MyPoint []reachableArray, int [][]weights,
                          MyPoint start, MyPoint target) {
        HashSet<Integer> processedIndexes = new HashSet<Integer>();
        int startingPointIndex = -1, oxygenSystemIndex = -1;
        int size = reachableArray.length;
        int []d = new int[size];
        int []t = new int[size];

        /* Find start and target array indexes */
        for (int i = 0; i < reachableArray.length; i++) {
            if (reachableArray[i].equals(start))
                startingPointIndex = i;

            if (reachableArray[i].equals(oxygenSystem))
                oxygenSystemIndex = i;

            if (startingPointIndex != -1 && oxygenSystemIndex != -1)
                break;
        }

        /* Initialize data for shortest path search */
        processedIndexes.add(startingPointIndex);

        for (int i = 0; i < size; i++) {
            d[i] = weights[startingPointIndex][i];
            if (d[i] != Integer.MAX_VALUE) {
                t[i] = startingPointIndex;
            } else {
                t[i] = -1;
            }
        }

        for (int i = 0; i < size - 1; i++) {
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
                throw new NullPointerException();
            processedIndexes.add(minDistanceIndex);

            /* Relax the the distance */
            for (int j = 0; j < size; j++) {
                if (processedIndexes.contains(j))
                    continue;

                if (j == minDistanceIndex)
                    continue;

                int relaxedDistance = Integer.MAX_VALUE;
                if (d[minDistanceIndex] != Integer.MAX_VALUE &&
                    weights[minDistanceIndex][j] != Integer.MAX_VALUE) {
                    relaxedDistance = d[minDistanceIndex] + weights[minDistanceIndex][j];
                }

                if (relaxedDistance < d[j]) {
                    d[j] = relaxedDistance;
                    t[j] = minDistanceIndex;
                }
            }
        }

        if (target != null) {
            result = d[oxygenSystemIndex];
        } else {
            int maxDistance = -1;

            for (int i = 0; i < size; i++) {
                if (d[i] != Integer.MAX_VALUE &&
                        d[i] > maxDistance) {
                    maxDistance = d[i];
                }
            }

            result2 = maxDistance;
        }
    }

    private void shortestPath() {
        MyPoint []reachableArray = getReachableArray();
        int [][]weights = getWeightMatrix(reachableArray);
        dijkstra(reachableArray, weights, startingPoint, oxygenSystem);
        dijkstra(reachableArray, weights, oxygenSystem, null);
    }
}
