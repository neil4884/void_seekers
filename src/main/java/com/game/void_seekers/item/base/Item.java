package com.game.void_seekers.item.base;

import com.game.void_seekers.interfaces.Draw;
import com.game.void_seekers.interfaces.Pickable;
import javafx.scene.image.Image;

public abstract class Item implements Pickable /*, todo: Draw*/ {
    protected String name;
    protected Image assetImage;
    protected int size;
    public Item(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getAssetImage() {
        return assetImage;
    }

    public void setAssetImage(Image assetImage) {
        this.assetImage = assetImage;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
