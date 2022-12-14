package com.game.void_seekers.obstacle.derived;

import com.game.void_seekers.interfaces.Destroyable;
import com.game.void_seekers.interfaces.Droppable;
import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.obstacle.base.Obstacle;

public class Boulder extends Obstacle implements Destroyable, Droppable {
    //TODO: implements later
    private int type;

    public Boulder(int type) {
        super(1);
        switch (type) {

        }
        setAssetImage(GameAssets.loadImage(GameAssets.bombURL, size));

    }

    @Override
    public void destroy() {
        //TODO: Create drop event and remove the bolder
    }

    @Override
    public void drop() {
        //TODO: Made drop item
    }

    @Override
    public void draw() {

    }
}
