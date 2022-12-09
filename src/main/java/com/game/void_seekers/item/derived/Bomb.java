package com.game.void_seekers.item.derived;

import com.game.void_seekers.interfaces.Usable;
import com.game.void_seekers.item.base.Item;
import com.game.void_seekers.item.base.PocketItem;

public class Bomb extends PocketItem implements Usable {
    private int value;
    private final String bombURL = "bomb.png";
    public Bomb(String name, int value) {
        super(name);
        setValue(1); //Only 1 for now :)
        //TODO: Add onContactEvent
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void use() {
        //TODO: later
    }

    //TODO: Add draw


}
