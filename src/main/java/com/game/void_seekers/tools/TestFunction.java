package com.game.void_seekers.tools;

import com.game.void_seekers.character.base.Health;
import com.game.void_seekers.character.base.HealthStatus;
import com.game.void_seekers.character.base.PlayableCharacter;
import com.game.void_seekers.character.derived.EnemyGaper;

public class TestFunction {
    public static void main(String[] args) {
        PlayableCharacter player = new PlayableCharacter("Play Test", 10, 0, 0);
        EnemyGaper gaper = new EnemyGaper();
        player.displayHealthBar();
        gaper.attack(player);
        player.displayHealthBar();
        gaper.attack(player);
        player.displayHealthBar();
        gaper.attack(player);
        player.displayHealthBar();
        player.setStatus(HealthStatus.BLUE);
        player.displayHealthBar();
    }
}
