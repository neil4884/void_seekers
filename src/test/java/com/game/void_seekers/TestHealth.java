package com.game.void_seekers;

import com.game.void_seekers.character.derived.CharacterHealth;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestHealth {

    CharacterHealth health;
    CharacterHealth health1;
    CharacterHealth health2;

    @BeforeEach
    void setUp() {
        health = new CharacterHealth(6, 0);
        health1 = new CharacterHealth(6, 1);
        health2 = new CharacterHealth(4, 0, 2, 1);
    }

    @Test
    void testConstructor() {
        assertEquals(6, health.getRedHealth());
        assertEquals(6, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(14, health.getMaxBlueHealth());
    }
    @Test
    void testAddInvalidRedHealth() {
        health.addHealth(3, 0);
        assertEquals(6, health.getRedHealth());
        assertEquals(6, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(14, health.getMaxBlueHealth());
    }
    @Test
    void testAddHeartHealth() {
        health.addRedHeartContainers(1);
        assertEquals(6, health.getRedHealth());
        assertEquals(8, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(12, health.getMaxBlueHealth());
        health.fullyHeal();
        assertEquals(8, health.getRedHealth());
        assertEquals(8, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(12, health.getMaxBlueHealth());
        health.decreaseRedHealth(3);
        assertEquals(5, health.getRedHealth());
        assertEquals(8, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(12, health.getMaxBlueHealth());
        health.removeRedHeartContainers(2);
        assertEquals(4, health.getRedHealth());
        assertEquals(4, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(16, health.getMaxBlueHealth());
        health.addRedHeartContainers(1);
        assertEquals(4, health.getRedHealth());
        assertEquals(6, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(14, health.getMaxBlueHealth());
        health.fullyHeal();
        assertEquals(6, health.getRedHealth());
        assertEquals(6, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(14, health.getMaxBlueHealth());
    }
    @Test
    void BlueHealthAdd() {
        assertEquals(0, health1.getRedHealth());
        assertEquals(0, health1.getMaxRedHealth());
        assertEquals(6, health1.getBlueHealth());
        assertEquals(20, health1.getMaxBlueHealth());
        health1.addBlueHealth(4);
        assertEquals(0, health1.getRedHealth());
        assertEquals(0, health1.getMaxRedHealth());
        assertEquals(10, health1.getBlueHealth());
        assertEquals(20, health1.getMaxBlueHealth());
    }
    @Test
    void RedHealthRemoveInBlueHealth() {
        assertEquals(0, health1.getRedHealth());
        assertEquals(0, health1.getMaxRedHealth());
        assertEquals(6, health1.getBlueHealth());
        assertEquals(20, health1.getMaxBlueHealth());
        health1.decreaseRedHealth(10);
        assertEquals(0, health1.getRedHealth());
        assertEquals(0, health1.getMaxRedHealth());
        assertEquals(6, health1.getBlueHealth());
        assertEquals(20, health1.getMaxBlueHealth());
        health1.removeRedHeartContainers(1);
        assertEquals(0, health1.getRedHealth());
        assertEquals(0, health1.getMaxRedHealth());
        assertEquals(6, health1.getBlueHealth());
        assertEquals(20, health1.getMaxBlueHealth());
    }
    @Test
    void addRedHearthWithBlueHealth() {
        assertEquals(0, health1.getRedHealth());
        assertEquals(0, health1.getMaxRedHealth());
        assertEquals(6, health1.getBlueHealth());
        assertEquals(20, health1.getMaxBlueHealth());
        health1.addHealth(2, 0);
        assertEquals(0, health1.getRedHealth());
        assertEquals(0, health1.getMaxRedHealth());
        assertEquals(6, health1.getBlueHealth());
        assertEquals(20, health1.getMaxBlueHealth());
        health1.addRedHeartContainers(2);
        assertEquals(0, health1.getRedHealth());
        assertEquals(4, health1.getMaxRedHealth());
        assertEquals(6, health1.getBlueHealth());
        assertEquals(16, health1.getMaxBlueHealth());
        health1.addHealth(1, 0);
        assertEquals(1, health1.getRedHealth());
        assertEquals(4, health1.getMaxRedHealth());
        assertEquals(6, health1.getBlueHealth());
        assertEquals(16, health1.getMaxBlueHealth());
    }
    @Test
    void addExceedRedHeart() {
        assertEquals(6, health.getRedHealth());
        assertEquals(6, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(14, health.getMaxBlueHealth());
        health.addRedHeartContainers(10);
        assertEquals(6, health.getRedHealth());
        assertEquals(20, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(0, health.getMaxBlueHealth());
        health.fullyHeal();
        assertEquals(20, health.getRedHealth());
        assertEquals(20, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(0, health.getMaxBlueHealth());
        health.addHealth(1, 0);
        assertEquals(20, health.getRedHealth());
        assertEquals(20, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(0, health.getMaxBlueHealth());
    }
    @Test
    void addExceedBlueHeart() {
        assertEquals(0, health1.getRedHealth());
        assertEquals(0, health1.getMaxRedHealth());
        assertEquals(6, health1.getBlueHealth());
        assertEquals(14, health1.getMaxBlueHealth());
        health1.addHealth(14, 1);
        assertEquals(0, health1.getRedHealth());
        assertEquals(0, health1.getMaxRedHealth());
        assertEquals(20, health1.getBlueHealth());
        assertEquals(20, health1.getMaxBlueHealth());
        health1.addHealth(1, 1);
        assertEquals(0, health1.getRedHealth());
        assertEquals(0, health1.getMaxRedHealth());
        assertEquals(20, health1.getBlueHealth());
        assertEquals(20, health1.getMaxBlueHealth());
    }

    @Test
    void dead() {
        assertEquals(6, health.getRedHealth());
        assertEquals(6, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(14, health.getMaxBlueHealth());
        health.addHealth(2, 1);
        assertEquals(6, health.getRedHealth());
        assertEquals(6, health.getMaxRedHealth());
        assertEquals(2, health.getBlueHealth());
        assertEquals(14, health.getMaxBlueHealth());
        health.decreaseRedHealth(6);
        assertEquals(0, health.getRedHealth());
        assertEquals(6, health.getMaxRedHealth());
        assertEquals(2, health.getBlueHealth());
        assertEquals(14, health.getMaxBlueHealth());
        assertFalse(health.isDead());
        health.decreaseBlueHealth(2);
        assertEquals(0, health.getRedHealth());
        assertEquals(6, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(14, health.getMaxBlueHealth());
        assertTrue(health.isDead());
    }

    @Test
    void reduceHealthMethod() {
        assertEquals(0, health1.getRedHealth());
        assertEquals(0, health1.getMaxRedHealth());
        assertEquals(6, health1.getBlueHealth());
        assertEquals(20, health1.getMaxBlueHealth());
        health.addRedHeartContainers(1);
        assertEquals(2, health1.getRedHealth());
        assertEquals(2, health1.getMaxRedHealth());
        assertEquals(6, health1.getBlueHealth());
        assertEquals(18, health1.getMaxBlueHealth());
        health.reduceHealth(4);
        assertEquals(2, health1.getRedHealth());
        assertEquals(2, health1.getMaxRedHealth());
        assertEquals(2, health1.getBlueHealth());
        assertEquals(18, health1.getMaxBlueHealth());
        health.reduceHealth(2);
        assertEquals(2, health1.getRedHealth());
        assertEquals(2, health1.getMaxRedHealth());
        assertEquals(0, health1.getBlueHealth());
        assertEquals(18, health1.getMaxBlueHealth());
        assertFalse(health.isDead());
        health.reduceHealth(2);
        assertEquals(0, health1.getRedHealth());
        assertEquals(2, health1.getMaxRedHealth());
        assertEquals(0, health1.getBlueHealth());
        assertEquals(18, health1.getMaxBlueHealth());
        assertTrue(health.isDead());
    }


}
