package com.game.void_seekers.character.derived;

import com.game.void_seekers.character.base.GameCharacter;
import com.game.void_seekers.character.base.PlayableCharacter;
import com.game.void_seekers.interfaces.Attack;
import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import javafx.scene.canvas.GraphicsContext;

public class PlayerJared extends PlayableCharacter implements Attack {
    public PlayerJared() {
        
        super("Jared", 2, 0, 4, 1, 0, 0);
        super.setAssetImage(GameAssets.loadImage(GameAssets.soulURL, height));
        setDamage(3);
        setSpeed(5);
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
