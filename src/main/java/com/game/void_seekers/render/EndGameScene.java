package com.game.void_seekers.render;

import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

public class EndGameScene extends AbstractScene {
    public EndGameScene(Pane parent, int width, int height) {
        super(parent, width, height);
    }

    public void redraw() {

        Thread threadRedraw = new Thread(() -> {

            GraphicsContext gc = canvas.getGraphicsContext2D();

            Platform.runLater(() -> {
//              Clear frame
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gc.drawImage(GameAssets.endBackground, 0, 0);
            });
        });

        threadRedraw.start();
    }
}
