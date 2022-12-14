package com.game.void_seekers.application;

import com.game.void_seekers.character.base.PlayableCharacter;
import com.game.void_seekers.character.derived.PlayerSuperIsaac;
import com.game.void_seekers.render.GameScene;
import com.game.void_seekers.render.HealthBar;
import com.game.void_seekers.render.InventoryBar;
import com.game.void_seekers.render.MenuScene;
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
//      Primary Window
        primaryStage.setTitle(GAME_TITLE);
        AnchorPane root = new AnchorPane();
        AnchorPane subPane = new AnchorPane();
        GameScene gameScene = new GameScene(root, GameLogic.WIN_WIDTH, GameLogic.WIN_HEIGHT);
        MenuScene menuScene = new MenuScene(subPane, GameLogic.WIN_WIDTH, GameLogic.WIN_HEIGHT);

//      Health Bar
        StackPane healthBarPane = new StackPane();
        HealthBar healthBar = new HealthBar(healthBarPane, (double) GameLogic.WIN_WIDTH * 0.5, 120);
        root.getChildren().add(healthBarPane);
        AnchorPane.setBottomAnchor(healthBarPane, 0d);
        AnchorPane.setLeftAnchor(healthBarPane, 10d);

//      Inventory Bar
        StackPane invBarPane = new StackPane();
        InventoryBar invBar = new InventoryBar(invBarPane, GameLogic.WALL_SIZE + 100, GameLogic.WIN_HEIGHT * 0.2);
        root.getChildren().add(invBarPane);
        AnchorPane.setTopAnchor(invBarPane, GameLogic.WIN_HEIGHT * 0.5 + GameLogic.DOOR_LENGTH * 4);
        AnchorPane.setLeftAnchor(invBarPane, 20d);

//      Passing GUI to game com.game.void_seekers.logic
        GameLogic.getInstance().setStage(primaryStage);
        GameLogic.getInstance().setGameScene(gameScene);
        GameLogic.getInstance().setMenuScene(menuScene);

        GameLogic.getInstance().setCurrentScene(gameScene);

        GameLogic.getInstance().setHealthBar(healthBar);
        GameLogic.getInstance().setInventoryBar(invBar);
        GameLogic.getInstance().setRootPane(root);

//      Initialize initial game character and room.
//      FIXME: MUST INIT AFTER MENU: START GAME
//        PlayableCharacter p = new PlayerIsaac();
//        PlayableCharacter p = new PlayerJared();
//        PlayableCharacter p = new PlayerSoul();
        PlayableCharacter p = new PlayerSuperIsaac();
        GameLogic.getInstance().init(p);

//      Show GUI
        primaryStage.setScene(GameLogic.getInstance().getCurrentScene());
        primaryStage.setResizable(false);
        primaryStage.show();

//      Key Pressing and Releasing Event
        gameScene.setOnKeyPressed(e -> GameLogic.getInstance().keyPressedHandler(e));
        gameScene.setOnKeyReleased(e -> GameLogic.getInstance().keyReleasedHandler(e));

//      Start Polling & Rendering thread;
        GameLogic.getInstance().pollingLoop.start();

//      Clear everything on close
        primaryStage.setOnCloseRequest(e -> GameLogic.getInstance().exit());

    }
}
