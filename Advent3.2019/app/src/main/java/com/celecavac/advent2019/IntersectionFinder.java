package com.celecavac.advent2019;

import java.util.List;

public class IntersectionFinder {
    private int result = 0;
    private int result2 = 0;

    public IntersectionFinder(String input) {
        String lines[] = input.split("\\n");

        Wire wire0 = new Wire(lines[0]);
        Wire wire1 = new Wire(lines[1]);

        List<MyPoint> intersections = wire0.getIntersections(wire1);

        int minDistance = Integer.MAX_VALUE;
        for (MyPoint point : intersections) {
            int distance = Math.abs(point.getX()) + Math.abs(point.getY());

            if (distance != 0 && distance < minDistance)
                minDistance = distance;
        }
        result = minDistance;

        int minSequence = Integer.MAX_VALUE;
        for (MyPoint point : intersections) {
            int seq1 = wire0.getSequenceByPoint(point);
            int seq2 = wire1.getSequenceByPoint(point);

            int sequence = seq1 + seq2;

            if (sequence != 0 && sequence < minSequence)
                minSequence = sequence;
        }
        result2 = minSequence;
    }

    public String getResult() {
        return Integer.toString(result);
    }

    public String getResult2() {
        return Integer.toString(result2);
    }
}
