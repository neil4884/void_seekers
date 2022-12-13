package com.game.void_seekers.character.derived;

import com.game.void_seekers.character.base.Health;

public class CharacterHealth extends Health {
    private static int maxHealth = 20;
    public CharacterHealth(int value, int type) {
        initializeHealth();
        addFullHealth(value, type);
    }
    public CharacterHealth(int value1, int type1, int value2, int type2) {
        if (type1 == type2) {
            initializeHealth();
            addFullHealth(value1 + value2, type1);
        } else {
            initializeHealth();
            addFullHealth(value1, type1);
            addFullHealth(value2, type2);
        }
    }
    public CharacterHealth(int value) {
        setMaxHealth(value);
        initializeHealth();
        setBlueHealth(value);
    }
    private void initializeHealth() {
        setMaxRedHealth(0);
        setMaxBlueHealth(maxHealth);
    }
    public void decreaseRedHealth(int value) {
        setRedHealth(Math.max(getRedHealth() - value, 0));
    }
    public void decreaseBlueHealth(int value) {
        setBlueHealth(Math.min(getBlueHealth() - value, getMaxBlueHealth()));
    }

    public void addRedHeartContainers(int value) {
        setMaxRedHealth(Math.min(getMaxRedHealth() + 2 * value, maxHealth));
        setMaxBlueHealth(maxHealth - getMaxRedHealth());
        if (getRedHealth() + getBlueHealth() >= maxHealth) {
            setBlueHealth(maxHealth - getMaxRedHealth());
        }
    }

    public void removeRedHeartContainers(int value) {
        setMaxRedHealth(Math.max((getMaxRedHealth() - 2 * value) - ((getMaxRedHealth() - 2 * value) % 2), 0));
        setRedHealth(Math.min(getRedHealth(), getMaxRedHealth()));
        setMaxBlueHealth(maxHealth - getMaxRedHealth());
    }
    public void addFullyHealRedHeartContainers(int value) {
        addRedHeartContainers(value);
        addHealth(2 * value, 0);
    }

    public int getRedHeartContainers() {
        return getMaxRedHealth() / 2;
    }

    public void fullyHeal() {
        setRedHealth(getMaxRedHealth());
    }

    public void addHealth(int value, int type) {
        if (type == 0) {
            setRedHealth(getRedHealth() + value);
        } else if (type == 1) {
            setBlueHealth(getBlueHealth() + value);
        }
    }

    public void addFullHealth(int value, int type) {
        if (type == 0) {
            setMaxRedHealth(value + (value % 2));
            setRedHealth(value);
            setMaxBlueHealth(maxHealth - getMaxRedHealth());
        } else if (type == 1) {
            setBlueHealth(value);
        }
    }

    public void reduceHealth(int damage) {
        while (damage > 0) {
            if (getBlueHealth() > 0) {
                decreaseBlueHealth(1);
            } else if (getRedHealth() > 0) {
                decreaseRedHealth(1);
            } else {
                return;
            }
            --damage;
        }
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public static void setMaxHealth(int maxHealth) {
        CharacterHealth.maxHealth = maxHealth;
    }

}
