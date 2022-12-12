package com.game.void_seekers.character.base;

import com.game.void_seekers.interfaces.Attack;
import com.game.void_seekers.tools.Coordinates;

import java.util.ArrayList;

public abstract class PlayableCharacter extends GameCharacter implements Attack {
    private int speed; //this should be double from coordinates
    private int fireRate; //min of 1, as same as shooting cool down value
    //private int range; //FIXME: consider later
    private int luck; //Do nothing only get set test value, lol

    //TODO: Make set health better
    public PlayableCharacter(String name, int health, Coordinates coordinate, int damage, int speed, int fireRate, int luck) {
        super(name, health, coordinate);
        setCharacterStats(health, damage, speed, fireRate, luck);
    }

    public PlayableCharacter(String name, int health, double x, double y) {
        super(name, health, x, y);
    }

    public PlayableCharacter() {
        super();
        super.setName("Untitled Player");
        setCharacterStats();
    }

    @Override
    public void attack(GameCharacter character) {
        character.reduceHealth(getDamage());
    }

    public ArrayList<PlayerEffects> getStats() {
        return new ArrayList<>(); //FIXME: ?
    }

    public void setCharacterStats() {
        super.setDamage(1);
        setSpeed(1);
        setFireRate(1);
        setLuck(0);
    }

    public void setCharacterStats(int health, int damage, int speed, int fireRate, int luck) {
        //fixme: what heart should this set?
//        super.setHealth(health);
        super.setDamage(damage);
        setSpeed(speed);
        setFireRate(fireRate);
        setLuck(luck);
    }

    public void setCharacterStats(int damage, int speed, int fireRate, int luck) {
        super.setDamage(damage);
        setSpeed(speed);
        setFireRate(fireRate);
        setLuck(luck);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = Math.max(Math.min(speed, 20), 4);
    }

    public int getFireRate() {
        return fireRate;
    }

    public void setFireRate(int fireRate) {
        this.fireRate = Math.max(1, fireRate);
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = Math.min(Math.max(luck, -16), 16);
    }
}
