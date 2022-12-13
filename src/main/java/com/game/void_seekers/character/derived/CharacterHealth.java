package com.game.void_seekers.character.derived;

import com.game.void_seekers.character.base.Health;

public class CharacterHealth extends Health {
    private static final int maxHealth = 20;
    public CharacterHealth(int value, int type) {
        initializeHealth();
        addHealth(value, type);
    }
    public CharacterHealth(int value1, int type1, int value2, int type2) {
        if (type1 == type2) {
            initializeHealth();
            addHealth(value1 + value2, type1);
        } else {
            initializeHealth();
            addHealth(value1, type1);
            addHealth(value2, type2);
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
        setRedHealth(Math.min(getMaxRedHealth(), getRedHealth()));
    }

    public void decreaseBlueHealth(int value) {
        setBlueHealth(Math.min(getBlueHealth() - value, getMaxBlueHealth()));
    }

    public void addRedHeartContainers(int value) {
        setMaxRedHealth(Math.min(getMaxRedHealth() + getMaxRedHealth() % 2 + value, maxHealth));
    }

    public void removeRedHeartContainers(int value) {
        setMaxRedHealth(Math.max((getMaxRedHealth() - 2 * value) - (getMaxRedHealth() - 2 * value) % 2, 0));
        setMaxBlueHealth(maxHealth - getMaxBlueHealth());
    }

    public int getRedHeartContainers() {
        return getMaxRedHealth() / 2;
    }

    public void fullyHeal() {
        setRedHealth(getMaxRedHealth());
    }

    public void addHealth(int value, int type) {
        if (type == 0) {
            addRedHealth(value);
        } else if (type == 1) {
            addBlueHealth(value);
        }
    }


    //use reduce health from Health class

}
