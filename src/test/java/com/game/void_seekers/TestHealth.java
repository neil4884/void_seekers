package com.game.void_seekers;

import com.game.void_seekers.character.base.CharacterHealth;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestHealth {

    CharacterHealth health;
    CharacterHealth healthAb;
    CharacterHealth health1;
    CharacterHealth health2;

    @BeforeEach
    void setUp() {
        health = new CharacterHealth(6, 0);
        healthAb = new CharacterHealth(5, 0);
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
    void testAddNonFullyContainerHealth() {
        assertEquals(5, healthAb.getRedHealth());
        assertEquals(6, healthAb.getMaxRedHealth());
        assertEquals(0, healthAb.getBlueHealth());
        assertEquals(14, healthAb.getMaxBlueHealth());
    }
    @Test
    void testAddNonFullyContainerHealth2() {
        CharacterHealth tempChr = new CharacterHealth(3, 0);
        assertEquals(3, tempChr.getRedHealth());
        assertEquals(4, tempChr.getMaxRedHealth());
        assertEquals(0, tempChr.getBlueHealth());
        assertEquals(16, tempChr.getMaxBlueHealth());
    }
    @Test
    void addHealthToNonFullyHealHeart() {
        healthAb.addHealth(1, 0);
        assertEquals(6, healthAb.getRedHealth());
        assertEquals(6, healthAb.getMaxRedHealth());
        assertEquals(0, healthAb.getBlueHealth());
        assertEquals(14, healthAb.getMaxBlueHealth());
    }
    @Test
    void addHealthExceedNonFullyHealHeart() {
        CharacterHealth tempChr = new CharacterHealth(3, 0);
        tempChr.addHealth(9, 0);
        assertEquals(4, tempChr.getRedHealth());
        assertEquals(4, tempChr.getMaxRedHealth());
        assertEquals(0, tempChr.getBlueHealth());
        assertEquals(16, tempChr.getMaxBlueHealth());
    }
    @Test
    void testAddHeartHealth() {
        health.addRedHeartContainers(1);
        assertEquals(6, health.getRedHealth());
        assertEquals(8, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(12, health.getMaxBlueHealth());
    }
    @Test
    void testDamageRedHealth() {
        health.addRedHeartContainers(1);
        assertEquals(6, health.getRedHealth());
        assertEquals(8, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(12, health.getMaxBlueHealth());
        health.decreaseRedHealth(3);
        assertEquals(3, health.getRedHealth());
        assertEquals(8, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(12, health.getMaxBlueHealth());
    }
    @Test
    void removedRedHeartContainers() {
        health.addRedHeartContainers(2);
        assertEquals(6, health.getRedHealth());
        assertEquals(10, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(10, health.getMaxBlueHealth());
        health.removeRedHeartContainers(1);
        assertEquals(6, health.getRedHealth());
        assertEquals(8, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(12, health.getMaxBlueHealth());
    }
    @Test
    void removedWithCharacterHealthReduced() {
        health.removeRedHeartContainers(1);
        assertEquals(4, health.getRedHealth());
        assertEquals(4, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(16, health.getMaxBlueHealth());
    }
    @Test
    void removedAllRedHeath() {
        health.removeRedHeartContainers(99);
        assertEquals(0, health.getRedHealth());
        assertEquals(0, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(20, health.getMaxBlueHealth());
    }
    @Test
    void removedNoneRedHealth() {
        health.removeRedHeartContainers(0);
        assertEquals(6, health.getRedHealth());
        assertEquals(6, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(14, health.getMaxBlueHealth());
    }
    @Test
    void testFullyHeal() {
        health.addRedHeartContainers(2);
        assertEquals(6, health.getRedHealth());
        assertEquals(10, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(10, health.getMaxBlueHealth());
        health.fullyHeal();
        assertEquals(10, health.getRedHealth());
        assertEquals(10, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(10, health.getMaxBlueHealth());
    }
    @Test
    void testFullyHeal2() {
        health.decreaseRedHealth(5);
        assertEquals(1, health.getRedHealth());
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
    void testAddFullyHealRedHeartContainers() {
        healthAb.addRedHeartContainers(1);
        assertEquals(5, healthAb.getRedHealth());
        assertEquals(8, healthAb.getMaxRedHealth());
        assertEquals(0, healthAb.getBlueHealth());
        assertEquals(12, healthAb.getMaxBlueHealth());
        healthAb.addFullyHealRedHeartContainers(1);
        assertEquals(7, healthAb.getRedHealth());
        assertEquals(10, healthAb.getMaxRedHealth());
        assertEquals(0, healthAb.getBlueHealth());
        assertEquals(10, healthAb.getMaxBlueHealth());
    }
    @Test
    void testExceedHeartContainers() {
        health.addFullyHealRedHeartContainers(7);
        assertEquals(20, health.getRedHealth());
        assertEquals(20, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(0, health.getMaxBlueHealth());
        health.addFullyHealRedHeartContainers(1);
        assertEquals(20, health.getRedHealth());
        assertEquals(20, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(0, health.getMaxBlueHealth());
        health.addRedHeartContainers(1);
        assertEquals(20, health.getRedHealth());
        assertEquals(20, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(0, health.getMaxBlueHealth());
    }
    @Test
    void numberOfHeartContainers() {
        assertEquals(3, health.getRedHeartContainers());
        health.addFullyHealRedHeartContainers(2);
        assertEquals(5, health.getRedHeartContainers());
        health.removeRedHeartContainers(1);
        assertEquals(4, health.getRedHeartContainers());
        health.decreaseRedHealth(3);
        assertEquals(4, health.getRedHeartContainers());
    }
    @Test
    void testBlueHealthConstructor() {
        assertEquals(0, health1.getRedHealth());
        assertEquals(0, health1.getMaxRedHealth());
        assertEquals(6, health1.getBlueHealth());
        assertEquals(20, health1.getMaxBlueHealth());
    }
    @Test
    void testBlueHealthAdd() {
        health1.addHealth(4, 1);
        assertEquals(0, health1.getRedHealth());
        assertEquals(0, health1.getMaxRedHealth());
        assertEquals(10, health1.getBlueHealth());
        assertEquals(20, health1.getMaxBlueHealth());
    }
    @Test
    void testRemoveInvalidRedHealthRemoveInBlueHealth() {
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
        assertEquals(20, health1.getMaxBlueHealth());
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
    void addRedHeartDuringFullyBlueHeart() {
        assertEquals(0, health1.getRedHealth());
        assertEquals(0, health1.getMaxRedHealth());
        assertEquals(6, health1.getBlueHealth());
        assertEquals(20, health1.getMaxBlueHealth());
        health1.addHealth(14, 1);
        assertEquals(0, health1.getRedHealth());
        assertEquals(0, health1.getMaxRedHealth());
        assertEquals(20, health1.getBlueHealth());
        assertEquals(20, health1.getMaxBlueHealth());
        health1.addFullyHealRedHeartContainers(1);
        assertEquals(2, health1.getRedHealth());
        assertEquals(2, health1.getMaxRedHealth());
        assertEquals(18, health1.getBlueHealth());
        assertEquals(18, health1.getMaxBlueHealth());
    }
    @Test
    void addBlueHealthDuringFullyRedHeart() {
        assertEquals(6, health.getRedHealth());
        assertEquals(6, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(14, health.getMaxBlueHealth());
        health.addFullyHealRedHeartContainers(5);
        assertEquals(16, health.getRedHealth());
        assertEquals(16, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(4, health.getMaxBlueHealth());
        health.addRedHeartContainers(4);
        assertEquals(16, health.getRedHealth());
        assertEquals(20, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(0, health.getMaxBlueHealth());
        health.addHealth(4, 0);
        assertEquals(20, health.getRedHealth());
        assertEquals(20, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(0, health.getMaxBlueHealth());
        health.addHealth(42, 1);
        assertEquals(20, health.getRedHealth());
        assertEquals(20, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(0, health.getMaxBlueHealth());
    }
    @Test
    void testDecreaseBlueHeart() {
        health1.decreaseBlueHealth(4);
        assertEquals(0, health1.getRedHealth());
        assertEquals(0, health1.getMaxRedHealth());
        assertEquals(2, health1.getBlueHealth());
        assertEquals(20, health1.getMaxBlueHealth());
    }
    @Test
    void testDecreaseBlueHeartExceeded() {
        assertEquals(0, health1.getRedHealth());
        assertEquals(0, health1.getMaxRedHealth());
        assertEquals(6, health1.getBlueHealth());
        assertEquals(20, health1.getMaxBlueHealth());
        health1.decreaseBlueHealth(4);
        assertEquals(0, health1.getRedHealth());
        assertEquals(0, health1.getMaxRedHealth());
        assertEquals(2, health1.getBlueHealth());
        assertEquals(20, health1.getMaxBlueHealth());
        health1.addRedHeartContainers(1);
        health1.addHealth(1, 0);
        assertEquals(1, health1.getRedHealth());
        assertEquals(2, health1.getMaxRedHealth());
        assertEquals(2, health1.getBlueHealth());
        assertEquals(18, health1.getMaxBlueHealth());
        health1.decreaseBlueHealth(4);
        assertEquals(1, health1.getRedHealth());
        assertEquals(2, health1.getMaxRedHealth());
        assertEquals(0, health1.getBlueHealth());
        assertEquals(18, health1.getMaxBlueHealth());
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
    void reduceRedHeart() {
        health1.addFullyHealRedHeartContainers(5);
        assertEquals(10, health1.getRedHealth());
        assertEquals(10, health1.getMaxRedHealth());
        assertEquals(6, health1.getBlueHealth());
        assertEquals(10, health1.getMaxBlueHealth());
        health1.reduceHealth(12);
        assertEquals(4, health1.getRedHealth());
        assertEquals(10, health1.getMaxRedHealth());
        assertEquals(0, health1.getBlueHealth());
        assertEquals(10, health1.getMaxBlueHealth());
        health1.addHealth(2, 0);
        assertEquals(6, health1.getRedHealth());
        assertEquals(10, health1.getMaxRedHealth());
        assertEquals(0, health1.getBlueHealth());
        assertEquals(10, health1.getMaxBlueHealth());
        health1.addHealth(2, 1);
        assertEquals(6, health1.getRedHealth());
        assertEquals(10, health1.getMaxRedHealth());
        assertEquals(2, health1.getBlueHealth());
        assertEquals(10, health1.getMaxBlueHealth());
        health1.reduceHealth(8);
        assertEquals(0, health1.getRedHealth());
        assertEquals(10, health1.getMaxRedHealth());
        assertEquals(0, health1.getBlueHealth());
        assertEquals(10, health1.getMaxBlueHealth());
    }

    @Test
    void testHealthConstructorSimultaneous() {
        assertEquals(4, health2.getRedHealth());
        assertEquals(4, health2.getMaxRedHealth());
        assertEquals(2, health2.getBlueHealth());
        assertEquals(16, health2.getMaxBlueHealth());
    }
    @Test
    void testDamageSimultaneousHealth() {
        health2.reduceHealth(4);
        assertEquals(2, health2.getRedHealth());
        assertEquals(4, health2.getMaxRedHealth());
        assertEquals(0, health2.getBlueHealth());
        assertEquals(16, health2.getMaxBlueHealth());
    }
    @Test
    void testSetMaxHealth() {
        health.setMaxHealth(100);
        assertEquals(100, health.getMaxHealth());
        assertEquals(6, health.getRedHealth());
        assertEquals(6, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(94, health.getMaxBlueHealth());
    }
    @Test
    void testEnemyHealthConstructor() {
        CharacterHealth enemyHealth = new CharacterHealth(100);
        assertEquals(100, enemyHealth.getMaxHealth());
        assertEquals(0, enemyHealth.getRedHealth());
        assertEquals(0, enemyHealth.getMaxRedHealth());
        assertEquals(100, enemyHealth.getBlueHealth());
        assertEquals(100, enemyHealth.getMaxBlueHealth());
    }
    @Test
    void testEnemyHealthDamage() {
        CharacterHealth enemyHealth = new CharacterHealth(100);
        assertEquals(100, enemyHealth.getMaxHealth());
        assertEquals(0, enemyHealth.getRedHealth());
        assertEquals(0, enemyHealth.getMaxRedHealth());
        assertEquals(100, enemyHealth.getBlueHealth());
        assertEquals(100, enemyHealth.getMaxBlueHealth());
        enemyHealth.reduceHealth(70);
        assertEquals(100, enemyHealth.getMaxHealth());
        assertEquals(0, enemyHealth.getRedHealth());
        assertEquals(0, enemyHealth.getMaxRedHealth());
        assertEquals(30, enemyHealth.getBlueHealth());
        assertEquals(100, enemyHealth.getMaxBlueHealth());
    }
}
