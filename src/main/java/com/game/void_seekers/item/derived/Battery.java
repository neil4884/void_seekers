package com.game.void_seekers.item.derived;

import com.game.void_seekers.item.base.Item;
import com.game.void_seekers.item.base.PocketItem;

public class Battery extends Item {
    private int charges;
    private final String normalBatteryURL = "normalBattery.png";
    private final String largeBatteryURL = "largeBattery.png";
    public Battery(String name, int charges) {
        super(name);
        setCharges(charges);
        //TODO: Add onContactEvent
    }

    public int getCharges() {
        return charges;
    }

    public void setCharges(int charges) {
        this.charges = Math.max(0, charges);
    }

    //TODO: Add draw
}
