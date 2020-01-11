package com.celecavac.advent2019;

import android.util.Log;

public class Quantity {
    public int x, y, z;

    public Quantity(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void add(Quantity q) {
        x += q.x;
        y += q.y;
        z += q.z;
    }

    public int getEnergy() {
        return Math.abs(x) + Math.abs(y) + Math.abs(z);
    }

    public void print() {
        Log.e("Quantity", "<x=" + x + ", y=" + y + ", z=" + z + ">");
    }

    public Quantity calculateGravityStep(Quantity q) {
        int x, y ,z;

        if (this.x > q.x) {
            x = -1;
        } else if (this.x < q.x) {
            x = 1;
        } else /* if (this.x == q.x) */ {
            x = 0;
        }

        if (this.y > q.y) {
            y = -1;
        } else if (this.y < q.y) {
            y = 1;
        } else /* if (this.y == q.y) */ {
            y = 0;
        }

        if (this.z > q.z) {
            z = -1;
        } else if (this.z < q.z) {
            z = 1;
        } else /* if (this.z == q.z) */ {
            z = 0;
        }

        return new Quantity(x, y, z);
    }

    public void invert() {
        x = -x;
        y = -y;
        z = -z;
    }

    public boolean equals(Quantity q) {
        return x == q.x && y == q.y && z == q.z;
    }
}
