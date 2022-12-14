package com.game.void_seekers.character.base;

import com.game.void_seekers.tools.Coordinates;

public abstract class EnemyCharacter extends GameCharacter {
    private boolean isAttacking;

    public EnemyCharacter(String name, int health, Coordinates coordinate) {
        super(name, health, coordinate);
    }

    public EnemyCharacter(String name, int health, int x, int y) {
        super(name, health, x, y);
    }

    public EnemyCharacter() {
        super();
        super.setName("Untitled Player");
    }

    public boolean isAttacking() {
        return isAttacking;
    }

    public void setAttacking(boolean attacking) {
        isAttacking = attacking;
    }
}
