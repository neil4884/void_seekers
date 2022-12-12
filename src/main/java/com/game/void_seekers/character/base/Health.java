package com.game.void_seekers.character.base;

public abstract class Health {
    //TODO: Make health getter/setter accessible from other health method
    protected int maxRedHealth;
    protected int redHealth;
    protected int maxBlueHealth;
    protected int blueHealth;

    public Health() {
        this(10);
    }

    public Health(int fullHP) {
        this(fullHP, fullHP);
    }

    public Health(int maxHealth, int health) {
        setMaxRedHealth(maxHealth);
        setRedHealth(health);
        setMaxBlueHealth(0);
        setBlueHealth(0);
    }

    public void reduceHealth(int damage) {
        while (damage > 0) {
            if (getBlueHealth() > 0) {
                setBlueHealth(getBlueHealth() - 1);
            } else if (getRedHealth() > 0) {
                setRedHealth(getRedHealth() - 1);
            } else {
                return;
            }
            --damage;
        }
        setMaxBlueHealth(getBlueHealth() + (getBlueHealth() % 2 == 0 ? 0 : 1));
    }

    public void increaseRedHealth(int gain) {
        setRedHealth(Math.min(getMaxRedHealth(), getRedHealth() + gain));
    }

    public void increaseMaxRedHealth(int gain) {
        setMaxRedHealth(getMaxRedHealth() + gain);
    }

    public void increaseBlueHealth(int gain) {
        setBlueHealth(Math.min(getMaxBlueHealth(), getBlueHealth() + gain));
    }

    public void increaseMaxBlueHealth(int gain) {
        setMaxBlueHealth(getMaxBlueHealth() + gain);
    }

    public boolean isDead() {
        return redHealth == 0 && blueHealth == 0;
    }

    public int getMaxRedHealth() {
        return maxRedHealth;
    }

    public void setMaxRedHealth(int maxRedHealth) {
        this.maxRedHealth = Math.max(Math.max(maxRedHealth, 0), getRedHealth());
    }

    public int getRedHealth() {
        return redHealth;
    }

    public void setRedHealth(int redHealth) {
        this.redHealth = Math.min(Math.max(redHealth, 0), getMaxRedHealth());
    }

    public int getMaxBlueHealth() {
        return maxBlueHealth;
    }

    public void setMaxBlueHealth(int maxBlueHealth) {
        this.maxBlueHealth = Math.max(Math.max(maxBlueHealth, 0), getBlueHealth());
    }

    public int getBlueHealth() {
        return blueHealth;
    }

    public void setBlueHealth(int blueHealth) {
        this.blueHealth = Math.min(Math.max(blueHealth, 0), getMaxBlueHealth());
    }

    public void displayHealthBar() {
        System.out.print("RED  ");
        for (int i = 0; i < redHealth; ++i)
            System.out.print("1");
        for (int i = redHealth; i < maxRedHealth; ++i)
            System.out.print("0");
        System.out.println();

        System.out.print("BLUE ");
        for (int i = 0; i < blueHealth; ++i)
            System.out.print("1");
        for (int i = blueHealth; i < maxBlueHealth; ++i)
            System.out.print("0");
        System.out.println("\n-----");
    }
}
