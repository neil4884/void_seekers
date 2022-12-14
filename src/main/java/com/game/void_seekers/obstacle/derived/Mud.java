package com.game.void_seekers.obstacle.derived;

import com.game.void_seekers.character.base.PlayableCharacter;
import com.game.void_seekers.interfaces.AffectPlayer;
import com.game.void_seekers.obstacle.base.Obstacle;

public class Mud extends Obstacle implements AffectPlayer {
    public static final String mudURL = "mud.png";
    public Mud() {
        super(0);
    }

    @Override
    public void affectPlayer(PlayableCharacter player) {
        //TODO:create onContactEvent
    }

    @Override
    public void draw() {

    }
}
