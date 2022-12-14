package com.game.void_seekers.render;

import com.game.void_seekers.character.base.EnemyCharacter;
import com.game.void_seekers.item.base.Item;
import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.obstacle.base.Obstacle;
import com.game.void_seekers.room.base.Room;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import com.game.void_seekers.logic.GameLogic;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;


public class GameScene extends AbstractScene {
    public GameScene(Pane parent, int width, int height) {
        super(parent, width, height);
    }

    public void redraw() {
        Thread threadRedraw = new Thread(() -> {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            Room currentRoom = GameLogic.getInstance().getCurrentRoom();

            Platform.runLater(() -> {
//              Clear frame
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

//              Draw room background
                currentRoom.draw();

//              Draw player's and enemies' shadows
                for (EnemyCharacter e : currentRoom.getEnemyCharacters())
                    e.drawShadow(gc);
                GameLogic.getInstance().getCharacter().drawShadow(gc);

//              Draw enemies and obstacles
                for (Obstacle e : currentRoom.getObstacles())
                    e.draw();
                for (EnemyCharacter e : currentRoom.getEnemyCharacters())
                    e.draw();

//              Draw player's character
                GameLogic.getInstance().getCharacter().draw();

//              Draw item
                for (Item e : currentRoom.getItems())
                    e.draw();

//              Draw texts
                drawText(gc);
            });
        });

        threadRedraw.start();

    }

    private void drawText(GraphicsContext gc) {
//      Character Name
        Paint p = gc.getFill();
        Font ft = gc.getFont();
        TextAlignment tx = gc.getTextAlign();
        gc.setFill(Color.WHITE);

//      "Now playing as" above character name
        gc.setFont(GameAssets.loadGameFont(28));
        gc.setTextAlign(TextAlignment.RIGHT);
        gc.fillText(
                "NOW PLAYING AS",
                GameLogic.FLOOR_BOTTOM_RIGHT.x - 80,
                GameLogic.FLOOR_BOTTOM_RIGHT.y + 20
        );

//      Current character name
        gc.setFont(GameAssets.loadGameFont(44));
        gc.setTextAlign(TextAlignment.RIGHT);
        gc.fillText(
                GameLogic.getInstance().getCharacter().getName(),
                GameLogic.FLOOR_BOTTOM_RIGHT.x - 80,
                GameLogic.FLOOR_BOTTOM_RIGHT.y + 50
        );

//

        gc.setFill(p);
        gc.setFont(ft);
        gc.setTextAlign(tx);
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
