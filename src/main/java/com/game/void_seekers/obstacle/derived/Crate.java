package com.game.void_seekers.obstacle.derived;

import com.game.void_seekers.interfaces.Destroyable;
import com.game.void_seekers.interfaces.Droppable;
import com.game.void_seekers.obstacle.base.Obstacle;

public class Crate extends Obstacle implements Droppable, Destroyable {
    private static final String crateURL = "crate.png";
    public Crate() {
        super(1, "crate.png");
    }

    @Override
    public void destroy() {
        //TODO: make crate destroy while contact with anything
    }

    @Override
    public void drop() {
        //TODO: make crate dropped random items
    }
}
