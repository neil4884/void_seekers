package com.game.void_seekers;


import com.game.void_seekers.character.derived.CharacterHealth;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHealth {

    CharacterHealth health;
    CharacterHealth health2;
    CharacterHealth health3;
    CharacterHealth health4;

    @BeforeEach
    void setUp() {
        health = new CharacterHealth(6, 0);
        health2 = new CharacterHealth(6, 0);
        health2.addRedHealth(3);
        health3 = new CharacterHealth(6, 0);
        health3.addRedHeartContainers(1);
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
        assertEquals(6, health.getRedHealth());
        assertEquals(6, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(14, health.getMaxBlueHealth());
    }
    @Test
    void testAddHeartHealth() {
        assertEquals(6, health3.getRedHealth());
        assertEquals(8, health3.getMaxRedHealth());
        assertEquals(0, health3.getBlueHealth());
        assertEquals(12, health3.getMaxBlueHealth());
        health3.fullyHeal();
        assertEquals(8, health3.getRedHealth());
        assertEquals(8, health3.getMaxRedHealth());
        assertEquals(0, health3.getBlueHealth());
        assertEquals(12, health3.getMaxBlueHealth());
        health3.decreaseRedHealth(3);
        assertEquals(5, health3.getRedHealth());
        assertEquals(8, health3.getMaxRedHealth());
        assertEquals(0, health3.getBlueHealth());
        assertEquals(12, health3.getMaxBlueHealth());
        health3.removeRedHeartContainers(2);
        assertEquals(4, health3.getRedHealth());
        assertEquals(4, health3.getMaxRedHealth());
        assertEquals(0, health3.getBlueHealth());
        assertEquals(16, health3.getMaxBlueHealth());
        health3.addRedHeartContainers(1);
        assertEquals(4, health3.getRedHealth());
        assertEquals(6, health3.getMaxRedHealth());
        assertEquals(0, health3.getBlueHealth());
        assertEquals(14, health3.getMaxBlueHealth());
        health3.fullyHeal();
        assertEquals(6, health3.getRedHealth());
        assertEquals(6, health3.getMaxRedHealth());
        assertEquals(0, health3.getBlueHealth());
        assertEquals(14, health3.getMaxBlueHealth());
    }



}
