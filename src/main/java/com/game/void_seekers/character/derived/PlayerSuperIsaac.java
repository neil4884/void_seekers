package com.game.void_seekers.character.derived;

import com.game.void_seekers.character.base.GameCharacter;
import com.game.void_seekers.character.base.PlayableCharacter;
import com.game.void_seekers.interfaces.Attack;
import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import javafx.scene.canvas.GraphicsContext;

public class PlayerSuperIsaac extends PlayableCharacter implements Attack {
    public PlayerSuperIsaac() {
        super("SUPER ISAAC", 6, 0, 14, 1, 0, 0);
        super.setHeight(2 * GameLogic.CHARACTER_SIZE_DEFAULT);
        super.setAssetDefaultImage(GameAssets.loadImage(GameAssets.isaacURL, height));
        super.setAssetImage(super.getAssetDefaultImage());

        setDamage(10);
        setSpeed(8);
    }

    @Override
    public void draw() {
        GraphicsContext gc = GameLogic.getGraphicsContext();
        gc.drawImage(super.getAssetImage(), coordinate.x, coordinate.y);
    }

    @Override
    public void attack(GameCharacter character) {
        character.reduceHealth(getDamage());
    }
}
