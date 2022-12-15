package com.game.void_seekers.tools;

public class Coordinates implements Cloneable {
    public int x;
    public int y;

    public Coordinates() {
        this(0, 0);
    }

    public Coordinates(Coordinates coordinates) {
        this(coordinates.x, coordinates.y);
    }

    public Coordinates(int z) {
        this.x = z;
        this.y = z;
    }

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getDistance(Coordinates other) {
        return Math.sqrt(Math.pow((x - other.x), 2) + Math.pow((y - other.y), 2));
    }

    public Coordinates add(Coordinates other) {
        return new Coordinates(x + other.x, y + other.y);
    }

    public Coordinates add(int x, int y) {
        return new Coordinates(this.x + x, this.y + y);
    }

    public Coordinates minus(Coordinates other) {
        return new Coordinates(x - other.x, y - other.y);
    }

    public Coordinates minus(int x, int y) {
        return new Coordinates(this.x - x, this.y - y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public Coordinates clone() {
        return new Coordinates(x, y);
    }
}
