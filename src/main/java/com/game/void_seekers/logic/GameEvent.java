package com.game.void_seekers.logic;

import com.game.void_seekers.character.base.EnemyCharacter;

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
            System.out.print("Character  ");
            System.out.println(GameLogic.getInstance().getCharacter().getCoordinate().toString());
            GameLogic.getInstance().getCharacter().displayHealthBar();
            for (EnemyCharacter enemy : GameLogic.getInstance().getCurrentRoom().getEnemyCharacters()) {
                System.out.print("Test Enemy ");
                System.out.println(enemy.getCoordinate().toString());
                enemy.displayHealthBar();
                System.out.println();
            }

//          Remove dead enemies
            GameLogic.getInstance().removeDeadEnemies(getDeadEnemies());

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
