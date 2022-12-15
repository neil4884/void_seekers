package com.game.void_seekers.obstacle.derived;

import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import com.game.void_seekers.obstacle.base.Obstacle;
import javafx.scene.canvas.GraphicsContext;

public class Spike extends Obstacle {
    public final int type;

    public Spike(int type) {
        super(0);
        this.type = type;
        if (type >= 0) setImage(GameAssets.loadImage(GameAssets.spike1URL, size));
        else setImage(GameAssets.loadImage(GameAssets.spike2URL, size));
    }

    public Spike(Spike other) {
        super(0);
        this.type = other.type;
    }

    @Override
    public void draw() {
        GraphicsContext gc = GameLogic.getGraphicsContext();
        gc.drawImage(super.getImage(), coordinates.x, coordinates.y);
    }
}
