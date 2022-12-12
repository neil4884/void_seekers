package com.game.void_seekers.render;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class GameScene extends Scene {
    private final Canvas canvas;
    public int x = 100;
    public int y = 100;

    public GameScene(Pane parent, int width, int height) {
        super(parent);
        canvas = new Canvas(width, height);
        parent.getChildren().add(canvas);
    }

    public void redraw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(Color.BLACK);

        String p = getClass()
                .getClassLoader()
                .getResource("com/game/void_seekers/character/isaac.png")
                .toExternalForm();
        gc.drawImage(new Image(p), 100, 100);
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
