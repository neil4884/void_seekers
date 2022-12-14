package com.game.void_seekers.render;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

public abstract class AbstractScene extends Scene {
    protected final Canvas canvas;

    public AbstractScene(Pane parent, int width, int height) {
        super(parent);
        canvas = new Canvas(width, height);
        parent.getChildren().add(canvas);
    }

    public abstract void redraw();

    public Canvas getCanvas() {
        return canvas;
    }
}
