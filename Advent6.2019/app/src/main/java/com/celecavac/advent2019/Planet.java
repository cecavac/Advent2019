package com.celecavac.advent2019;

import java.util.ArrayList;

public class Planet {
    private ArrayList<Planet> planetsInTheOrbit = new ArrayList<Planet>();
    private String name;

    public Planet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addToOrbit(Planet planet) {
        planetsInTheOrbit.add(planet);
    }

    public int orbitingCount(int orbitLevel) {
        int orbitingCount;
        int indirectOrbits;

        if (orbitLevel == 0) {
            orbitingCount = 0;
            indirectOrbits = 0;
        } else {
            orbitingCount = 1;
            indirectOrbits = orbitLevel - 1;
        }

        for (Planet planet : planetsInTheOrbit) {
            orbitingCount += planet.orbitingCount(orbitLevel + 1);
        }


        return orbitingCount + indirectOrbits;
    }

    public int getDistance(String name, int orbitLevel) {
        for (Planet planet : planetsInTheOrbit) {
            if (planet.name.equals(name))
                return orbitLevel;

            int distance = planet.getDistance(name, orbitLevel + 1);

            if (distance > 0)
                return distance;
        }

        return -1;
    }
}
