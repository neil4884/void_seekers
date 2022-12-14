package com.game.void_seekers.room.derived;

import com.game.void_seekers.character.base.EnemyCharacter;
import com.game.void_seekers.character.derived.EnemyGaper;
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
        e0.setCoordinate(GameLogic.MIDDLE_LEFT.add(new Coordinates(160, 0)));
        e1.setCoordinate(GameLogic.MIDDLE_CENTER.add(new Coordinates(160, 0)));
        enemyCharacters.add(e0);
        enemyCharacters.add(e1);
    }
}
