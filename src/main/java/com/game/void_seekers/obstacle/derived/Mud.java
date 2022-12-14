package com.game.void_seekers.obstacle.derived;

import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import com.game.void_seekers.obstacle.base.Obstacle;
import javafx.scene.canvas.GraphicsContext;

public class Mud extends Obstacle {
    public Mud() {
        super(0);
        setImage(GameAssets.loadImage(GameAssets.mudURL, size));
    }

    @Override
    public void draw() {
        GraphicsContext gc = GameLogic.getGraphicsContext();
        gc.drawImage(super.getImage(), coordinates.x, coordinates.y);
    }
}
