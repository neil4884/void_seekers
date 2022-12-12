package com.game.void_seekers.application;

import com.game.void_seekers.character.derived.PlayerIsaac;
import com.game.void_seekers.render.GameScene;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.GameLogic;

import java.io.InputStream;

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
        GameScene scene = new GameScene(root, GameLogic.WIN_WIDTH, GameLogic.WIN_HEIGHT);

//      Passing GUI to game logic
        GameLogic.getInstance().setGameScene(scene);

//      Show GUI
        primaryStage.setScene(scene);
        primaryStage.show();

//      Key Pressing and Releasing Event
        scene.setOnKeyPressed(e -> GameLogic.getInstance().keyPressedHandler(e));
        scene.setOnKeyReleased(e -> GameLogic.getInstance().keyReleasedHandler(e));

//      Animation Timer for refreshing redraw;
        GameLogic.getInstance().gameLoop.start();

//      Clear everything on close
        primaryStage.setOnCloseRequest(e -> {
            GameLogic.getInstance().gameLoop.stop();
            Platform.exit();
            System.out.println("Exited program");
            System.exit(0);
        });
    }
}
