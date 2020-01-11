package com.celecavac.advent2019;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class FullCircle {
    private Set<Double> []sets = new Set[4];
    private ArrayList<Double> []lists = new ArrayList[4];

    private int listIndex = 0, elementIndex = 0;

    FullCircle () {
        for (int i = 0; i < 4; i++)
            sets[i] = new HashSet<Double>();
    }

    public static boolean IsQA(int oi, int oj, int i, int j) {
        return oi > i && oj <= j;
    }

    public static boolean IsQB(int oi, int oj, int i, int j) {
        return oi <= i && oj < j;
    }

    public static boolean IsQC(int oi, int oj, int i, int j) {
        return oi < i && oj >= j;
    }

    public static boolean IsQD(int oi, int oj, int i, int j) {
        return oi >= i && oj > j;
    }

    public boolean addAngle(int oi, int oj, int i, int j, double angle) {
        if (IsQA(oi, oj, i, j)) {
            sets[0].add(angle);
        } else if (IsQB(oi, oj, i, j)) {
            sets[1].add(angle);
        } else if (IsQC(oi, oj, i, j)) {
            sets[2].add(angle);
        } else if (IsQD(oi, oj, i, j)) {
            sets[3].add(angle);
        } else {
            return false;
        }

        return true;
    }

    public int getSize() {
        int size = 0;

        for (int i = 0; i < 4; i++)
            size += sets[i].size();

        return size;
    }

    public void order() {
        for (int i = 0; i < 4; i++) {
            lists[i] = new ArrayList<Double>();

            for (Double d : sets[i])
                lists[i].add(d);

            Collections.sort(lists[i], Collections.reverseOrder());
        }
    }

    public double getCurrentTarget() {
        return lists[listIndex].get(elementIndex);
    }

    public void selectNext() {
        elementIndex++;

        if (elementIndex == lists[listIndex].size()) {
            listIndex = (listIndex + 1) % 4;
            elementIndex = 0;
        }
    }

    public boolean properTargetQuadrant(int oi, int oj, int i, int j) {
        switch (listIndex) {
            case 0:
                return IsQA(oi, oj, i, j);
            case 1:
                return IsQB(oi, oj, i, j);
            case 2:
                return IsQC(oi, oj, i, j);
            case 3:
                return IsQD(oi, oj, i, j);
        }

        return false;
    }
}
