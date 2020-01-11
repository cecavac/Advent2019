package com.celecavac.advent2019;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Wire {
    HashSet<MyPoint> points = new HashSet<MyPoint>();
    ArrayList<MyPoint> pointsList = new ArrayList<MyPoint>();

    private void addnewPoint(int x, int y, int sequence) {
        MyPoint p = new MyPoint(x, y, sequence);
        points.add(p);
        pointsList.add(p);
        sequence++;
    }

    public Wire(String input) {
        String elements[] = input.split(",");
        int x = 0, y = 0;
        int sequence = 0;
        for (String element : elements) {
            int targetX = x, targetY = y;

            char direction = element.charAt(0);
            String distanceString = element.substring(1);
            int distance = Integer.parseInt(distanceString);

            switch (direction) {
                case 'U':
                    targetY += distance;
                    for (; y < targetY; y++)
                        addnewPoint(x, y, sequence++);

                    break;
                case 'D':
                    targetY -= distance;
                    for (; y > targetY; y--)
                        addnewPoint(x, y, sequence++);

                    break;
                case 'L':
                    targetX -= distance;
                    for (; x > targetX; x--)
                        addnewPoint(x, y, sequence++);

                    break;
                case 'R':
                    targetX += distance;
                    for (; x < targetX; x++)
                        addnewPoint(x, y, sequence++);

                    break;
            }

            // Add the end point
            addnewPoint(x, y, sequence);
        }
    }

    public List<MyPoint> getIntersections(Wire wire) {
        List<MyPoint> intersections = new LinkedList<MyPoint>();

        for (MyPoint point : points) {
            if (wire.points.contains(point)) {
                intersections.add(point);
            }
        }

        return intersections;
    }

    public int getSequenceByPoint(MyPoint point) {
        for (MyPoint p : pointsList) {
            if (point.equals(p))
                return p.getSequence();
        }

        return Integer.MAX_VALUE;
    }
}
