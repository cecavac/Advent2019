package com.celecavac.advent2019;

public class Dealer {
    private Deck deck;
    private long result;

    public Dealer(int cards, String input, int target) {
        deck = new Deck(cards);

        String []lines = input.split("\n");
        for (String line: lines) {
            if (line.substring(0,4).equals("cut ")) {
                int n = Integer.parseInt(line.substring(4));
                deck.cut(n);
            } else if (line.equals("deal into new stack")) {
                deck.newStack();
            } else {
                int n = Integer.parseInt(line.substring(20));
                deck.increment(n);
            }
        }

        result = deck.getIndex(target);
    }

    public long getResult() {
        return result;
    }
}
