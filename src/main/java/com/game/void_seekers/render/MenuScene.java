package com.game.void_seekers.render;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

public class MenuScene extends AbstractScene {
    public MenuScene(Pane parent, int width, int height) {
        super(parent, width, height);
    }

    public void redraw() {
        Thread threadRedraw = new Thread(() -> {
            GraphicsContext gc = canvas.getGraphicsContext2D();

            Platform.runLater(() -> {
//              Clear frame
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gc.fillRect(50, 50, 300, 300);

            });
        });

        threadRedraw.start();
    }
}
