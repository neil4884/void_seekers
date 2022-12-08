package com.game.void_seekers.tools;

public class Coordinates {
    public double x;
    public double y;

    public Coordinates() {
        this(0, 0);
    }

    public Coordinates(double x, double y) {
        setX(x);
        setY(y);
    }

    public double getDistance(Coordinates other) {
        return Math.sqrt(Math.pow((x - other.x), 2) + Math.pow((y - other.y), 2));
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
