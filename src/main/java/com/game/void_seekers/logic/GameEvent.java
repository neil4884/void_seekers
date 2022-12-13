package com.game.void_seekers.logic;

public class GameEvent implements Runnable {
    private volatile boolean isRunning = true;

    @Override
    public void run() {
        while (isRunning) {
            System.out.println("Game running");
        }
    }

    public void kill() {
        isRunning = false;
    }
}
