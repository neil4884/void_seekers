package com.game.void_seekers.item.active;

import com.game.void_seekers.character.base.PlayableCharacter;
import com.game.void_seekers.interfaces.AffectPlayer;
import com.game.void_seekers.item.base.Active;
import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import javafx.scene.canvas.GraphicsContext;

public class BookOfRage extends Active implements AffectPlayer {
    public BookOfRage() {
        super("Book of Rage", "The gifted on wrath", 6);
        setAssetImage(GameAssets.loadImage(GameAssets.bookOfRageURL, size));
    }

    @Override
    public void affectPlayer(PlayableCharacter player) {
        super.use();
        player.setDamage(player.getDamage() * 2);
    }

    @Override
    public void draw() {
        GraphicsContext gc = GameLogic.getGraphicsContext();
        gc.drawImage(super.getAssetImage(), coordinate.x, coordinate.y);
    }
}
