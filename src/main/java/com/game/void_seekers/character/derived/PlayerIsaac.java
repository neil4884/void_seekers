package com.game.void_seekers.character.derived;

import com.game.void_seekers.character.base.GameCharacter;
import com.game.void_seekers.character.base.PlayableCharacter;
import com.game.void_seekers.interfaces.Attack;
import com.game.void_seekers.logic.GameAssets;
import javafx.scene.canvas.GraphicsContext;
import com.game.void_seekers.logic.GameLogic;

public class PlayerIsaac extends PlayableCharacter implements Attack {
    public PlayerIsaac() {
        super("Isaac", 6, 0, 0, 0);
        super.setAssetDefaultImage(GameAssets.loadImage(GameAssets.isaacURL, height));
        super.setAssetImage(super.getAssetDefaultImage());

        setDamage(2);
        setSpeed(5);
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
