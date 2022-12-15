package com.game.void_seekers.item.passive;

import com.game.void_seekers.character.base.PlayableCharacter;
import com.game.void_seekers.interfaces.AffectPlayer;
import com.game.void_seekers.item.base.EffectItem;
import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import javafx.scene.canvas.GraphicsContext;

public class LonelyEye extends EffectItem implements AffectPlayer {
    public LonelyEye() {
        super("Lonely Eye", "Become one with him");
        setAssetImage(GameAssets.loadImage(GameAssets.loneEyeURL, size));
    }

    @Override
    public void affectPlayer(PlayableCharacter player) {
        player.setDamage(player.getDamage() + 1);
        player.setSpeed(player.getSpeed() + 1);
    }

    @Override
    public void draw() {
        GraphicsContext gc = GameLogic.getGraphicsContext();
        gc.drawImage(super.getAssetImage(), coordinate.x, coordinate.y);
    }
}
