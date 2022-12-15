package com.game.void_seekers.item.base;

import com.game.void_seekers.interfaces.Pickable;
import javafx.scene.image.Image;

public abstract class PocketItem extends Item implements Pickable {
    public PocketItem(String name) {
        super(name);
        setSize(60);
    }
}
