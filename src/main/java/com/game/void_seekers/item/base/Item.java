package com.game.void_seekers.item.base;

import com.game.void_seekers.interfaces.Pickable;

public abstract class Item implements Pickable {
    private String name;
    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
