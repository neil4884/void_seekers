package com.game.void_seekers.character.derived;

import com.game.void_seekers.character.base.GameCharacter;
import com.game.void_seekers.character.base.PlayableCharacter;
import com.game.void_seekers.interfaces.Attack;
import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import com.game.void_seekers.tools.RandomIntRange;
import javafx.scene.canvas.GraphicsContext;

public class PlayerJared extends PlayableCharacter implements Attack {
    private static final int beginHealth = 2 * new RandomIntRange(1, 3).getResult();
    private static final int hp = new RandomIntRange(0, beginHealth).getResult();
    public PlayerJared() {
        super("Jared", hp, 0, beginHealth - hp, 1, 0, 0);
        super.setAssetDefaultImage(GameAssets.loadImage(GameAssets.jaredURL, height));
        super.setAssetImage(super.getAssetDefaultImage());

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
