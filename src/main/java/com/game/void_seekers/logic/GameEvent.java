package com.game.void_seekers.logic;

public class GameEvent implements Runnable {
    private volatile boolean isRunning = true;

    @Override
    public void run() {
        while (isRunning) {
            System.out.println(GameLogic.getInstance().getCharacter().getCoordinate().toString());
            System.out.println(GameLogic.getInstance().getCurrentRoom().
                    getEnemyCharacters().get(0).getCoordinate().toString());
            System.out.println();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public void kill() {
        isRunning = false;
    }
}
