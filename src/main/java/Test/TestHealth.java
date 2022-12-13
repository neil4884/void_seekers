package Test;


import com.game.void_seekers.character.derived.CharacterHealth;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHealth {

    CharacterHealth health;
    CharacterHealth health2;

    @BeforeEach
    void setUp() {
        health = new CharacterHealth(6, 0);
        health2 = new CharacterHealth(6, 0);
        health2.addRedHealth(3);
    }

    @Test
    void testConstructor() {
        assertEquals(6, health.getRedHealth());
        assertEquals(6, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(14, health.getMaxBlueHealth());
    }
    @Test
    void testAddRedHealth() {
        assertEquals(6, health.getRedHealth());
        assertEquals(6, health.getMaxRedHealth());
        assertEquals(0, health.getBlueHealth());
        assertEquals(14, health.getMaxBlueHealth());
    }



}
