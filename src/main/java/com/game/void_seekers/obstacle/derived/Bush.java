package com.game.void_seekers.obstacle.derived;

import com.game.void_seekers.interfaces.Draw;
import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import com.game.void_seekers.obstacle.base.Obstacle;
import javafx.scene.canvas.GraphicsContext;

public class Bush extends Obstacle implements Draw {
    public final int type;

    public Bush(int type) {
        super(1);
        this.type = type;
        if (type == 0) {
            setImage(GameAssets.loadImage(GameAssets.bush1URL, size));
        } else {
            setImage(GameAssets.loadImage(GameAssets.bush2URL, size));
        }
    }

    public Bush(Bush other) {
        super(1);
        this.type = other.type;
    }

    @Override
    public void draw() {
        GraphicsContext gc = GameLogic.getGraphicsContext();
        gc.drawImage(super.getImage(), coordinates.x, coordinates.y);
    }
}
