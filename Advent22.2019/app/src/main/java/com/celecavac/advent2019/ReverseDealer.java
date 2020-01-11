package com.celecavac.advent2019;

public class ReverseDealer {
    private GiantReverseDeck deck;
    private long result;

    public ReverseDealer(long cards, String input, long target) {
        long z = 0;
        deck = new GiantReverseDeck(cards, target);

        String[] lines = input.split("\n");
        int []moves = new int [lines.length];
        int []extras = new int [lines.length];

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];

            if (line.substring(0, 4).equals("cut ")) {
                moves[i] = 1;
                int n = Integer.parseInt(line.substring(4));
                extras[i] = n;
            } else if (line.equals("deal into new stack")) {
                moves[i] = 2;
            } else {
                moves[i] = 3;
                int n = Integer.parseInt(line.substring(20));
                extras[i] = n;
            }
        }

        for (int i = moves.length - 1; i >= 0; i--) {
            if (moves[i] == 1) {
                deck.reverseCut(extras[i]);
            } else if (moves[i] == 2) {
                deck.reverseNewStack();
            } else {
                deck.reverseIncrement(extras[i]);
            }
        }

        result = deck.getTarget();
    }

    public long getResult() {
        return result;
    }
}