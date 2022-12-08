package com.game.void_seekers.item.derived;

import com.game.void_seekers.item.base.Item;

public class Heart extends Item {
    private int value;
    private final String halfRedHeartURL = "halfRedHeart.png";
    private final String halfBlueHeartURL = "halfBlueHeart.png";
    private final String redHeartURL = "redHeart.png";
    private final String blueHeartURL = "blueHeart.png";
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
