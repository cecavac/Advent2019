package com.celecavac.advent2019;

public class Moon {
    public Quantity position, velocity;

    public Moon (int x, int y, int z) {
        position = new Quantity(x, y, z);
        velocity = new Quantity(0, 0, 0);
    }

    private int getPotentialEnergy() {
        return position.getEnergy();
    }

    public int getKineticEnergy() {
        return velocity.getEnergy();
    }

    public int getEnergy() {
        return getPotentialEnergy() * getKineticEnergy();
    }

    public void applyGravity(Moon moon) {
        Quantity gravityStep = position.calculateGravityStep(moon.position);
        velocity.add(gravityStep);
        gravityStep.invert();
        moon.velocity.add(gravityStep);
    }

    public void tick() {
        position.add(velocity);
    }

    public void print() {
        position.print();;
        velocity.print();
    }

    public boolean equals(Moon m) {
        return position.equals(m.position) && velocity.equals(m.velocity);
    }
}
