package com.celecavac.advent2019;

public class Password {
    private int password;

    public Password(int password) {
        this.password = password;
    }

    public boolean isValid() {
        boolean hasMatching = false;
        int []digits = new int[6];
        int shiftedPassword = password;

        for (int i = 0; i < 6; i++) {
            digits[5 - i] = shiftedPassword % 10;
            shiftedPassword /= 10;
        }

        for (int i = 0; i < 5; i++) {
            if (digits[i] > digits[i + 1])
                return false;

            if (digits[i] == digits[i + 1])
                hasMatching = true;
        }

        return hasMatching;
    }

    public boolean isValid2() {
        boolean hasMatching = false;
        int []digits = new int[6];
        int shiftedPassword = password;

        for (int i = 0; i < 6; i++) {
            digits[5 - i] = shiftedPassword % 10;
            shiftedPassword /= 10;
        }

        for (int i = 0; i < 5; i++) {
            if (digits[i] > digits[i + 1])
                return false;

            if (digits[i] == digits[i + 1])
                hasMatching = true;
        }

        if (!hasMatching) {
            return hasMatching;
        } else {
            if (digits[0] == digits[1] &&
                    digits[1] != digits[2])
                return true;
            else if(digits[0] != digits[1] &&
                    digits[1] == digits[2] &&
                    digits[2] != digits[3])
                return true;
            else if(digits[1] != digits[2] &&
                    digits[2] == digits[3] &&
                    digits[3] != digits[4])
                return true;
            else if(digits[2] != digits[3] &&
                    digits[3] == digits[4] &&
                    digits[4] != digits[5])
                return true;
            else if(digits[3] != digits[4] &&
                    digits[4] == digits[5])
                return true;
            else
                return  false;
        }
    }
}
