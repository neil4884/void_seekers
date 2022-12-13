package com.game.void_seekers.logic;

import com.game.void_seekers.character.base.EnemyCharacter;

import java.util.ArrayList;

public class GameEvent implements Runnable {
    private static final long INTERVAL_MILLIS = 1000;
    private volatile boolean isRunning = true;

    @Override
    public void run() {
        while (isRunning) {
            System.out.print("Character  ");
            System.out.print(GameLogic.getInstance().getCharacter().getCoordinate().toString() + "\t");
            GameLogic.getInstance().getCharacter().displayHealthBar();
            for (EnemyCharacter enemy : GameLogic.getInstance().getCurrentRoom().getEnemyCharacters()) {
                System.out.print("Test Enemy ");
                System.out.print(enemy.getCoordinate().toString() + "\t");
                enemy.displayHealthBar();
                System.out.println();
            }

//          Remove dead enemies
            GameLogic.getInstance().getCurrentRoom().getEnemyCharacters().removeAll(getDeadEnemy());

            try {
                Thread.sleep(INTERVAL_MILLIS);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    private ArrayList<EnemyCharacter> getDeadEnemy() {
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
