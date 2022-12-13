package com.game.void_seekers.room.derived;

import com.game.void_seekers.character.base.EnemyCharacter;
import com.game.void_seekers.character.derived.EnemyGaper;
import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;

public class SpawnRoom extends NormalRoom {
    public SpawnRoom(int difficulty) {
        super(difficulty);
    }

    @Override
    public void generateRoom() {
        EnemyCharacter e0 = new EnemyGaper();
        e0.setCoordinate(GameLogic.MIDDLE_LEFT);
        enemyCharacters.add(e0);
    }
}
