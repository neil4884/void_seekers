package com.game.void_seekers.render;

import com.game.void_seekers.character.base.PlayableCharacter;
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

public class InventoryBar extends Scene {
    private final Canvas canvas;
    private static final Image assetBomb = GameAssets.loadImage(GameAssets.bombIconURL, 40);
    private static final Image assetCoin = GameAssets.loadImage(GameAssets.coinIconURL, 40);

    public InventoryBar(Pane parent, double width, double height) {
        super(parent);
        canvas = new Canvas(width, height);
        parent.getChildren().add(canvas);
    }

    public void redraw() {
        Thread threadRedraw = new Thread(() -> {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            Platform.runLater(() -> {
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                int xPos = 0;
                int yPos = 0;
                int coinAmount = ((PlayableCharacter) GameLogic.getInstance().getCharacter()).getCoins();
                int bombAmount = ((PlayableCharacter) GameLogic.getInstance().getCharacter()).getBombs();

                gc.drawImage(assetCoin, xPos + 4, yPos);
                gc.drawImage(assetBomb, xPos, yPos + 40);

                Paint p = gc.getFill();
                Font ft = gc.getFont();
                gc.setFill(Color.WHITE);
                gc.setFont(GameAssets.loadGameFont(40));

                gc.fillText(String.format("%02d", coinAmount), xPos + 50, yPos + 30);
                gc.fillText(String.format("%02d", bombAmount), xPos + 50, yPos + 70);

                gc.setFill(p);
                gc.setFont(ft);
            });
        });

        threadRedraw.start();
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
