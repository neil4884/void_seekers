package com.game.void_seekers.character.base;

import com.game.void_seekers.tools.Coordinates;

public class EnemyCharacter extends GameCharacter {
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
}
