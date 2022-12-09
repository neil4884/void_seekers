package com.game.void_seekers.item.base;

import com.game.void_seekers.interfaces.Pickable;

public abstract class PocketItem extends Item implements Pickable {
    public PocketItem(String name) {
        super(name);
    }

    //FIXME: should / should not have, will think later
}
