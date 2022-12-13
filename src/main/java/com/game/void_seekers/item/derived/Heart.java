package com.game.void_seekers.item.derived;

import com.game.void_seekers.item.base.Item;

public class Heart extends Item {
    private int value;
    private final String halfRedHeartURL = "com/game/void_seekers/heart/halfRedHeart.png";
    private final String halfBlueHeartURL = "com/game/void_seekers/heart/halfBlueHeart.png";
    private final String redHeartURL = "com/game/void_seekers/heart/redHeart.png";
    private final String blueHeartURL = "com/game/void_seekers/heart/blueHeart.png";
    //TODO: make heart type distinguishable
    public Heart(String name, int value) {
        super(name);
        setValue(value);
        //TODO: Add onContactHandle
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = Math.max(0, value);
    }

    //TODO: Assign Heart type from specific value


}
