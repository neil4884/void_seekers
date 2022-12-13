package com.game.void_seekers.character.derived;

import com.game.void_seekers.character.base.EnemyCharacter;
import com.game.void_seekers.character.base.GameCharacter;
import com.game.void_seekers.interfaces.Attack;
import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import javafx.scene.canvas.GraphicsContext;

public class EnemyGaper extends EnemyCharacter implements Attack {
    public EnemyGaper() {
        super("Gaper", 10, 0, 0);
        setDamage(1);
    }

    @Override
    public void draw() {
        GraphicsContext gc = GameLogic.getGraphicsContext();
        gc.drawImage(GameAssets.loadImage(GameAssets.gaperURL, height), coordinate.x, coordinate.y);
    }

    @Override
    public void attack(GameCharacter character) {
        character.reduceHealth(getDamage());
    }
}
