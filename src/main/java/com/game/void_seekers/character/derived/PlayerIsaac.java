package com.game.void_seekers.character.derived;

import com.game.void_seekers.character.base.GameCharacter;
import com.game.void_seekers.character.base.PlayableCharacter;
import com.game.void_seekers.interfaces.Attack;

public class PlayerIsaac extends PlayableCharacter implements Attack {
    private static final String isaacURL = "isaac.png";
    public PlayerIsaac() {
        super("Isaac", 6, 0, 0);
        setDamage(2);
    }

    @Override
    public void attack(GameCharacter character) {
        character.reduceHealth(getDamage());
    }
}
