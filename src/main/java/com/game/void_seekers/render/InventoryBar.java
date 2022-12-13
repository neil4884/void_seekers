package com.game.void_seekers.render;

import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

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
        Thread thread = new Thread(() -> {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            Platform.runLater(() -> {
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                int pos = 0;
                int coinAmount = GameLogic.getInstance().getCharacter().getCoins();
                int bombAmount = GameLogic.getInstance().getCharacter().getBombs();
                gc.drawImage(assetCoin, pos, 0);
                Text textCoin = new Text(pos + 20, 0, Integer.toString(coinAmount));
                gc.drawImage(assetBomb, pos, 40);
                Text textBomb = new Text(pos + 20, 0, Integer.toString(bombAmount));
                //FIXME: Add text to pane
            });
        });
        thread.start();
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
