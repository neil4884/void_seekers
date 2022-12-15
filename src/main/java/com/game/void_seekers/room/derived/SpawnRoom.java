package com.game.void_seekers.room.derived;

import com.game.void_seekers.item.derived.Bomb;
import com.game.void_seekers.logic.GameUtils;

public class SpawnRoom extends NormalRoom {
    public SpawnRoom(int difficulty) {
        super(difficulty);
    }

    @Override
    public void generateRoom() {
        generateObstacles();

        for (int i = 0; i < 3; ++i) {
            Bomb bomb = new Bomb();
            bomb.setCoordinate(GameUtils.coordinatesRandomizer());
            items.add(bomb);
        }
    }
}
