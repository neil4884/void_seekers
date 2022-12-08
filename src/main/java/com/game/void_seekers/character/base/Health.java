package com.game.void_seekers.character.base;

import com.game.void_seekers.tools.GameTools;

public abstract class Health {
    private boolean dead;
    private int maxHealth;
    private int health;
    private HealthStatus status;

    public Health() {
        this(10);
    }

    public Health(int fullHP) {
        this(fullHP, fullHP, HealthStatus.RED);
    }

    public Health(int maxHealth, int health, HealthStatus status) {
        setHealth(health);
        setMaxHealth(maxHealth);
        setStatus(status);
    }

    public void reduceHealth(int damage) {
        setHealth(GameTools.getPositive(getHealth() - damage));
        if (status == HealthStatus.BLUE)
            setMaxHealth(getMaxHealth() - 2 * ((getMaxHealth() - getHealth()) / 2));
    }

    public void increaseHealth(int gain) {
        setHealth(Math.min(gain, maxHealth));
    }

    public void increaseMaxHealth(int gain) {
        setMaxHealth(getMaxHealth() + gain);
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead() {
        dead = true;
    }

    public void resurrect() {
        dead = false;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public HealthStatus getStatus() {
        return status;
    }

    public void setStatus(HealthStatus status) {
        this.status = status;
    }

    public void displayHealthBar() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < health; ++i) {
            s.append("1");
            if (i % 2 == 1)
                s.append(" ");
        }
        for (int i = health; i < maxHealth; ++i) {
            s.append("0");
            if (i % 2 == 1)
                s.append(" ");
        }
        System.out.println(s);
    }
}
