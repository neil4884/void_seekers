package com.game.void_seekers.logic;

import com.game.void_seekers.character.base.EnemyCharacter;
import com.game.void_seekers.character.base.GameCharacter;
import com.game.void_seekers.character.base.PlayableCharacter;
import com.game.void_seekers.item.base.Item;
import com.game.void_seekers.room.base.Room;
import com.game.void_seekers.room.base.RoomDirection;
import com.game.void_seekers.room.derived.SpawnRoom;
import com.game.void_seekers.tools.Coordinates;

import java.util.ArrayList;

public class GameEvent implements Runnable {
    private static final long INTERVAL_MILLIS = 50;
    private static final long STROBE_HEALTH_MILLIS = 200;
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

    private void itemCollisionEvent() {
        ArrayList<Item> remove = new ArrayList<>();
        for (Item item : GameLogic.getInstance().getCurrentRoom().getItems()) {
            if (GameUtils.isCollided(GameLogic.getInstance().getCharacter(), item)) {
                GameLogic.getInstance().getCharacter().add(item);
                remove.add(item);
            }
        }
        for (Item item : remove) {
            GameLogic.getInstance().getCurrentRoom().getItems().remove(item);
        }
    }

    @Override
    public void run() {
        Thread lowHealthWatcher = new Thread(() -> {
            while (isRunning) {
                try {
                    while (GameLogic.getInstance().getCharacter().getAbsoluteTotalHealth() <= 2) {
                        GameLogic.getInstance().getHealthBar().setStrobeHealth(true);
                        Thread.sleep(STROBE_HEALTH_MILLIS);
                        GameLogic.getInstance().getHealthBar().setStrobeHealth(false);
                        Thread.sleep(STROBE_HEALTH_MILLIS);
                    }

                    GameLogic.getInstance().getHealthBar().setStrobeHealth(false);
                    Thread.sleep(INTERVAL_MILLIS);

                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        lowHealthWatcher.start();

        while (isRunning) {
            if (GameLogic.getInstance().getCharacter().isDead())
                GameLogic.getInstance().endGame();
//          Item detection
            itemCollisionEvent();

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
