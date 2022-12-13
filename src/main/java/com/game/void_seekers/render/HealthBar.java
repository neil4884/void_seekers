package com.game.void_seekers.render;

import com.game.void_seekers.logic.GameAssets;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class HealthBar extends Scene {
    private final Canvas canvas;
    private static final Image assetEmptyHealth = GameAssets.loadImage(GameAssets.emptyRedHealthURL, 40);
    private static final Image assetFullRedHealth = GameAssets.loadImage(GameAssets.fullRedHealthURL, 40);
    private static final Image assetFullBlueHealth = GameAssets.loadImage(GameAssets.fullBlueHealthURL, 40);
    private static final Image assetHalfRedHealth = GameAssets.loadImage(GameAssets.halfRedHealthURL, 40);
    private static final Image assetHalfBlueHealth = GameAssets.loadImage(GameAssets.halfBlueHealthURL, 40);

    public HealthBar(Pane parent, double width, double height) {
        super(parent);
        canvas = new Canvas(width, height);
        parent.getChildren().add(canvas);
    }

    public void redraw() {
        Thread threadRedraw = new Thread(() -> {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            Platform.runLater(() -> {
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

                gc.drawImage(assetEmptyHealth, 10, 0);
                gc.drawImage(assetFullBlueHealth, 50, 0);
                gc.drawImage(assetFullRedHealth, 90, 0);
                gc.drawImage(assetHalfBlueHealth, 130, 0);
                gc.drawImage(assetHalfRedHealth, 170, 0);
            });
        });

        threadRedraw.start();
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
