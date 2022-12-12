package com.game.void_seekers.character.base;

import com.game.void_seekers.tools.Coordinates;

public abstract class EnemyCharacter extends GameCharacter {
    private double speed;
    public EnemyCharacter(String name, int health, Coordinates coordinate) {
        super(name, health, coordinate);
    }

    public EnemyCharacter(String name, int health, double x, double y) {
        super(name, health, x, y);
    }

    public EnemyCharacter() {
        super();
        super.setName("Untitled Player");
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
