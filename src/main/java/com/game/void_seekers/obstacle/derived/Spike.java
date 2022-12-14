package com.game.void_seekers.obstacle.derived;

import com.game.void_seekers.character.base.PlayableCharacter;
import com.game.void_seekers.interfaces.AffectPlayer;
import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import com.game.void_seekers.obstacle.base.Obstacle;
import javafx.scene.canvas.GraphicsContext;

public class Spike extends Obstacle {
    public Spike(int type) {
        super(0);
        if (type >= 0) setImage(GameAssets.loadImage(GameAssets.spike1URL, size));
        else setImage(GameAssets.loadImage(GameAssets.spike2URL, size));
    }

    @Override
    public void draw() {
        GraphicsContext gc = GameLogic.getGraphicsContext();
        gc.drawImage(super.getImage(), coordinates.x, coordinates.y);
    }
}
