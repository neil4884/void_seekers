package com.game.void_seekers.character.derived;

import com.game.void_seekers.character.base.EnemyCharacter;
import com.game.void_seekers.character.base.GameCharacter;
import com.game.void_seekers.interfaces.Attack;
import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import com.game.void_seekers.projectile.derived.NormalProjectile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class EnemyNecromancer extends EnemyCharacter implements Attack {
    public EnemyNecromancer() {
        super("Necromancer", 4, 0, 0);
        super.setAssetDefaultImage(GameAssets.fromPath("tiles/necromancer_idle_anim_f1.png", height));
        super.setAssetImage(getAssetDefaultImage());

        super.setAssetHurtAnimation(super.getAssetDefaultImage());
        super.setAssetDeadAnimation(super.getAssetDefaultImage());
        setDamage(2);
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
