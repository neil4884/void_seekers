package com.game.void_seekers.logic;

import com.game.void_seekers.character.base.EnemyCharacter;
import com.game.void_seekers.room.base.Room;
import com.game.void_seekers.room.derived.SpawnRoom;

import java.util.ArrayList;

public class GameEvent implements Runnable {
    private static final long INTERVAL_MILLIS = 50;
    private volatile boolean isRunning = true;

    @Override
    public void run() {
        while (isRunning) {
            if (GameLogic.getInstance().getCharacter().isDead()) {
                GameLogic.getInstance().endGame();
            }

//          Remove dead enemies
            GameLogic.getInstance().removeDeadEnemies(getDeadEnemies());

//          Check for player touching doors, if so, transition to next room
            if (GameUtils.isCollided(GameLogic.getInstance().getCharacter(),
                    GameLogic.TOP_DOOR, GameLogic.HORZ_DOOR_SIZE)) {
                Room topRoom = new SpawnRoom(1);

                GameLogic.getInstance().getCurrentRoom().setTopRoom(topRoom);
                topRoom.setBottomRoom(GameLogic.getInstance().getCurrentRoom());

                GameLogic.getInstance().transitionToNextRoom(topRoom);
            }

            if (GameUtils.isCollided(GameLogic.getInstance().getCharacter(),
                    GameLogic.BOTTOM_DOOR, GameLogic.HORZ_DOOR_SIZE)) {
                System.out.println("Bottom door collided");
            }

            if (GameUtils.isCollided(GameLogic.getInstance().getCharacter(),
                    GameLogic.LEFT_DOOR, GameLogic.VERT_DOOR_SIZE)) {
                System.out.println("Left door collided");
            }

            if (GameUtils.isCollided(GameLogic.getInstance().getCharacter(),
                    GameLogic.RIGHT_DOOR, GameLogic.VERT_DOOR_SIZE)) {
                System.out.println("Right door collided");
            }

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
