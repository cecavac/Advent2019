package com.celecavac.advent2019;

public class Phase {
    private static short []basePhase;

    public static void init() {
        if (basePhase == null) {
            int i = 0;
            basePhase = new short[4];
            basePhase[i++] = 0;
            basePhase[i++] = 1;
            basePhase[i++] = 0;
            basePhase[i] = -1;
        }
    }

    public static int shiftSignal(int position, short []signal) {
        int result = 0;
        int repeat = position + 1;

        for (int i = position; i < signal.length; i++) {
            int j = ((i + 1) % (repeat * 4)) / repeat;

            if (basePhase[j] == 0) {
                i += repeat - 1;
            } else if (basePhase[j] == 1) {
                result += signal[i];
            } else {
                result -= signal[i];
            }
        }

        return result;
    }
}
