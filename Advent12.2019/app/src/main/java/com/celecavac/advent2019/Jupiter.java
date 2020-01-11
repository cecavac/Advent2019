package com.celecavac.advent2019;

import android.util.Log;

import java.util.ArrayList;

public class Jupiter {
    private int result;
    private long result2;
    private Moon []moons = new Moon[4];
    private Moon []snapshots = new Moon[4];


    public Jupiter(String input) {
        int i = 0;
        String []elements = input.split("\\n");

        for (String element: elements) {
            element = element.substring(1, element.length() - 1);
            String [] values = element.split(",");
            int x = Integer.parseInt(values[0].substring(2));
            int y = Integer.parseInt(values[1].substring(3));
            int z = Integer.parseInt(values[2].substring(3));

            moons[i] = (new Moon(x, y, z));
            snapshots[i++] = (new Moon(x, y, z));
        }
    }

    private void applyGravity() {
        for (int i = 0; i < moons.length; i++) {
            for (int j = i + 1; j < moons.length; j++) {
                Moon m1 = moons[i];
                Moon m2 = moons[j];
                m1.applyGravity(m2);
            }
        }
    }

    private void tick(long tick, boolean print) {
        for (int i = 0; i < moons.length; i++) {
            Moon moon = moons[i];
            moon.tick();
            if (print)
                moon.print();
        }

        if (print)
            Log.e("=======================", "tick: " + tick);
    }

    private void calculateMoonEnergy() {
        int energy = 0;

        for (int i = 0; i < moons.length; i++) {
            Moon moon = moons[i];
            energy += moon.getEnergy();
        }

        result = energy;
    }

    public void simulate(long ticks) {
        for (long i = 0; i < ticks; i++) {
            applyGravity();
            tick(i + 2, true);
        }

        calculateMoonEnergy();
    }

    /* Source: https://www.baeldung.com/java-least-common-multiple */
    public static long lcm(long number1, long number2) {
        if (number1 == 0 || number2 == 0) {
            return 0;
        }
        long absNumber1 = Math.abs(number1);
        long absNumber2 = Math.abs(number2);
        long absHigherNumber = Math.max(absNumber1, absNumber2);
        long absLowerNumber = Math.min(absNumber1, absNumber2);
        long lcm = absHigherNumber;

        while (lcm % absLowerNumber != 0) {
            lcm += absHigherNumber;
        }

        return lcm;
    }

    public void findRepeating() {
        long ticks = 0;
        long t1 = 0, t2 = 0, t3 = 0;
        boolean f1 = false, f2 = false, f3 = false;

        while(true) {
            applyGravity();
            tick(ticks++ + 2, false);

            if (!f1 &&
                    moons[0].position.x == snapshots[0].position.x &&
                    moons[1].position.x == snapshots[1].position.x &&
                    moons[2].position.x == snapshots[2].position.x &&
                    moons[3].position.x == snapshots[3].position.x &&
                    moons[0].velocity.x == snapshots[0].velocity.x &&
                    moons[1].velocity.x == snapshots[1].velocity.x &&
                    moons[2].velocity.x == snapshots[2].velocity.x &&
                    moons[3].velocity.x == snapshots[3].velocity.x) {
                f1 = true;
                t1 = ticks;
                Log.e("Repeat", "x: " + ticks);
            }

            if (!f2 &&
                    moons[0].position.y == snapshots[0].position.y &&
                    moons[1].position.y == snapshots[1].position.y &&
                    moons[2].position.y == snapshots[2].position.y &&
                    moons[3].position.y == snapshots[3].position.y &&
                    moons[0].velocity.y == snapshots[0].velocity.y &&
                    moons[1].velocity.y == snapshots[1].velocity.y &&
                    moons[2].velocity.y == snapshots[2].velocity.y &&
                    moons[3].velocity.y == snapshots[3].velocity.y) {
                f2 = true;
                t2 = ticks;
                Log.e("Repeat", "y: " + ticks);
            }

            if (!f3 &&
                    moons[0].position.z == snapshots[0].position.z &&
                    moons[1].position.z == snapshots[1].position.z &&
                    moons[2].position.z == snapshots[2].position.z &&
                    moons[3].position.z == snapshots[3].position.z &&
                    moons[0].velocity.z == snapshots[0].velocity.z &&
                    moons[1].velocity.z == snapshots[1].velocity.z &&
                    moons[2].velocity.z == snapshots[2].velocity.z &&
                    moons[3].velocity.z == snapshots[3].velocity.z) {
                f3 = true;
                t3 = ticks;
                Log.e("Repeat", "z: " + ticks);
            }

            if (f1 && f2 && f3)
                break;
        }

        result2 = lcm(t1, lcm(t2, t3));
    }

    public int getResult() {
        return result;
    }

    public long getResult2() {
        return result2;
    }
}
