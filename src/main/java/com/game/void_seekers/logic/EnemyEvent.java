package com.game.void_seekers.logic;

import com.game.void_seekers.character.base.EnemyCharacter;
import com.game.void_seekers.character.base.GameCharacter;
import com.game.void_seekers.interfaces.Attack;

public class EnemyEvent implements Runnable {
    private static final long INTERVAL_MILLIS = 50;
    private static final long ATTACK_FRAME_MILLIS = 1000;
    private volatile boolean isRunning = true;

    @Override
    public void run() {
        while (isRunning) {
            for (EnemyCharacter enemy : GameLogic.getInstance().getCurrentRoom().getEnemyCharacters())
                if (enemy instanceof Attack && GameUtils.isCollided(GameLogic.getInstance().getCharacter(), enemy))
                    (new Thread(() -> {
                        try {
                            Thread.sleep(ATTACK_FRAME_MILLIS);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        GameLogic.getInstance().attack((Attack) enemy, GameLogic.getInstance().getCharacter());
                    })).start();

            try {
                Thread.sleep(INTERVAL_MILLIS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void kill() {
        isRunning = false;
    }
}
