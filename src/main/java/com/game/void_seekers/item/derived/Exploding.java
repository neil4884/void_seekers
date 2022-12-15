package com.game.void_seekers.item.derived;

import com.game.void_seekers.item.base.Item;
import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import javafx.scene.canvas.GraphicsContext;

public class Exploding extends Item {
    public Exploding() {
        super("exploding");
        setSize(75);
        setCanNowPicked(false);
        setAssetImage(GameAssets.loadImage(GameAssets.bombURL, size));
    }

    @Override
    public void draw() {
        GraphicsContext gc = GameLogic.getGraphicsContext();
        gc.drawImage(super.getAssetImage(), coordinate.x, coordinate.y);
    }
}
