package com.celecavac.advent1;

public class FuelCounter {
    protected int totalFuel = 0;

    public FuelCounter(String masses) {
        String split[] = masses.split("\\n");

        for (String input : split) {
            int mass = Integer.parseInt(input);

            totalFuel += moduleMassToFuel(mass);
        }
    }

    protected static int massToFuel(int mass) {
        return mass / 3 - 2;
    }

    protected int moduleMassToFuel(int mass) {
        return massToFuel(mass);
    }

    public String getTotalFuel() {
        return Integer.toString(totalFuel);
    }
}
