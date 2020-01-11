package com.celecavac.advent2019;

import androidx.annotation.NonNull;

public class Label {
    private String label;
    private int i, j;
    private boolean outer;

    public Label(String label, int i, int j, boolean outer) {
        this.label = label;
        this.i = i;
        this.j = j;
        this.outer = outer;
    }

    public String getLabel() {
        return label;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public boolean isOuter() {
        return outer;
    }

    public boolean isInner() {
        return !outer;
    }

    @NonNull
    @Override
    public String toString() {
        return label + " " + outer + " " + i + "," + j;
    }
}
