package com.celecavac.advent2019;

import java.math.BigInteger;

public class Resulter {
    private long result;

    public Resulter(Long a, Long b, Long n, Long iterations, Long target) {
        // a = k * 2020 + c
        // b = k * 2021 + c

        // c = a - k * 2020

        // b = k * 2021 + a - k * 2020
        // b = k + a
        // k = b - a
        Long k = b - a;
        Long c = a - k * 2020;

        // result = (k^iterations:mod(n)) * target + ((k^iterations:mod(n) - 1) * modinverse(k - 1), n) * c
        // result %= n

        BigInteger bigN = new BigInteger(n.toString());
        BigInteger bigK = new BigInteger(k.toString());
        BigInteger bigC = new BigInteger(c.toString());
        BigInteger bigTarget = new BigInteger(target.toString());
        BigInteger bigI = new BigInteger(iterations.toString());
        Long divider = k - 1;
        BigInteger bigDivider = new BigInteger(divider.toString());

        BigInteger element1 = bigK.modPow(bigI, bigN);
        element1 = element1.multiply(bigTarget);

        BigInteger element2 = bigK.modPow(bigI, bigN);
        element2 = element2.subtract(BigInteger.ONE);

        BigInteger element3 = bigDivider;
        element3 = element3.modInverse(bigN);

        element2 = element2.multiply(element3);
        element2 = element2.multiply(bigC);

        element1 = element1.add(element2);
        element1 = element1.mod(bigN);

        result = element1.longValue();
    }

    public long getResult() {
        return result;
    }
}
