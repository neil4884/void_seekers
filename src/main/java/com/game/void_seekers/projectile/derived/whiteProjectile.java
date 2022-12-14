package com.game.void_seekers.projectile.derived;

import com.game.void_seekers.character.base.PlayableCharacter;
import com.game.void_seekers.interfaces.AffectPlayer;
import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import com.game.void_seekers.projectile.base.Projectile;
import com.game.void_seekers.tools.Coordinates;
import javafx.scene.canvas.GraphicsContext;

public class whiteProjectile extends Projectile implements AffectPlayer {
    public whiteProjectile(Coordinates coordinate) {
        super("white", 1, 1, coordinate);
        setImage(GameAssets.loadImage(GameAssets.whiteProjectileURL, size));
    }

    @Override
    public void draw() {
        GraphicsContext gc = GameLogic.getGraphicsContext();
        gc.drawImage(super.getImage(), coordinate.x, coordinate.y);
    }

    @Override
    public void affectPlayer(PlayableCharacter player) {
        //TODO: add slow effect to player after contact for amnt duration
    }
}
