package com.game.void_seekers.application;

import com.game.void_seekers.render.GameScene;
import javafx.application.Application;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.GameLogic;

public class Main extends Application {
    private static final String GAME_TITLE = "Void Seekers";
    private static final int WIN_WIDTH = 1280;
    private static final int WIN_HEIGHT = 720;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(GAME_TITLE);
        AnchorPane root = new AnchorPane();
        GameScene scene = new GameScene(root, WIN_WIDTH, WIN_HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
