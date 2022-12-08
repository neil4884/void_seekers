package com.game.void_seekers.item.base;

public class Active extends Item {
    private int charge;
    private int maxCharge;
    public Active(String name, int maxCharge) {
        super(name);
        setCharge(0);
        setMaxCharge(maxCharge);
    }

    public boolean isAvaliable() {
        return charge >= maxCharge;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = Math.min(Math.max(0, charge), maxCharge);
    }

    public int getMaxCharge() {
        return maxCharge;
    }

    public void setMaxCharge(int maxCharge) {
        this.maxCharge = maxCharge;
    }

}
