package com.game.void_seekers.application;

import com.game.void_seekers.character.base.PlayableCharacter;
import com.game.void_seekers.character.derived.PlayerIsaac;
import com.game.void_seekers.character.derived.PlayerJared;
import com.game.void_seekers.character.derived.PlayerSoul;
import com.game.void_seekers.character.derived.PlayerSuperIsaac;
import com.game.void_seekers.render.GameScene;
import com.game.void_seekers.render.HealthBar;
import javafx.application.Application;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import com.game.void_seekers.logic.GameLogic;

public class Main extends Application {
    private static final String GAME_TITLE = "Void Seekers";

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
//      Primary WindowR
        primaryStage.setTitle(GAME_TITLE);
        AnchorPane root = new AnchorPane();
        GameScene scene = new GameScene(root, GameLogic.WIN_WIDTH, GameLogic.WIN_HEIGHT);

        StackPane barPane = new StackPane();
        HealthBar bar = new HealthBar(barPane, GameLogic.WIN_WIDTH - GameLogic.WALL_SIZE, 60);
        root.getChildren().add(barPane);
        AnchorPane.setBottomAnchor(barPane, 0.0);
        AnchorPane.setLeftAnchor(barPane, 10.0);

//      Passing GUI to game com.game.void_seekers.logic
        GameLogic.getInstance().setGameScene(scene);
        GameLogic.getInstance().setHealthBar(bar);
        GameLogic.getInstance().setRootPane(root);

//      Initialize initial game character and room.
        PlayableCharacter p = new PlayerIsaac();
//        PlayableCharacter p = new PlayerJared();
//        PlayableCharacter p = new PlayerSoul();
//        PlayableCharacter p = new PlayerSuperIsaac();
        GameLogic.getInstance().init(p);

//      Show GUI
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

//      Key Pressing and Releasing Event
        scene.setOnKeyPressed(e -> GameLogic.getInstance().keyPressedHandler(e));
        scene.setOnKeyReleased(e -> GameLogic.getInstance().keyReleasedHandler(e));

//      Game thread and input thread;
        GameLogic.getInstance().inputLoop.start();
        GameLogic.getInstance().gameLoop.start();
        GameLogic.getInstance().enemyLoop.start();

//      Clear everything on close
        primaryStage.setOnCloseRequest(e -> GameLogic.getInstance().exit());

    }
}
