package com.game.void_seekers.item.trinkets;

import com.game.void_seekers.character.base.PlayableCharacter;
import com.game.void_seekers.interfaces.AffectPlayer;
import com.game.void_seekers.item.base.Trinket;
import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import javafx.scene.canvas.GraphicsContext;

public class LingeringFeather extends Trinket implements AffectPlayer {
    public LingeringFeather() {
        super("LingeringFeather", "Lighter Weight");
        setAssetImage(GameAssets.loadImage(GameAssets.lingeringFeatherURL, size));
    }

    @Override
    public void affectPlayer(PlayableCharacter player) {
        player.setSpeed(player.getSpeed() + 10);
    }

    @Override
    public void draw() {
        GraphicsContext gc = GameLogic.getGraphicsContext();
        gc.drawImage(super.getAssetImage(), coordinate.x, coordinate.y);
    }
}
