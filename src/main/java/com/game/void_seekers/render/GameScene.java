package com.game.void_seekers.render;

import com.game.void_seekers.character.base.EnemyCharacter;
import com.game.void_seekers.obstacle.base.Obstacle;
import com.game.void_seekers.room.base.Room;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import com.game.void_seekers.logic.GameLogic;


public class GameScene extends Scene {
    private final Canvas canvas;

    public GameScene(Pane parent, int width, int height) {
        super(parent);
        canvas = new Canvas(width, height);
        parent.getChildren().add(canvas);
    }

    public void redraw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        GameLogic.getInstance().getCharacter().draw();
        Room currentRoom = GameLogic.getInstance().getCurrentRoom();
        for (Obstacle e : currentRoom.getObstacles())
            e.draw();
        for (EnemyCharacter e : currentRoom.getEnemyCharacters())
            e.draw();
//      todo: Add draw()
//        for (Item e : currentRoom.getItems())
//            e.draw();
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
