package com.game.void_seekers.character.derived;

import com.game.void_seekers.character.base.GameCharacter;
import com.game.void_seekers.character.base.PlayableCharacter;
import com.game.void_seekers.interfaces.Attack;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.GameLogic;

import java.net.URISyntaxException;
import java.util.Objects;

public class PlayerIsaac extends PlayableCharacter implements Attack {
    private static final String isaacURL = "isaac.png";

    public PlayerIsaac() {
        super("Isaac", 6, 0, 0);
        setDamage(2);
    }

    @Override
    public void draw() {
        GraphicsContext gc = GameLogic.getInstance().getCanvas().getGraphicsContext2D();
//        String p = Objects.requireNonNull(getClass().getResource("/dime.png")).toExternalForm();
//        gc.drawImage(new Image(p), 100, 100);
    }

    @Override
    public void attack(GameCharacter character) {
        character.reduceHealth(getDamage());
    }
}
