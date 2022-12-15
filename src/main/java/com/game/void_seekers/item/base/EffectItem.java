package com.game.void_seekers.item.base;

public abstract class EffectItem extends Item {
    private String description;

    public EffectItem(String name, String description) {
        super(name);
        setDescription(description);
        setSize(75);
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
}
