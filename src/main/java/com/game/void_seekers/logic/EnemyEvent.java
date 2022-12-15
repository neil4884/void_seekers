package com.game.void_seekers.logic;

import com.game.void_seekers.character.base.EnemyCharacter;
import com.game.void_seekers.interfaces.Attack;

public class EnemyEvent implements Runnable {
    private static final long INTERVAL_MILLIS = 50;
    private static final long ATTACK_FRAME_MILLIS = 1000;
    private static final long WAIT_FRAME_MILLIS = 1500;
    private volatile boolean isRunning = true;

    @Override
    public void run() {
        while (isRunning) {
            for (EnemyCharacter enemy : GameLogic.getInstance().getCurrentRoom().getEnemyCharacters()) {
                if (!enemy.isAttacking() &&
                        enemy instanceof Attack &&
                        GameUtils.isCollided(GameLogic.getInstance().getCharacter(), enemy)) {

                    Thread subEnemy = new Thread(() -> {
                        try {
                            Thread.sleep(ATTACK_FRAME_MILLIS);
                        } catch (InterruptedException ignored) {
                        }

                        if (enemy.isDead())
                            return;

                        GameLogic.getInstance().attack((Attack) enemy, GameLogic.getInstance().getCharacter());

                        enemy.setAttacking(true);
                        try {
                            Thread.sleep(WAIT_FRAME_MILLIS);
                        } catch (InterruptedException ignored) {
                        }
                        enemy.setAttacking(false);
                    });
                    subEnemy.start();
                }
            }

            try {
                Thread.sleep(INTERVAL_MILLIS);
            } catch (InterruptedException ignored) {
            }
        }
    }

    public void kill() {
        isRunning = false;
    }
}
