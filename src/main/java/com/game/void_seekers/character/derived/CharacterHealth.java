package com.game.void_seekers.character.derived;

import com.game.void_seekers.character.base.Health;

public class CharacterHealth extends Health {
    private static final int maxHealth = 20;
    public CharacterHealth(int value, int type) {
        initializeHealth();
        if (type == 0) {
            addRedHealth(value);
        } else if (type == 1) {
            addBlueHealth(value);
        }
    }
    private void initializeHealth() {
        setMaxRedHealth(0);
        setMaxBlueHealth(maxBlueHealth);
    }
    public void addRedHealth(int value) {
        setMaxRedHealth(Math.min((getMaxRedHealth() + value) - (getMaxRedHealth() + value) % 2, getMaxRedHealth()));
        setMaxBlueHealth(Math.max(getMaxBlueHealth() - getMaxRedHealth(), 0));
        setRedHealth(getRedHealth() + value);
    }

    public void addBlueHealth(int value) {
        setBlueHealth(Math.min(getBlueHealth() + value, getMaxBlueHealth()));
    }

    public void decreaseRedHealth(int value) {
        setMaxRedHealth(Math.max(0, getMaxRedHealth() - value));
        setRedHealth(Math.min(getMaxRedHealth(), getRedHealth()));
        setMaxBlueHealth(maxHealth - getMaxRedHealth());
    }

    public void decreaseBlueHealth(int value) {
        setBlueHealth(Math.min(getBlueHealth() - value, getMaxBlueHealth()));
    }

    public void addRedHeartContainers(int value) {
        setMaxRedHealth(Math.min(getMaxRedHealth() + getMaxRedHealth() % 2 + value, maxHealth));
    }

    public int getRedHeartContainers() {
        return getMaxRedHealth() / 2;
    }

    public void fullyHeal() {
        setRedHealth(getMaxRedHealth());
    }
    //use reduce health from Health class

}
