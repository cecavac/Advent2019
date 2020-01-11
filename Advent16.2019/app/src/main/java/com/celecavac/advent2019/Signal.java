package com.celecavac.advent2019;

public class Signal {
    private short []signal;

    public Signal(short []signal) {
        this.signal = signal;
    }

    public short[] decode () {
        short []result = new short[signal.length];

        for (int i = 0; i < signal.length; i++) {
            int shiftedValue = Phase.shiftSignal(i, signal);
            shiftedValue = Math.abs(shiftedValue) % 10;
            result[i] = (short) shiftedValue;
        }

        return result;
    }

    public short[] decodeLastHalf(int position) {
        short []result = new short[signal.length];
        int shiftedValue = 0;

        for (int i = signal.length - 1; i >= position; i--) {
            shiftedValue += signal[i];
            shiftedValue = Math.abs(shiftedValue) % 10;
            result[i] = (short) shiftedValue;
        }

        return result;
    }
}
