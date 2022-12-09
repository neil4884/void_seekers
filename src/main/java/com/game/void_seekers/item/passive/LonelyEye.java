package com.game.void_seekers.item.passive;

import com.game.void_seekers.character.base.PlayableCharacter;
import com.game.void_seekers.interfaces.AffectPlayer;
import com.game.void_seekers.item.base.EffectItem;

public class LonelyEye extends EffectItem implements AffectPlayer {
    public LonelyEye() {
        super("Lonely Eye", "Become one with him", "LonelyEye.png");
    }
    @Override
    public void affectPlayer(PlayableCharacter player) {
        player.setDamage(player.getDamage() + 1);
        player.setSpeed(player.getSpeed() + 1);
    }
}
