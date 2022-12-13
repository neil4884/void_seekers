package com.game.void_seekers.character.base;

import com.game.void_seekers.interfaces.Attack;
import com.game.void_seekers.item.base.Active;
import com.game.void_seekers.item.base.EffectItem;
import com.game.void_seekers.item.base.Passive;
import com.game.void_seekers.item.base.Trinket;
import com.game.void_seekers.tools.Coordinates;

import java.util.ArrayList;

public abstract class PlayableCharacter extends GameCharacter implements Attack {
    private int speed; //this should be double from coordinates
    private int fireRate; //min of 1, as same as shooting cool down value
    //private int range; //FIXME: consider later
    private int luck; //Do nothing only get set test value, lol
    private int bombs;
    private int coins;
    private boolean holdTrinket;
    private ArrayList<Passive> passives;
    private Active active;
    private Trinket trinket;

    //TODO: Make set health better
    public PlayableCharacter(String name, int health, Coordinates coordinate, int damage, int speed, int fireRate, int luck) {
        super(name, health, coordinate);
        setCharacterStats(health, damage, speed, fireRate, luck);
        setBombs(0);
        setCoins(0);
        setHoldTrinket(false);
    }

    public PlayableCharacter(String name, int health, int x, int y) {
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

    public int getBombs() {
        return bombs;
    }

    public void setBombs(int bombs) {
        this.bombs = bombs;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = Math.max(Math.min(speed, 15), 4);
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

    public boolean isHoldTrinket() {
        return holdTrinket;
    }

    public void setHoldTrinket(boolean holdTrinket) {
        this.holdTrinket = holdTrinket;
    }

    public EffectItem addEffectItem(EffectItem item) {
        if (item instanceof Passive) {
            passives.add((Passive) item);
            return item;
        } else if (item instanceof Active) {
            Active tmp = getActive();
            setActive((Active) item);
            return tmp;
        } else if (item instanceof Trinket) {
            Trinket tmp = getTrinket();
            setTrinket((Trinket) item);
            return tmp;
        }
        return null;
    }

    public ArrayList<Passive> getPassives() {
        return passives;
    }

    public void setPassives(ArrayList<Passive> passives) {
        this.passives = passives;
    }

    public Active getActive() {
        return active;
    }

    public void setActive(Active active) {
        this.active = active;
    }

    public Trinket getTrinket() {
        return trinket;
    }

    public void setTrinket(Trinket trinket) {
        this.trinket = trinket;
    }
}
