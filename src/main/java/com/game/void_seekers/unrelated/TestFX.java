package com.game.void_seekers.unrelated;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TestFX extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Canvas mainCanvas = new Canvas(800, 600);
        GraphicsContext context = mainCanvas.getGraphicsContext2D();
        context.setFill(Color.RED);
        context.fillRect(50, 50, 20, 30);
        context.fillRect(100, 100, 20, 30);

        primaryStage.setScene(new Scene(new Pane(mainCanvas)));
        primaryStage.show();
    }
}
