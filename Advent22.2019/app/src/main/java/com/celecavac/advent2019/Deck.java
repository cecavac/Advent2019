package com.celecavac.advent2019;

import androidx.annotation.NonNull;

public class Deck {
    private int []deck;
    private int []pattern;

    public Deck(int cards) {
        deck = new int[cards];
        for (int i = 0; i < deck.length; i++)
            deck[i] = i;
    }

    public void newStack() {
        for (int i = 0; i < deck.length / 2; i++) {
            int index1 = i;
            int index2 = deck.length - 1 - i;
            int temp = deck[index1];
            deck[index1] = deck[index2];
            deck[index2] = temp;
        }
    }

    public void cut(int n) {
        int []newDeck = new int[deck.length];
        if (n < 0) {
            n = -n;

            for (int i = deck.length - n; i < deck.length; i++)
                newDeck[i - deck.length + n] = deck[i];

            for (int i = 0; i < deck.length - n; i++)
                newDeck[i + n] = deck[i];

        } else {
            for (int i = n; i < deck.length; i++)
                newDeck[i - n] = deck[i];

            for (int i = 0; i < n; i++) {
                newDeck[deck.length - n + i] = deck[i];
            }
        }

        deck = newDeck;
    }

    public void increment(int n) {
        int i = 0;
        int []newDeck = new int[deck.length];
        for (int j = 0; j < deck.length; j++) {
            newDeck[i] = deck[j];
            i = (i + n) % deck.length;
        }

        deck = newDeck;
    }

    public int getIndex(int target) {
        for (int i = 0; i < deck.length; i++) {
            if (deck[i] == target)
                return i;
        }

        return -1;
    }

    public int get(int index) {
        return deck[index];
    }

    public void saveShufflePattern() {
        pattern = new int[deck.length];
        for (int i = 0; i < deck.length; i++)
            pattern[i] = deck[i];
    }

    public boolean applyPattern() {
        boolean matchingPattern = true;
        int []newDeck = new int[deck.length];

        for (int i = 0; i < deck.length; i++) {
            newDeck[i] = deck[pattern[i]];
            matchingPattern &= newDeck[i] == pattern[i];
        }

        deck = newDeck;

        return matchingPattern;
    }

    public void reset() {
        deck = new int[deck.length];
        for (int i = 0; i < deck.length; i++)
            deck[i] = i;
    }

    @NonNull
    @Override
    public String toString() {
        String ret = "";
        for (int i = 0; i < deck.length; i++)
            ret += deck[i] + " ";
        return ret;
    }
}
