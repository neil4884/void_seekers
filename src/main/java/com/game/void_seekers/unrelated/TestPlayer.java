package com.game.void_seekers.unrelated;

import com.game.void_seekers.character.base.PlayableCharacter;
import com.game.void_seekers.character.derived.PlayerSoul;
import com.game.void_seekers.item.active.bookOfRage;
import com.game.void_seekers.item.base.Item;
import javafx.application.Application;
import javafx.stage.Stage;

public class TestPlayer extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        PlayableCharacter soul = new PlayerSoul();
        soul.displayHealthBar();
        Item active1 = new bookOfRage();
//        soul.addEffectItem();

    }
}
