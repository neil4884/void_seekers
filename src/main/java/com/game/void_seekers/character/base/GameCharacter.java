package com.game.void_seekers.character.base;

import com.game.void_seekers.interfaces.Movable;
import com.game.void_seekers.tools.Coordinates;

public abstract class GameCharacter extends Health implements Movable {
    protected int damage; //Change from baseDamage to damage
    protected String name;
    protected Coordinates coordinate;

    public GameCharacter() {
        super();
        setDamage(0);
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
        setDamage(0);
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

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = Math.max(damage, 1);
    } //Should deal basic damage

    public void addRedHeart(int amount) {
        int amt = 2 * amount;
        if (super.getMaxRedHealth() - super.getRedHealth() < amt)
            super.increaseMaxRedHealth(amt - super.getMaxRedHealth() + super.getRedHealth());
        if (super.getMaxRedHealth() % 2 == 1)
            super.increaseMaxRedHealth(1);
        super.increaseRedHealth(amt);
    }

    public void addBlueHeart(int amount) {
        int amt = 2 * amount;
        if (super.getMaxBlueHealth() - super.getBlueHealth() < amt)
            super.increaseMaxBlueHealth(amt - super.getMaxBlueHealth() + super.getBlueHealth());
        if (super.getMaxBlueHealth() % 2 == 1)
            super.increaseMaxBlueHealth(1);
        super.increaseBlueHealth(amt);
    }

    public void setRedHealth(int health, int max_health) {
        super.setMaxRedHealth(max_health);
        super.setRedHealth(health);
    }

    public void setBlueHealth(int health, int max_health) {
        super.setMaxBlueHealth(max_health);
        super.setBlueHealth(health);
    }
}
