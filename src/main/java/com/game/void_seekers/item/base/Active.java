package com.game.void_seekers.item.base;

import com.game.void_seekers.interfaces.AffectPlayer;
import com.game.void_seekers.interfaces.Pickable;
import com.game.void_seekers.interfaces.Usable;

public abstract class Active extends EffectItem implements Usable {
    private int charge;
    private int maxCharge;
    public Active(String name, String description, String itemURL, int maxCharge) {
        super(name, description, itemURL);
        setCharge(0);
        setMaxCharge(maxCharge);
    }
    public Active(String name, String itemURL, int maxCharge) {
        super(name, "", itemURL);
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
