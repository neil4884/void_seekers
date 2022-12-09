package com.game.void_seekers.item.base;

import com.game.void_seekers.interfaces.Pickable;

import java.util.ArrayList;

public abstract class EffectItem extends Item {
    private String description;
    private String itemURL;
    public EffectItem(String name, String description, String itemURL) {
        super(name);
        setDescription(description);
        setItemURL(itemURL);
    }

    public String toString() {
        return super.getName() + "\n" + description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItemURL() {
        return itemURL;
    }

    public void setItemURL(String itemURL) {
        this.itemURL = itemURL;
    }
}
