package com.celecavac.advent1;

public class AdjustedFuelCounter extends FuelCounter {
    public AdjustedFuelCounter(String masses) {
        super(masses);
    }

    private static int adjustFuelForFuel(int mass) {
        int fuel = massToFuel(mass);

        if (fuel > 0) {
            fuel = adjustFuelForFuel(fuel);

            return mass + fuel;
        }
        else
        {
            return mass;
        }
    }

    @Override
    protected int moduleMassToFuel(int mass) {
        int fuel = massToFuel(mass);

        return adjustFuelForFuel(fuel);
    }
}
