package com.game.void_seekers;

import com.game.void_seekers.character.base.PlayableCharacter;
import com.game.void_seekers.character.derived.PlayerIsaac;
import com.game.void_seekers.character.derived.PlayerJared;
import com.game.void_seekers.character.derived.PlayerSoul;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPlayer {
    PlayableCharacter player1 = new PlayerIsaac();
//    PlayableCharacter player2 = new PlayerSoul();
//    PlayableCharacter player3 = new PlayerJared();

    @BeforeEach
    void setUp() {
        player1 = new PlayerIsaac();
//        player2 = new PlayerSoul();
//        player3 = new PlayerJared();
    }

    @Test
    void testCreatePlayer() {
        assertEquals(6, player1.getRedHealth());
    }
}
