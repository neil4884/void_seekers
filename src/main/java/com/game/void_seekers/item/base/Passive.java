package com.game.void_seekers.item.base;

import com.game.void_seekers.interfaces.Pickable;

public abstract class Passive extends EffectItem implements Pickable {
    public Passive(String name, String description) {
        super(name, description);
    }
    public Passive(String name) {
        super(name, "");
    }
}
