package com.game.void_seekers.item.base;

public class Heart extends Item {
    private int value;
    private final String redHeartURL = "redHeart.png";
    private final String blueHeartURL = "blueHeart.png";
    public Heart(String name, int value) {
        super(name);
        setValue(value);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value == 1 ? 1: value == 2 ? 2: 0;
    }


}
