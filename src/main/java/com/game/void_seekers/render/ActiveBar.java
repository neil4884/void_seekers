package com.game.void_seekers.render;

import com.game.void_seekers.item.base.Active;
import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class ActiveBar extends AbstractScene {
    private final Image defaultImage = GameAssets.transparentImage;
    private Image image;

    public ActiveBar(Pane parent, double width, double height) {
        super(parent, width, height);
    }

    public void redraw() {
        Thread thread = new Thread(() -> {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            Platform.runLater(() -> {
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

                int xPos = 0;
                int yPos = 0;
                int charge;
                int power;

                Active item = GameLogic.getInstance().getCharacter().getActive();
                if (item == null) {
                    setImage(defaultImage);
                    charge = 0;
                    power = 0;
                } else {
                    setImage(item.getAssetImage());
                    charge = item.getMaxCharge();
                    power = item.getCharge();
                }

                Paint p = gc.getFill();
                Font ft = gc.getFont();

                double a = gc.getGlobalAlpha();
                gc.setGlobalAlpha(0.6);
                gc.fillRect(0, 0, GameLogic.CHARACTER_SIZE_DEFAULT, GameLogic.CHARACTER_SIZE_DEFAULT);
                gc.setGlobalAlpha(a);

                gc.drawImage(getImage(), xPos, yPos);

                gc.setFill(Color.WHITE);
                gc.setFont(GameAssets.loadGameFont(32));
                gc.fillText(String.format("%02d/%02d", power, charge), xPos, yPos + 120);
                gc.setFont(GameAssets.loadGameFont(20));
                if (item == null)
                    gc.fillText("No active\nitem", xPos, yPos + 140);
                else
                    gc.fillText(item.getName(), xPos, yPos + 140);

                gc.setFill(p);
                gc.setFont(ft);
            });
        });

        thread.start();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
