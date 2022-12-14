package com.game.void_seekers.room.derived;

import com.game.void_seekers.character.base.EnemyCharacter;
import com.game.void_seekers.character.derived.EnemyGaper;
import com.game.void_seekers.item.active.bookOfRage;
import com.game.void_seekers.item.base.Active;
import com.game.void_seekers.item.derived.Bomb;
import com.game.void_seekers.logic.GameLogic;
import com.game.void_seekers.tools.Coordinates;

public class SpawnRoom extends NormalRoom {
    public SpawnRoom(int difficulty) {
        super(difficulty);
    }

    @Override
    public void generateRoom() {
        EnemyCharacter e0 = new EnemyGaper();
        EnemyCharacter e1 = new EnemyGaper();
        e0.setCoordinate(GameLogic.MIDDLE_LEFT.add(160, 0));
        e1.setCoordinate(GameLogic.MIDDLE_CENTER.add(160, 0));
        enemyCharacters.add(e0);
        enemyCharacters.add(e1);
        Active item1 = new bookOfRage();
        item1.setCoordinate(GameLogic.MIDDLE_CENTER.add(160, 0));
        items.add(item1);
        Bomb bomb = new Bomb();
        bomb.setCoordinate(GameLogic.MIDDLE_CENTER.add(111, 0));
        items.add(bomb);
    }
}
