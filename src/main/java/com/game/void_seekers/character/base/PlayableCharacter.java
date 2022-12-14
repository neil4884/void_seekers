package com.game.void_seekers.character.base;

import com.game.void_seekers.interfaces.Attack;
import com.game.void_seekers.item.base.*;
import com.game.void_seekers.item.derived.*;
import com.game.void_seekers.tools.Coordinates;

import java.util.ArrayList;

public abstract class PlayableCharacter extends GameCharacter implements Attack {
    private int fireRate; //min of 1, as same as shooting cool down value
    //private int range; //FIXME: consider later
    private int luck; //Do nothing only get set test value, lol
    private int bombs;
    private int coins;
    private Active active;
    private Trinket trinket;
    private ArrayList<Passive> passives = new ArrayList<>();
    private ArrayList<EffectItem> pickedUpEffectItem = new ArrayList<>();

    public PlayableCharacter(String name, int health, Coordinates coordinate, int damage, int speed, int fireRate, int luck) {
        super(name, health, coordinate);
        setCharacterStats(health, damage, speed, fireRate, luck);
        setBombs(0);
        setCoins(0);
    }

    public PlayableCharacter(String name, int health, int type, int x, int y) {
        super(name, health, type, x, y);
    }

    public PlayableCharacter(String name, int health1, int type1, int health2, int type2, int x, int y) {
        super(name, health1, type1, health2, type2, x, y);
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
        this.bombs = Math.min(Math.max(0, bombs), 99);
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = Math.min(Math.max(0, coins), 99);
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

    public Item addEffectItem(EffectItem item) {
        addEffectToPickedItem(item);
        if (item instanceof Passive) {
            addPassive((Passive) item);
        } else if (item instanceof Active) {
            if (isActiveSlotEmpty()) {
                setActive((Active) item);
            } else {
                return swapActiveItem((Active) item);
            }
        } else if (item instanceof Trinket) {
            if (isTrinketSlotEmpty()) {
                setTrinket((Trinket) item);
            } else {
                return swapTrinket((Trinket) item);
            }
        }
        return null;
    }

    public Item add(Item item) {
        if (item instanceof PocketItem) {
            if (item instanceof Coin) {
                setCoins(getCoins() + ((Coin) item).getValue());
            } else if (item instanceof Bomb) {
                setBombs(getBombs() + ((Bomb) item).getValue());
            } else if (item instanceof Battery && !isActiveSlotEmpty()) {
                active.setCharge(active.getCharge() + ((Battery) item).getPower());
            } else if (item instanceof Heart tmp) {
                if (tmp.getType() == 0 && super.isExistEmptyRedHeartContainers()) {
                    super.addHealth(tmp.getValue(), 0);
                } else if (tmp.getType() == 1 && super.isExistEmptyBlueHeartContainers()) {
                    super.addHealth(tmp.getValue(), 1);
                }
            }
            //TODO: in logic no value change -> not delete item
        } else if (item instanceof EffectItem) {
            return addEffectItem((EffectItem) item);
        }
        return null;
    }
    public Active dropActiveItem() {
        Active tmp = getActive();
        setActive(null);
        return tmp;
    }
    public Active swapActiveItem(Active newItem) {
        Active tmp = dropActiveItem();
        setActive(newItem);
        return tmp;
    }
    public Trinket dropTrinket() {
        Trinket tmp = getTrinket();
        setTrinket(null);
        return tmp;
    }
    public Trinket swapTrinket(Trinket newItem) {
        Trinket tmp = dropTrinket();
        setTrinket(newItem);
        return tmp;
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

    private boolean isActiveSlotEmpty() {
        return getActive() == null;
    }

    private boolean isTrinketSlotEmpty() {return getTrinket() == null;}

    public boolean isAlreadyPicked(EffectItem item) {
        return pickedUpEffectItem.contains(item);
    }

    public void addEffectToPickedItem(EffectItem item) {
        if (!pickedUpEffectItem.contains(item)) {
            pickedUpEffectItem.add(item);
        }

    }

    public ArrayList<EffectItem> getPickedUpEffectItem() {
        return pickedUpEffectItem;
    }

    public int getNumberOfPickedEffectItem() {
        return pickedUpEffectItem.size();
    }

    public ArrayList<Passive> getPassiveItem() {
        return passives;
    }

    public void addPassive(Passive item) {
        if (!passives.contains(item)) {
            passives.add(item);
        }
    }

    public int getNumberOfPassiveItem() {
        return passives.size();
    }

    public boolean hasBomb() {
        return bombs != 0;
    }

    public boolean hasCoin() {
        return coins != 0;
    }
}
