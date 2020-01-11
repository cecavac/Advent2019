package com.celecavac.advent2019;

public class Element {
    private long quantity;
    private String particle;

    public Element(String data) {
        String []dataArray = data.split(" ");

        this.quantity = Long.parseLong(dataArray[0]);
        this.particle = dataArray[1];
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getQuantity() {
        return quantity;
    }

    public String getParticle() {
        return particle;
    }

    public boolean equals(Element element) {
        return particle.equals(element.particle);
    }

    public String toString() {
        return quantity + " " + particle;
    }
}
