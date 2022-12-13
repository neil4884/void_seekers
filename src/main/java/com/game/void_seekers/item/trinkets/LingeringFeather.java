package com.game.void_seekers.item.trinkets;

import com.game.void_seekers.character.base.PlayableCharacter;
import com.game.void_seekers.interfaces.AffectPlayer;
import com.game.void_seekers.item.base.Trinket;

public class LingeringFeather extends Trinket implements AffectPlayer {
    public LingeringFeather() {
        super("LingeringFeather", "Lighter Weight", "lingeringFeather.png");
    }

    @Override
    public void affectPlayer(PlayableCharacter player) {
        player.setSpeed(player.getSpeed() + 1);
    }

    @Override
    public void draw() {

    }
}
