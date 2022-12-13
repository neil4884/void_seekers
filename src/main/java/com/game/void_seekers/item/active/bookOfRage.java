package com.game.void_seekers.item.active;

import com.game.void_seekers.character.base.PlayableCharacter;
import com.game.void_seekers.interfaces.AffectPlayer;
import com.game.void_seekers.item.base.Active;

public class bookOfRage extends Active implements AffectPlayer {
    public bookOfRage() {
        super("Book of Rage", "The gifted on wrath", "BookOfRage.png", 6);
    }

    @Override
    public void affectPlayer(PlayableCharacter player) {
        super.use();
        player.setDamage(player.getDamage() * 2);
    }

    @Override
    public void draw() {

    }
}
