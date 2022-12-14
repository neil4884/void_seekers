package com.game.void_seekers.logic;

import com.game.void_seekers.character.base.EnemyCharacter;
import com.game.void_seekers.room.base.Room;
import com.game.void_seekers.room.base.RoomDirection;
import com.game.void_seekers.room.derived.SpawnRoom;

import java.util.ArrayList;

public class GameEvent implements Runnable {
    private static final long INTERVAL_MILLIS = 50;
    private volatile boolean isRunning = true;

    private void doorCollisionEvent() {

//      Check for player touching doors, if so, transition to next room
//      Top Room
        if (GameUtils.isCollided(
                GameLogic.getInstance().getCharacter(),
                GameLogic.TOP_DOOR,
                GameLogic.HORZ_DOOR_SIZE)) {

            GameLogic.getInstance().transitionToNextRoom(RoomDirection.TOP);
        }

//      Bottom Room
        else if (GameUtils.isCollided(
                GameLogic.getInstance().getCharacter(),
                GameLogic.BOTTOM_DOOR,
                GameLogic.HORZ_DOOR_SIZE)) {

            GameLogic.getInstance().transitionToNextRoom(RoomDirection.BOTTOM);
        }

//      Left Room
        else if (GameUtils.isCollided(
                GameLogic.getInstance().getCharacter(),
                GameLogic.LEFT_DOOR,
                GameLogic.VERT_DOOR_SIZE)) {

            GameLogic.getInstance().transitionToNextRoom(RoomDirection.LEFT);
        }

//      Right Room
        else if (GameUtils.isCollided(
                GameLogic.getInstance().getCharacter(),
                GameLogic.RIGHT_DOOR,
                GameLogic.VERT_DOOR_SIZE)) {

            GameLogic.getInstance().transitionToNextRoom(RoomDirection.RIGHT);
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            if (GameLogic.getInstance().getCharacter().isDead())
                GameLogic.getInstance().endGame();

//          Remove dead enemies
            GameLogic.getInstance().removeDeadEnemies(getDeadEnemies());

//          Door collision
            if (GameLogic.getInstance().getCurrentRoom().getEnemyCharacters().isEmpty())
                doorCollisionEvent();

            try {
                Thread.sleep(INTERVAL_MILLIS);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    private ArrayList<EnemyCharacter> getDeadEnemies() {
        ArrayList<EnemyCharacter> retVal = new ArrayList<>();
        for (EnemyCharacter enemy : GameLogic.getInstance().getCurrentRoom().getEnemyCharacters()) {
            if (enemy.isDead())
                retVal.add(enemy);
        }
        return retVal;
    }

    public void kill() {
        isRunning = false;
    }
}
