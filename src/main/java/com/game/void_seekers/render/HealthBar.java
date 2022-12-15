package com.game.void_seekers.render;

import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class HealthBar extends AbstractScene {
    private boolean strobeHealth = false;
    private Color healthColor;

    private static final Image assetEmptyHealth = GameAssets.loadImage(GameAssets.emptyRedHealthURL, 40);
    private static final Image assetFullRedHealth = GameAssets.loadImage(GameAssets.fullRedHealthURL, 40);
    private static final Image assetFullBlueHealth = GameAssets.loadImage(GameAssets.fullBlueHealthURL, 40);
    private static final Image assetHalfRedHealth = GameAssets.loadImage(GameAssets.halfRedHealthURL, 40);
    private static final Image assetHalfBlueHealth = GameAssets.loadImage(GameAssets.halfBlueHealthURL, 40);

    public HealthBar(Pane parent, double width, double height) {
        super(parent, width, height);
        setHealthColor(Color.WHITE);
    }

    public void redraw() {
        Thread threadRedraw = new Thread(() -> {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            Platform.runLater(() -> {
                int xPos = 10;
                int yPos = 60;
                int fullRed = GameLogic.getInstance().getCharacter().getRedHealth() / 2;
                int halfRed = GameLogic.getInstance().getCharacter().getRedHealth() % 2;
                int emptyRed = GameLogic.getInstance().getCharacter().getMaxRedHealth() / 2 - fullRed - halfRed;
                int fullBlue = GameLogic.getInstance().getCharacter().getBlueHealth() / 2;
                int halfBlue = GameLogic.getInstance().getCharacter().getBlueHealth() % 2;

                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

                if (strobeHealth) {
                    for (int i = 0; i < fullRed; ++i, xPos += 40)
                        gc.drawImage(GameAssets.transparentImage, xPos, yPos);
                    for (int i = 0; i < halfRed; ++i, xPos += 40)
                        gc.drawImage(GameAssets.transparentImage, xPos, yPos);
                    for (int i = 0; i < emptyRed; ++i, xPos += 40)
                        gc.drawImage(GameAssets.transparentImage, xPos, yPos);
                    for (int i = 0; i < fullBlue; ++i, xPos += 40)
                        gc.drawImage(GameAssets.transparentImage, xPos, yPos);
                    for (int i = 0; i < halfBlue; ++i, xPos += 40)
                        gc.drawImage(GameAssets.transparentImage, xPos, yPos);
                } else {
                    for (int i = 0; i < fullRed; ++i, xPos += 40)
                        gc.drawImage(assetFullRedHealth, xPos, yPos);
                    for (int i = 0; i < halfRed; ++i, xPos += 40)
                        gc.drawImage(assetHalfRedHealth, xPos, yPos);
                    for (int i = 0; i < emptyRed; ++i, xPos += 40)
                        gc.drawImage(assetEmptyHealth, xPos, yPos);
                    for (int i = 0; i < fullBlue; ++i, xPos += 40)
                        gc.drawImage(assetFullBlueHealth, xPos, yPos);
                    for (int i = 0; i < halfBlue; ++i, xPos += 40)
                        gc.drawImage(assetHalfBlueHealth, xPos, yPos);
                }

                Paint p = gc.getFill();
                Font ft = gc.getFont();
                gc.setFill(getHealthColor());
                gc.setFont(GameAssets.loadGameFont(36));

                gc.fillText(String.format("Health  %03d", playerHealth()) + "/100", 16, 40);

                gc.setFill(p);
                gc.setFont(ft);
            });
        });

        threadRedraw.start();
    }

    private int playerHealth() {
        int maxRed = GameLogic.getInstance().getCharacter().getMaxRedHealth();
        int currRed = GameLogic.getInstance().getCharacter().getRedHealth();
        int currBlue = GameLogic.getInstance().getCharacter().getBlueHealth();

        if (maxRed + currBlue == 0)
            return 0;

        return 100 * (currRed + currBlue) / (maxRed + currBlue);
    }

    public boolean isStrobeHealth() {
        return strobeHealth;
    }

    public void setStrobeHealth(boolean strobeHealth) {
        this.strobeHealth = strobeHealth;
    }

    public Color getHealthColor() {
        return healthColor;
    }

    public void setHealthColor(Color healthColor) {
        this.healthColor = healthColor;
    }
}
