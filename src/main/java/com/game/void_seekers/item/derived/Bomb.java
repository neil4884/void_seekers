package com.game.void_seekers.item.derived;

import com.game.void_seekers.item.base.Item;

public class Bomb extends Item {
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

    //TODO: Add draw


}
