package com.game.void_seekers.character.base;

import com.game.void_seekers.interfaces.Movable;
import com.game.void_seekers.tools.Coordinates;

public abstract class GameCharacter extends Health implements Movable {
    protected int baseDamage;
    protected String name;
    protected Coordinates coordinate;

    public GameCharacter() {
        super();
        setBaseDamage(0);
        setCoordinate(new Coordinates());
        setName("Untitled Character");
    }

    public GameCharacter(String name, int health, double x, double y) {
        this(name, health, new Coordinates(x, y));
    }

    public GameCharacter(String name, int health, Coordinates coordinate) {
        super(health);
        setCoordinate(coordinate);
        setName(name);
        setBaseDamage(0);
    }

    public Coordinates getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinates coordinate) {
        this.coordinate = coordinate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = Math.max(baseDamage, 0);
    }
}
