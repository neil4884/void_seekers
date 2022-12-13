package com.game.void_seekers.item.passive;

import com.game.void_seekers.character.base.PlayableCharacter;
import com.game.void_seekers.interfaces.AffectPlayer;
import com.game.void_seekers.item.base.Passive;
import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import javafx.scene.canvas.GraphicsContext;

public class EternalBlessing extends Passive implements AffectPlayer {

    public EternalBlessing() {
        super("Eternal Blessing", "A faithful man shall abound with blessings");
        setAssetImage(GameAssets.loadImage(GameAssets.eternalBlessingURL, size));
    }

    @Override
    public void affectPlayer(PlayableCharacter player) {
        player.setCharacterStats(player.getDamage() * 2, player.getSpeed() * 2, player.getFireRate() * 2, 100);
    }

    @Override
    public void draw() {
        GraphicsContext gc = GameLogic.getGraphicsContext();
        gc.drawImage(super.getAssetImage(), coordinate.x, coordinate.y);
    }
}
