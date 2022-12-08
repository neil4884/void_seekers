package com.game.void_seekers.character.base;

import com.game.void_seekers.tools.GameTools;

public abstract class Health {
    protected int maxRedHealth;
    protected int redHealth;
    protected int maxBlueHealth;
    protected int blueHealth;

    public Health() {
        this(10);
    }

    public Health(int fullHP) {
        this(fullHP, fullHP, HealthStatus.RED);
    }

    public Health(int maxHealth, int health, HealthStatus status) {
        setRedHealth(health);
        setRedMaxHealth(maxHealth);
    }

    public void reduceHealth(int damage) {
//        setHealth(getHealth() - damage);
//        if (status == HealthStatus.BLUE)
//            setMaxHealth(getMaxHealth() - 2 * ((getMaxHealth() - getHealth()) / 2));
    }

    public void increaseRedHealth(int gain) {
    }

    public void increaseRedMaxHealth(int gain) {
    }

    public boolean isDead() {
        return redHealth == 0;
    }

    public int getMaxRedHealth() {
        return maxRedHealth;
    }

    public void setMaxRedHealth(int maxRedHealth) {
        this.maxRedHealth = Math.max(maxRedHealth, 0);
    }

    public int getRedHealth() {
        return redHealth;
    }

    public void setRedHealth(int redHealth) {
        this.redHealth = Math.max(redHealth, 0);
    }

    public int getMaxBlueHealth() {
        return maxBlueHealth;
    }

    public void setMaxBlueHealth(int maxBlueHealth) {
        this.maxBlueHealth = Math.max(maxBlueHealth, 0);
    }

    public int getBlueHealth() {
        return blueHealth;
    }

    public void setBlueHealth(int blueHealth) {
        this.blueHealth = Math.max(blueHealth, 0);
    }
}
