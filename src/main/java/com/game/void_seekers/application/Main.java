package com.game.void_seekers.application;

import com.game.void_seekers.logic.GameLogic;
import com.game.void_seekers.logic.GameState;
import com.game.void_seekers.render.*;
import javafx.application.Application;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

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
        AnchorPane startPane = new AnchorPane();
        AnchorPane endPane = new AnchorPane();

        GameScene gameScene = new GameScene(root, GameLogic.WIN_WIDTH, GameLogic.WIN_HEIGHT);
        MenuScene menuScene = new MenuScene(startPane, GameLogic.WIN_WIDTH, GameLogic.WIN_HEIGHT);
        EndGameScene endGameScene = new EndGameScene(endPane, GameLogic.WIN_WIDTH, GameLogic.WIN_HEIGHT);

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

//      Active Item Bar
        StackPane activeBarPane = new StackPane();
        ActiveBar activeBar = new ActiveBar(activeBarPane, 1.5 * GameLogic.WALL_SIZE, 4 * GameLogic.WALL_SIZE);
        root.getChildren().add(activeBarPane);
        AnchorPane.setTopAnchor(activeBarPane, 40d);
        AnchorPane.setRightAnchor(activeBarPane, 20d);

//      Trinket Item Bar
        StackPane trinkedBarPane = new StackPane();
        TrinketBar trinketBar = new TrinketBar(trinkedBarPane, 1.5 * GameLogic.WALL_SIZE, 1.5 * GameLogic.WALL_SIZE);
        root.getChildren().add(trinkedBarPane);
        AnchorPane.setBottomAnchor(trinkedBarPane, 10d);
        AnchorPane.setRightAnchor(trinkedBarPane, 20d);

//      Passing GUI to game com.game.void_seekers.logic
        GameLogic.getInstance().setStage(primaryStage);
        GameLogic.getInstance().setGameScene(gameScene);
        GameLogic.getInstance().setMenuScene(menuScene);
        GameLogic.getInstance().setEndGameScene(endGameScene);

        GameLogic.getInstance().switchScene(menuScene);
        GameLogic.setState(GameState.MENU);

        GameLogic.getInstance().setHealthBar(healthBar);
        GameLogic.getInstance().setInventoryBar(invBar);
        GameLogic.getInstance().setActiveBar(activeBar);
        GameLogic.getInstance().setTrinketBar(trinketBar);

//      Show GUI
        primaryStage.setScene(GameLogic.getInstance().getCurrentScene());
        primaryStage.setResizable(false);
        primaryStage.show();

//      Key Pressing and Releasing Event
        gameScene.setOnKeyPressed(e -> GameLogic.getInstance().keyPressedHandler(e));
        gameScene.setOnKeyReleased(e -> GameLogic.getInstance().keyReleasedHandler(e));
        menuScene.setOnKeyPressed(e -> GameLogic.getInstance().keyPressedHandler(e));
        menuScene.setOnKeyReleased(e -> GameLogic.getInstance().keyReleasedHandler(e));
        endGameScene.setOnKeyPressed(e -> GameLogic.getInstance().keyPressedHandler(e));
        endGameScene.setOnKeyReleased(e -> GameLogic.getInstance().keyReleasedHandler(e));

//      Start Polling & Rendering thread;
        GameLogic.getInstance().pollingLoop.start();

//      Clear everything on close
        primaryStage.setOnCloseRequest(e -> GameLogic.getInstance().exit());

    }
}
