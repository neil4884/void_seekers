package com.game.void_seekers.obstacle.derived;

import com.game.void_seekers.obstacle.base.Obstacle;

public class Water extends Obstacle {
    private static final String waterURL = "water.png";
    public Water() {
        super(1, waterURL);
    }

    @Override
    public void draw() {

    }
}
