package com.game.void_seekers.item.base;

import com.game.void_seekers.interfaces.Usable;

public abstract class Active extends EffectItem implements Usable {
    protected int charge;
    protected int maxCharge;
    public Active(String name, String description, int maxCharge) {
        super(name, description);
        setCharge(0);
        setMaxCharge(maxCharge);
    }
    public Active(String name, int maxCharge) {
        super(name, "");
        setCharge(0);
        setMaxCharge(maxCharge);
    }

    public boolean isAvailable() {
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

    public void use() {
        if (isAvailable()) {
            setCharge(getCharge() - maxCharge);
        }
    }

}
