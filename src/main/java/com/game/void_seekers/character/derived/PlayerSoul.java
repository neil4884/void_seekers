package com.game.void_seekers.character.derived;

import com.game.void_seekers.character.base.GameCharacter;
import com.game.void_seekers.character.base.PlayableCharacter;
import com.game.void_seekers.interfaces.Attack;
import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import javafx.scene.canvas.GraphicsContext;

public class PlayerSoul extends PlayableCharacter implements Attack {
    public PlayerSoul() {
        super("Soul", 2, 1, 0, 0);
        super.setAssetDefaultImage(GameAssets.loadImage(GameAssets.soulURL, height));
        super.setAssetImage(super.getAssetDefaultImage());

        setDamage(3);
        setSpeed(7);
    }

    @Override
    public void draw() {
        GraphicsContext gc = GameLogic.getGraphicsContext();
        drawShadow(gc);
        gc.drawImage(super.getAssetImage(), coordinate.x, coordinate.y);
    }

    @Override
    public void attack(GameCharacter character) {
        character.reduceHealth(getDamage());
    }
}
