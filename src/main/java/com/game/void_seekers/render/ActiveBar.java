package com.game.void_seekers.render;

import com.game.void_seekers.character.base.PlayableCharacter;
import com.game.void_seekers.item.base.Active;
import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class ActiveBar extends Scene {
    private final Canvas canvas;
    private Image image;

    public ActiveBar(Pane parent, double width, double height) {
        super(parent);
        canvas = new Canvas(width, height);
        parent.getChildren().add(canvas);
    }

    public void redraw() {
        Thread thread = new Thread(() -> {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            Platform.runLater(() -> {
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                int xPos = 0;
                int yPos = 0;
                Active item = GameLogic.getInstance().getCharacter().getActive();
                int charge = item.getMaxCharge();
                int power = item.getCharge();
                setImage(item.getAssetImage());

                gc.drawImage(getImage(), xPos + 4, yPos);

                Paint p = gc.getFill();
                Font ft = gc.getFont();
                gc.setFont(GameAssets.loadGameFont(40));

                //charge
                gc.fillText(String.format("%d/%d", power, charge), xPos + 50, yPos + 30);

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
