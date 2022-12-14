package com.game.void_seekers.projectile.base;

import com.game.void_seekers.interfaces.Draw;
import com.game.void_seekers.tools.Coordinates;
import javafx.scene.image.Image;

public abstract class Projectile implements Draw {
    protected Image image;
    protected int size;
    protected int speed;
    protected int damage;
    protected String name;
    protected Coordinates coordinate;
    public Projectile(String name, int damage, int speed, Coordinates coordinate) {
        setName(name);
        setDamage(damage);
        setSpeed(speed);
        setCoordinate(coordinate);
    }
    public Projectile(String name, Coordinates coordinate) {
        this(name, 1, 1, coordinate);
    }

    public Projectile(String name, int damage, Coordinates coordinate) {
        this(name, damage, 1, coordinate);
    }
    public Projectile(int damage, Coordinates coordinate) {
        this("Default", damage, 1, coordinate);
    }
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Coordinates getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinates coordinate) {
        this.coordinate = coordinate;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
