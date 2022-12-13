package com.game.void_seekers.item.derived;

import com.game.void_seekers.item.base.PocketItem;
import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import javafx.scene.canvas.GraphicsContext;

public class Heart extends PocketItem {
    private int value;
    //TODO: make heart type distinguishable
    public Heart(String name, int value, int type) {
        super(name);
        setValue(value);
        if (value == 0 && type == 0) {
            setAssetImage(GameAssets.loadImage(GameAssets.halfRedHeartURL, size));
        } else if (value == 1 && type == 0) {
            setAssetImage(GameAssets.loadImage(GameAssets.redHeartURL, size));
        } else if (value == 0 && type == 1) {
            setAssetImage(GameAssets.loadImage(GameAssets.halfBlueHeartURL, size));
        } else if (value == 1 && type == 1) {
            setAssetImage(GameAssets.loadImage(GameAssets.blueHeartURL, size));
        }
    }
    //TODO: Add onContactHandle

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = Math.max(0, value);
    }

    @Override
    public void draw() {
        GraphicsContext gc = GameLogic.getGraphicsContext();
        gc.drawImage(super.getAssetImage(), coordinate.x, coordinate.y);
    }

    //TODO: Assign Heart type from specific value


}
