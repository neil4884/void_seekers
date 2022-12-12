package com.game.void_seekers.tools;

public class Coordinates {
    public int x;
    public int y;

    public Coordinates() {
        this(0, 0);
    }

    public Coordinates(int x, int y) {
        setX(x);
        setY(y);
    }

    public double getDistance(Coordinates other) {
        return Math.sqrt(Math.pow((x - other.x), 2) + Math.pow((y - other.y), 2));
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
