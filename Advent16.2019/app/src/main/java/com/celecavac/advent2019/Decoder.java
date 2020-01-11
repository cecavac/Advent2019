package com.celecavac.advent2019;

public class Decoder {
    String result = "";
    short []input;
    private Signal signal;

    public Decoder(String input) {
        this.input = new short[input.length()];
        for (int i = 0; i < input.length(); i++)
            this.input[i] = (short) Character.getNumericValue(input.charAt(i));
        Phase.init();
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            signal = new Signal(input);
            input = signal.decode();
        }

        for (int i = 0; i < 8; i++)
            result += input[i];
    }

    public void runReal() {
        int multiplyFactor = 10000;
        short []realInput = new short[input.length * multiplyFactor];

        for (int i = 0; i < realInput.length; i++)
            realInput[i] = input[i % input.length];

        int offset = 0;
        for (int i = 0; i < 7; i++)
            offset = (offset * 10) + input[i];

        for (int i = 0; i < 100; i++) {
            signal = new Signal(realInput);
            realInput = signal.decodeLastHalf(offset);
        }

        for (int i = 0; i < 8; i++)
            result += realInput[i + offset];
    }

    public String getResult() {
        return result;
    }
}
