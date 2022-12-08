package com.game.void_seekers.tools;

import com.game.void_seekers.character.base.Health;
import com.game.void_seekers.character.base.HealthStatus;
import com.game.void_seekers.character.base.PlayableCharacter;
import com.game.void_seekers.character.derived.EnemyGaper;
import com.game.void_seekers.character.derived.PlayerIsaac;

public class TestFunction {
    public static void main(String[] args) {
        PlayableCharacter player = new PlayerIsaac();
        EnemyGaper gaper = new EnemyGaper();
        player.increaseBlueMaxHealth(10);
        player.increaseBlueHealth(10);
        player.displayHealthBar();
        gaper.displayHealthBar();
        player.attack(gaper);
        gaper.displayHealthBar();
        gaper.attack(player);
        player.displayHealthBar();
    }
}
