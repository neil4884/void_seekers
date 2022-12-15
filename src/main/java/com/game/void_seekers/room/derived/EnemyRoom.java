package com.game.void_seekers.room.derived;

import com.game.void_seekers.item.active.BookOfRage;
import com.game.void_seekers.item.base.Active;
import com.game.void_seekers.item.base.Item;
import com.game.void_seekers.item.derived.Bomb;
import com.game.void_seekers.logic.GameUtils;

public class EnemyRoom extends NormalRoom {
    public EnemyRoom(int difficulty) {
        super(difficulty);
    }

    @Override
    public void generateRoom() {
        generateEnemies();

        Active item1 = new BookOfRage();
        item1.setCoordinate(GameUtils.coordinatesRandomizer());
        items.add(item1);

        Bomb bomb = new Bomb();
        bomb.setCoordinate(GameUtils.coordinatesRandomizer());
        items.add(bomb);

        Item item = GameUtils.getRandomEffectItem();
        item.setCoordinate(GameUtils.coordinatesRandomizer());
        items.add(item);
    }
}
