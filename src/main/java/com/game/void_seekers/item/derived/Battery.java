package com.game.void_seekers.item.derived;

import com.game.void_seekers.item.base.PocketItem;

public class Battery extends PocketItem {
    private int power;
    private static final String normalBatteryURL = "com/game/void_seekers/battery/normalBattery.png";
    private static final String largeBatteryURL = "com/game/void_seekers/battery/largeBattery.png";
    public Battery(int type) {
        super(type <= 0 ? "battery": "largeBattery");
        setPower(type <= 0 ? 2: 999);
        //TODO: Add onContactEvent
    }
    public Battery() {
        super("battery");
        setPower(2);
    }
    public int getPower() {
        return power;
    }
    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public void draw() {

    }
    //TODO: Add draw
}
