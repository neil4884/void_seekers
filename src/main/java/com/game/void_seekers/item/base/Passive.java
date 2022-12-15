package com.game.void_seekers.item.base;

public abstract class Passive extends EffectItem {
    public Passive(String name, String description) {
        super(name, description);
    }

    public Passive(String name) {
        super(name, "");
    }
}
