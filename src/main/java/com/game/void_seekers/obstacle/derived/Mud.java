package com.game.void_seekers.obstacle.derived;

import com.game.void_seekers.interfaces.Slowable;
import com.game.void_seekers.obstacle.base.Obstacle;

public class Mud extends Obstacle implements Slowable {
    public static final String mudURL = "mud.png";
    public Mud() {
        super(0, mudURL);
    }

    @Override
    public void slow() {
        
    }
}
