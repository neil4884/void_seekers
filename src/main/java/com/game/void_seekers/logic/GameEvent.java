package com.game.void_seekers.logic;

public class GameEvent implements Runnable {
    private static final long INTERVAL_MILLIS = 1000;
    private volatile boolean isRunning = true;

    @Override
    public void run() {
        while (isRunning) {
            System.out.print("Character  ");
            System.out.println(GameLogic.getInstance().getCharacter().getCoordinate().toString());
            System.out.print("Test Enemy ");
            System.out.println(GameLogic.getInstance().getCurrentRoom().
                    getEnemyCharacters().get(0).getCoordinate().toString());
            System.out.println();

            try {
                Thread.sleep(INTERVAL_MILLIS);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public void kill() {
        isRunning = false;
    }
}
