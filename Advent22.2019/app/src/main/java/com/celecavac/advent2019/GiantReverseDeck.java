package com.celecavac.advent2019;

import java.util.HashMap;

public class GiantReverseDeck {
    private long cards, target;
    HashMap<Long, Long> seriesMap = new HashMap<>();
    HashMap<Long, Long> offsetsMap = new HashMap<>();

    public GiantReverseDeck(long cards, long target) {
        this.cards = cards;
        this.target = target;
    }

    public void reverseNewStack() {
        target = cards - 1 - target;
    }

    public void reverseCut(long n) {
        long originalTarget = target;
        if (n < 0)
            n = cards + n;

        target = target + n;

        if (target >= cards) {
            target = originalTarget - cards + n;
        }
    }

    public void reverseIncrement(long n) {
        long compressedDeck = n + (cards % n);
        long mod = target % n;

        long index = 0;
        long series = 0;

        long hash = (n << 10) + mod;

        boolean containsHash = seriesMap.containsKey(hash);

        if (containsHash) {
            series = seriesMap.get(hash);
        } else {
            for (int i = 0; i < compressedDeck; i++) {
                if (index == mod) {
                    break;
                }
                if (index + n >= compressedDeck)
                    series++;
                index = (index + n) % compressedDeck;
            }

            seriesMap.put(hash, series);
        }

        long offset = target / n;
        long base = (cards / n) * series;

        if (mod != 0)
            base++;

        long start = base + offset;

        if (containsHash) {
            start += offsetsMap.get(hash);
        }

        for (long j = start; j < cards; j++) {
            long i = (j * n) % cards;
            if (i == target) {
                target = j;
                if (!containsHash) {
                    offsetsMap.put(hash, j - start);
                }
                break;
            }
        }
    }

    public long getTarget() {
        return target;
    }
}
