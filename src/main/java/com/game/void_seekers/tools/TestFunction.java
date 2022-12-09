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
        player.displayHealthBar();
        player.addRedHeart(2);
        player.displayHealthBar();
        gaper.attack(player);
        gaper.attack(player);
        gaper.attack(player);
        gaper.attack(player);
        player.displayHealthBar();
//        player.addBlueHeart(1);
        player.addRedHeart(1);
        player.displayHealthBar();
    }
}
