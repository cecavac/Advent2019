package com.celecavac.advent2019;

import java.util.ArrayList;
import java.util.HashMap;

public class OrbitCounter {
    private int result;
    private int result2;
    private HashMap<String, Planet> map = new HashMap<String, Planet>();
    private ArrayList<Planet> arrayList = new ArrayList<Planet>();

    public OrbitCounter(String input) {
        String []pairs = input.split("\\n");

        Planet com = null;

        for (String pair : pairs) {
            String []planets = pair.split("\\)");

            Planet planet0;
            if (map.containsKey(planets[0])) {
                planet0 = map.get(planets[0]);
            } else {
                planet0 = new Planet(planets[0]);
                map.put(planets[0], planet0);
                arrayList.add(planet0);
            }

            Planet planet1;
            if (map.containsKey(planets[1])) {
                planet1 = map.get(planets[1]);
            } else {
                planet1 = new Planet(planets[1]);
                map.put(planets[1], planet1);
                arrayList.add(planet1);
            }

            planet0.addToOrbit(planet1);

            if (planet0.getName().equals("COM")) {
                com = planet0;
            }
        }

        int min = Integer.MAX_VALUE;

        for (Planet planet : arrayList) {
            int distance1 = planet.getDistance("YOU", 0);
            int distance2 = planet.getDistance("SAN", 0);
            int distance = distance1 + distance2;

            if (distance1 > 0 && distance2 > 0 && distance < min)
                min = distance;
        }

        result = com.orbitingCount(0);
        result2 = min;
    }

    public String getResult() {
        return Integer.toString(result);
    }

    public String getResult2() {
        return Integer.toString(result2);
    }
}
