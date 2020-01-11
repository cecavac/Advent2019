package com.celecavac.advent2019;


public class Line {
    private double k;
    private int y1;
    private int x1;

    public Line(int x1, int y1, int x2, int y2) {
        k = y2 - y1;
        k /= x2 - x1;

        this.y1 = y1;
        this.x1 = x1;
    }

    public int getX(int y) {
        int x = y - y1;
        x /= k;
        x += x1;
        return x;
    }

    public int getY(int x) {
        int y = x - x1;
        y *= k;
        y += y1;
        return y;
    }

    public static int getXC(Line lineB, Line lineC, int width, int height) {
        double result = 0;
        width--;
        height--;

        result += height;
        result -= lineC.y1;
        result += lineB.y1;
        result += lineC.k * lineC.x1;
        result += lineB.k * width;
        result -= lineB.k * lineB.x1;
        result /= lineC.k - lineB.k;

        return (int) result;
    }

    public static int getXB(int xC, int width) {
        width--;
        return xC + width;
    }

    public static int getYB(Line lineB, Line lineC, int width, int height) {
        double result = 0;
        width--;
        height--;

        result += lineB.k * lineC.k * (- width - lineC.x1 + lineB.x1);
        result -= lineB.k * height;
        result += lineB.k * lineC.y1;
        result -= lineC.k * lineB.y1;
        result /= lineB.k - lineC.k;

        return (int) result;
    }

    public static int getYC(int yB, int height) {
        height--;
        return yB + height;
    }
}
