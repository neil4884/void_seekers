package com.game.void_seekers.item.derived;

import com.game.void_seekers.item.base.Item;

public class Coin extends Item {
    private int value;
    private final String pennyURL = "penny.png";
    private final String nickelURL = "nickel.png";
    private final String dimeURL = "dime.png";

    public Coin(String name, int value) {
        super(name);
        setValue(value);
        //TODO: Add onContactEvent
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        switch (value) {
            case 1 -> this.value = 1;
            case 5 -> this.value = 5;
            case 10 -> this.value = 10;
            default -> this.value = 0;
        }
    }

    //TODO: Add draw
}