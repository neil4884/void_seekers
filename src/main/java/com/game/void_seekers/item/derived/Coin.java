package com.game.void_seekers.item.derived;

import com.game.void_seekers.item.base.PocketItem;
import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import javafx.scene.canvas.GraphicsContext;

public class Coin extends PocketItem {
    private int value;

    public Coin(int value) {
        super("coin");
        setValue(value);
        addAssets(value);
    }

    private void addAssets(int value) {
        switch (value) {
            case 5 -> setAssetImage(GameAssets.loadImage(GameAssets.nickelURL, size));
            case 10 -> setAssetImage(GameAssets.loadImage(GameAssets.dimeURL, size));
            default -> setAssetImage(GameAssets.loadImage(GameAssets.pennyURL, size));
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        switch (value) {
            case 5 -> this.value = 5;
            case 10 -> this.value = 10;
            default -> this.value = 1;
        }
    }

    @Override
    public void draw() {
        GraphicsContext gc = GameLogic.getGraphicsContext();
        gc.drawImage(super.getAssetImage(), coordinate.x, coordinate.y);
    }
}
