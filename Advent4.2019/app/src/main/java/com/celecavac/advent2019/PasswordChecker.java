package com.celecavac.advent2019;


public class PasswordChecker {
    private int result = 0;
    private int result2 = 0;

    public PasswordChecker(int bottom, int top) {
        for (int i = bottom; i <= top; i++) {
            Password password = new Password(i);

            if (password.isValid())
                result++;

            if (password.isValid2())
                result2++;
        }
    }

    public String getResult() {
        return Integer.toString(result);
    }

    public String getResult2() {
        return Integer.toString(result2);
    }
}
