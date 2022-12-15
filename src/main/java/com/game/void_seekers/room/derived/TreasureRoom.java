package com.game.void_seekers.room.derived;

import com.game.void_seekers.item.base.EffectItem;
import com.game.void_seekers.logic.GameLogic;
import com.game.void_seekers.logic.GameUtils;
import com.game.void_seekers.obstacle.derived.Crate;
import com.game.void_seekers.tools.Coordinates;

public class TreasureRoom extends NormalRoom {
    public TreasureRoom(int difficulty) {
        super(difficulty);
    }

    @Override
    public void generateRoom() {
        Crate pedestal = new Crate();
        pedestal.setCoordinates(GameLogic.MIDDLE_CENTER.add(0, 0));
        obstacles.add(pedestal);
        EffectItem item = GameUtils.getRandomEffectItem();
        item.setCoordinate(new Coordinates(0, pedestal.getSize()));
        items.add(item);
    }
}
