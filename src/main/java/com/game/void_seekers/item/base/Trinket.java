package com.game.void_seekers.item.base;

import com.game.void_seekers.interfaces.Droppable;
import com.game.void_seekers.interfaces.Pickable;

public abstract class Trinket extends EffectItem implements Droppable {
    public Trinket(String name, String description, String itemURL) {
        super(name, description, itemURL);
    }
    @Override
    public void drop() {
        //Todo: add drop events
    }
}
