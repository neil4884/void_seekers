package com.game.void_seekers.projectile.derived;

import com.game.void_seekers.character.base.PlayableCharacter;
import com.game.void_seekers.interfaces.AffectPlayer;
import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import com.game.void_seekers.projectile.base.Projectile;
import com.game.void_seekers.tools.Coordinates;
import javafx.scene.canvas.GraphicsContext;

public class NormalProjectile extends Projectile implements AffectPlayer {

    public NormalProjectile(Coordinates coordinate) {
        super("Normal", coordinate);
        setSize((int) (GameLogic.CHARACTER_SIZE_DEFAULT * 0.75));
        setImage(GameAssets.loadImage(GameAssets.normalProjectileURL, size));
        setSpeed(5);
    }

    @Override
    public void draw() {
        GraphicsContext gc = GameLogic.getGraphicsContext();
        gc.drawImage(super.getImage(), coordinate.x, coordinate.y);
    }

    @Override
    public void affectPlayer(PlayableCharacter player) {
        //FIXME: on contact
        player.reduceHealth(getDamage());
    }
}
