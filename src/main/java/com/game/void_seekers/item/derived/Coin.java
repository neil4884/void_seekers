package com.game.void_seekers.item.derived;

import com.game.void_seekers.item.base.PocketItem;

public class Coin extends PocketItem {
    private int value;
    private static final String pennyURL = "com/game/void_seekers/coin/penny.png";
    private static final String nickelURL = "com/game/void_seekers/coin/nickel.png";
    private static final String dimeURL = "com/game/void_seekers/coin/dime.png";

    public Coin(int value) {
        super("coin");
        setValue(value);
        //TODO: Add onContactEvent
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        switch (value) {
            case 5 -> this.value = 5;
            case 10 -> this.value = 10;
            default -> this.value = 1;
        }
    }
}
