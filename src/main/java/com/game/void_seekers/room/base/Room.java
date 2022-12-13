package com.game.void_seekers.room.base;

import com.game.void_seekers.character.base.EnemyCharacter;
import com.game.void_seekers.character.derived.EnemyGaper;
import com.game.void_seekers.interfaces.Draw;
import com.game.void_seekers.item.base.Item;
import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import com.game.void_seekers.obstacle.base.Obstacle;
import com.game.void_seekers.tools.Coordinates;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.ArrayList;

public abstract class Room implements Draw {
    protected int difficulty = 0;
    protected URL floorURL;

    protected URL topWallURL;
    protected URL bottomWallURL;
    protected URL leftWallURL;
    protected URL rightWallURL;

    protected ArrayList<EnemyCharacter> enemyCharacters;
    protected ArrayList<Obstacle> obstacles;
    protected ArrayList<Item> items;

    protected Room rightRoom;
    protected Room leftRoom;
    protected Room topRoom;
    protected Room bottomRoom;

    public Room(int difficulty) {
        this(difficulty,
                GameAssets.floorTemplateURL,
                GameAssets.wallTopTemplateURL,
                GameAssets.wallTopTemplateURL,
                GameAssets.wallLeftTemplateURL,
                GameAssets.wallLeftTemplateURL
        );
    }

    public Room(int difficulty,
                URL floorURL,
                URL topWallURL,
                URL bottomWallURL,
                URL leftWallURL,
                URL rightWallURL) {
        this.difficulty = Math.max(0, difficulty);
        this.floorURL = floorURL;
        this.topWallURL = topWallURL;
        this.bottomWallURL = bottomWallURL;
        this.leftWallURL = leftWallURL;
        this.rightWallURL = rightWallURL;

        this.setEnemyCharacters(new ArrayList<>());
        this.setItems(new ArrayList<>());
        this.setObstacles(new ArrayList<>());

        generateRoom();
    }

    public abstract void generateRoom();

    @Override
    public void draw() {
        GraphicsContext gc = GameLogic.getGraphicsContext();

        Image floor = GameAssets.loadImage(floorURL, GameLogic.FLOOR_WIDTH, GameLogic.FLOOR_HEIGHT);
        Image left = GameAssets.loadImage(leftWallURL, GameLogic.WALL_SIZE, 400);

        gc.drawImage(floor, 100, 100);
    }

    public ArrayList<EnemyCharacter> getEnemyCharacters() {
        return enemyCharacters;
    }

    public void setEnemyCharacters(ArrayList<EnemyCharacter> enemyCharacters) {
        this.enemyCharacters = enemyCharacters;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public void setObstacles(ArrayList<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public Room getRightRoom() {
        return rightRoom;
    }

    public void setRightRoom(Room rightRoom) {
        this.rightRoom = rightRoom;
    }

    public Room getLeftRoom() {
        return leftRoom;
    }

    public void setLeftRoom(Room leftRoom) {
        this.leftRoom = leftRoom;
    }

    public Room getTopRoom() {
        return topRoom;
    }

    public void setTopRoom(Room topRoom) {
        this.topRoom = topRoom;
    }

    public Room getBottomRoom() {
        return bottomRoom;
    }

    public void setBottomRoom(Room bottomRoom) {
        this.bottomRoom = bottomRoom;
    }
}
