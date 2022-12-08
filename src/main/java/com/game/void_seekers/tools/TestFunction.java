package com.game.void_seekers.tools;

import com.game.void_seekers.character.base.Health;
import com.game.void_seekers.character.base.HealthStatus;

public class TestFunction {
    public static void main(String[] args) {
        TestHealth health = new TestHealth();
        System.out.println(health.getMaxHealth());
        System.out.println(health.getHealth());
        health.displayHealthBar();
        health.setStatus(HealthStatus.BLUE);
        health.reduceHealth(3);
        System.out.println(health.getMaxHealth());
        System.out.println(health.getHealth());
        health.displayHealthBar();
    }
}

class TestHealth extends Health {
    public TestHealth() {
        super(8);
    }
}