package com.game.void_seekers.character.base;

public abstract class Health {
    int maxHealth;
    int health;
    HealthStatus status;

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

    public void reduceHealth() {
        
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
}
