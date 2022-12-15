package com.game.void_seekers.item.base;

import com.game.void_seekers.interfaces.Droppable;

public abstract class Trinket extends EffectItem implements Droppable {
    public Trinket(String name, String description) {
        super(name, description);
    }

    @Override
    public void drop() {
        //Todo: add drop events
    }
}
