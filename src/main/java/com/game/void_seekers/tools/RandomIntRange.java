package com.game.void_seekers.tools;

public class RandomIntRange {
    public int result;
    public int rMin;
    public int rMax;

    public RandomIntRange(int min, int max) {
        rMin = min;
        rMax = max;

        this.result = min + (int) (Math.random() * ((max - min) + 1));
    }

    public int getResult() {
        return result;
    }

    public void createNewResult(int min, int max) {
        this.result = min + (int) (Math.random() * ((max - min) + 1));
    }

    public int next() {
        createNewResult(rMin, rMax);
        return result;
    }
}
