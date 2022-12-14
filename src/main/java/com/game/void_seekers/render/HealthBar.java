package com.game.void_seekers.render;

import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class HealthBar extends Scene {
    private final Canvas canvas;
    private boolean strobeHealth = false;

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
                int pos = 10;
                int fullRed = GameLogic.getInstance().getCharacter().getRedHealth() / 2;
                int halfRed = GameLogic.getInstance().getCharacter().getRedHealth() % 2;
                int emptyRed = GameLogic.getInstance().getCharacter().getMaxRedHealth() / 2 - fullRed - halfRed;
                int fullBlue = GameLogic.getInstance().getCharacter().getBlueHealth() / 2;
                int halfBlue = GameLogic.getInstance().getCharacter().getBlueHealth() % 2;

                if (strobeHealth) {
                    for (int i = 0; i < fullRed; ++i, pos += 40)
                        gc.drawImage(GameAssets.transparentImage, pos, 0);
                    for (int i = 0; i < halfRed; ++i, pos += 40)
                        gc.drawImage(GameAssets.transparentImage, pos, 0);
                    for (int i = 0; i < emptyRed; ++i, pos += 40)
                        gc.drawImage(GameAssets.transparentImage, pos, 0);
                    for (int i = 0; i < fullBlue; ++i, pos += 40)
                        gc.drawImage(GameAssets.transparentImage, pos, 0);
                    for (int i = 0; i < halfBlue; ++i, pos += 40)
                        gc.drawImage(GameAssets.transparentImage, pos, 0);
                } else {
                    for (int i = 0; i < fullRed; ++i, pos += 40)
                        gc.drawImage(assetFullRedHealth, pos, 0);
                    for (int i = 0; i < halfRed; ++i, pos += 40)
                        gc.drawImage(assetHalfRedHealth, pos, 0);
                    for (int i = 0; i < emptyRed; ++i, pos += 40)
                        gc.drawImage(assetEmptyHealth, pos, 0);
                    for (int i = 0; i < fullBlue; ++i, pos += 40)
                        gc.drawImage(assetFullBlueHealth, pos, 0);
                    for (int i = 0; i < halfBlue; ++i, pos += 40)
                        gc.drawImage(assetHalfBlueHealth, pos, 0);
                }

            });
        });

        threadRedraw.start();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public boolean isStrobeHealth() {
        return strobeHealth;
    }

    public void setStrobeHealth(boolean strobeHealth) {
        this.strobeHealth = strobeHealth;
    }
}
