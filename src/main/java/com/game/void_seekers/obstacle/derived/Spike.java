package com.game.void_seekers.obstacle.derived;

import com.game.void_seekers.character.base.PlayableCharacter;
import com.game.void_seekers.interfaces.AffectPlayer;
import com.game.void_seekers.obstacle.base.Obstacle;

public class Spike extends Obstacle implements AffectPlayer {
    private static final String spikeURL = "spike.png";
    public Spike() {
        super(0, spikeURL);
    }


    @Override
    public void affectPlayer(PlayableCharacter player) {
        //TODO: create onContactEvent
    }

    @Override
    public void draw() {

    }
}
