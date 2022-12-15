package com.game.void_seekers.character.derived;

import com.game.void_seekers.character.base.EnemyCharacter;
import com.game.void_seekers.character.base.GameCharacter;
import com.game.void_seekers.interfaces.Attack;
import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import javafx.scene.canvas.GraphicsContext;

public class EnemySwamper extends EnemyCharacter implements Attack {
    public EnemySwamper() {
        super("Swamper", 1, 0, 0);
        super.setAssetDefaultImage(GameAssets.fromPath("tiles/swampy_run_anim_f2.png", height));
        super.setAssetImage(getAssetDefaultImage());

        super.setAssetHurtAnimation(GameAssets.fromPath("tiles/swampy_run_anim_f3.png", height));
        super.setAssetDeadAnimation(GameAssets.fromPath("tiles/swampy_idle_anim_f0.png", height));
        setDamage(10);
        setSpeed(1);
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
