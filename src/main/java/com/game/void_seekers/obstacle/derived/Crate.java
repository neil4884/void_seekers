package com.game.void_seekers.obstacle.derived;

import com.game.void_seekers.interfaces.Destroyable;
import com.game.void_seekers.interfaces.Droppable;
import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import com.game.void_seekers.obstacle.base.Obstacle;
import javafx.scene.canvas.GraphicsContext;

public class Crate extends Obstacle implements Droppable, Destroyable {
    public Crate() {
        super(1);
        setSize(5);
        setImage(GameAssets.loadImage(GameAssets.crateURL, size));
    }

    @Override
    public void destroy() {
        //TODO: make crate destroy while contact with anything
    }

    @Override
    public void drop() {
        //TODO: make crate dropped random items
    }

    @Override
    public void draw() {
        GraphicsContext gc = GameLogic.getGraphicsContext();
        gc.drawImage(super.getImage(), coordinates.x, coordinates.y);
    }
}
