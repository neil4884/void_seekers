package com.game.void_seekers.character.derived;

import com.game.void_seekers.character.base.GameCharacter;
import com.game.void_seekers.character.base.PlayableCharacter;
import com.game.void_seekers.interfaces.Attack;

public class PlayerTest extends PlayableCharacter implements Attack {
    public PlayerTest() {
        super("Test", 6, 0, 0, 0);
        setDamage(0);
        setSpeed(1);
    }

    @Override
    public void draw() {
    }

    @Override
    public void attack(GameCharacter character) {
        character.reduceHealth(getDamage());
    }
}
