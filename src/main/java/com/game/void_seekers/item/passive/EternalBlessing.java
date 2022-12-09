package com.game.void_seekers.item.passive;

import com.game.void_seekers.character.base.PlayableCharacter;
import com.game.void_seekers.interfaces.AffectPlayer;
import com.game.void_seekers.item.base.Passive;

public class EternalBlessing extends Passive implements AffectPlayer {
    public EternalBlessing() {
        super("Eternal Blessing", "A faithful man shall abound with blessings", "eternalBlessing.png");
    }
    @Override
    public void affectPlayer(PlayableCharacter player) {
        player.setCharacterStats(player.getDamage() * 2, player.getSpeed() * 2, player.getFireRate() * 2, 100);
    }
}
