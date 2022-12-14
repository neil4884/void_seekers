package com.game.void_seekers.obstacle.derived;

import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import com.game.void_seekers.obstacle.base.Obstacle;
import javafx.scene.canvas.GraphicsContext;

public class Exploding extends Obstacle {
    public Exploding() {
        super(1);
        setSize(GameLogic.CHARACTER_SIZE_DEFAULT / 2);
        setImage(GameAssets.loadImage(GameAssets.bombURL, size));
    }

    @Override
    public void draw() {
        GraphicsContext gc = GameLogic.getGraphicsContext();
        gc.drawImage(super.getImage(), coordinates.x, coordinates.y);
    }
}
