package com.game.void_seekers.render;

import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class MenuScene extends AbstractScene {
    private final String[] selections = {"JARED", "ISAAC", "SOUL"};
    private int currentSelection;


    public MenuScene(Pane parent, int width, int height) {
        super(parent, width, height);
        currentSelection = 1;
    }

    public void redraw() {
        Thread threadRedraw = new Thread(() -> {
            GraphicsContext gc = canvas.getGraphicsContext2D();

            Platform.runLater(() -> {
//              Clear frame
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gc.drawImage(GameAssets.menuBackground, 0, 0);

//              Arrow selection
                int yLevel = GameLogic.MIDDLE_CENTER.y + 140;
                switch (currentSelection) {
                    case 0: {
                        gc.drawImage(
                                GameAssets.arrowTopImage,
                                GameLogic.MIDDLE_CENTER.x - (double) GameLogic.WIN_WIDTH / 3 + 20,
                                yLevel
                        );
                        break;
                    }
                    case 1: {
                        gc.drawImage(
                                GameAssets.arrowTopImage,
                                GameLogic.MIDDLE_CENTER.x - 30,
                                yLevel
                        );
                        break;
                    }
                    case 2: {
                        gc.drawImage(
                                GameAssets.arrowTopImage,
                                GameLogic.MIDDLE_CENTER.x + (double) GameLogic.WIN_WIDTH / 3 - 80,
                                yLevel
                        );
                        break;
                    }
                }

//              Text overlay and selection
                Paint p = gc.getFill();
                Font ft = gc.getFont();
                TextAlignment tx = gc.getTextAlign();
                gc.setFill(Color.WHITE);

                gc.setTextAlign(TextAlignment.CENTER);
                gc.setFont(GameAssets.loadGameFont(56));
                gc.fillText(
                        "PLAY AS " + getSelection(),
                        GameLogic.MIDDLE_CENTER.x,
                        GameLogic.MIDDLE_CENTER.y + 260
                );
                gc.setFill(Color.BEIGE);
                gc.setFont(GameAssets.loadGameFont(32));
                gc.fillText(
                        "PRESS SPACE TO PLAY",
                        GameLogic.MIDDLE_CENTER.x,
                        GameLogic.MIDDLE_CENTER.y + 320
                );

                gc.setFill(p);
                gc.setFont(ft);
                gc.setTextAlign(tx);

            });
        });

        threadRedraw.start();
    }

    public void moveSelection(int direction) {
        int newSelection = currentSelection + direction;
        currentSelection = Math.min(Math.max(newSelection, 0), selections.length - 1);
    }

    public String getSelection() {
        return selections[currentSelection];
    }
}
