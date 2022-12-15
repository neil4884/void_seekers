package com.game.void_seekers.character.derived;

import com.game.void_seekers.character.base.EnemyCharacter;
import com.game.void_seekers.character.base.GameCharacter;
import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import javafx.scene.canvas.GraphicsContext;

public class EnemyBobo extends EnemyCharacter {
    public EnemyBobo() {
        super("Bobo", 20, 0, 0);
        super.setAssetDefaultImage(GameAssets.fromPath("tiles/masked_orc_idle_anim_f0.png", height));
        super.setAssetImage(getAssetDefaultImage());

        super.setAssetHurtAnimation(super.getAssetDefaultImage());
        super.setAssetDeadAnimation(super.getAssetDefaultImage());
        setDamage(0);
        setSpeed(10);
    }

    @Override
    public void draw() {
        GraphicsContext gc = GameLogic.getGraphicsContext();
        gc.drawImage(super.getAssetImage(), coordinate.x, coordinate.y);
    }
}
