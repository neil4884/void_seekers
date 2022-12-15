package com.game.void_seekers.item.derived;

import com.game.void_seekers.item.base.PocketItem;
import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import javafx.scene.canvas.GraphicsContext;

public class Bomb extends PocketItem {
    private int value;

    public Bomb() {
        super("bomb");
        setValue(1); //Only 1 for now :)
        setAssetImage(GameAssets.loadImage(GameAssets.bombURL, size));
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void draw() {
        GraphicsContext gc = GameLogic.getGraphicsContext();
        gc.drawImage(super.getAssetImage(), coordinate.x, coordinate.y);
    }


}
