package com.game.void_seekers.render;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

public class GameScene extends Scene {
    public GameScene(Pane parent, int width, int height) {
        super(parent);
        Canvas canvas = new Canvas(width, height);
        parent.getChildren().add(canvas);
    }

    public void redraw() {

    }
}
