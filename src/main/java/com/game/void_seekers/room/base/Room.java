package com.game.void_seekers.room.base;

import com.game.void_seekers.character.base.EnemyCharacter;
import com.game.void_seekers.interfaces.Draw;
import com.game.void_seekers.item.base.Item;
import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import com.game.void_seekers.obstacle.base.Obstacle;
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

    protected Image floorImage;
    protected Image leftImage;
    protected Image rightImage;
    protected Image topImage;
    protected Image bottomImage;

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

        floorImage = GameAssets.loadImage(floorURL, GameLogic.FLOOR_WIDTH, GameLogic.FLOOR_HEIGHT);
        leftImage = GameAssets.loadImage(leftWallURL, GameLogic.WALL_SIZE, GameLogic.FLOOR_HEIGHT);
        rightImage = GameAssets.loadImage(rightWallURL, GameLogic.WALL_SIZE, GameLogic.FLOOR_HEIGHT);
        topImage = GameAssets.loadImage(topWallURL, GameLogic.WIN_WIDTH, GameLogic.WALL_SIZE);
        bottomImage = GameAssets.loadImage(bottomWallURL, GameLogic.WIN_WIDTH, GameLogic.WALL_SIZE);

        this.setEnemyCharacters(new ArrayList<>());
        this.setItems(new ArrayList<>());
        this.setObstacles(new ArrayList<>());

        generateRoom();
    }

    public abstract void generateRoom();

    @Override
    public void draw() {
        GraphicsContext gc = GameLogic.getGraphicsContext();

        gc.drawImage(floorImage, GameLogic.WALL_SIZE,
                GameLogic.WALL_SIZE);
        gc.drawImage(topImage, GameLogic.TOP_LEFT.x,
                GameLogic.TOP_LEFT.y);
        gc.drawImage(bottomImage, GameLogic.BOTTOM_LEFT.x,
                GameLogic.BOTTOM_LEFT.y - GameLogic.WALL_SIZE);
        gc.drawImage(leftImage, GameLogic.TOP_LEFT.x,
                GameLogic.TOP_LEFT.y + GameLogic.WALL_SIZE);
        gc.drawImage(rightImage, GameLogic.TOP_RIGHT.x - GameLogic.WALL_SIZE,
                GameLogic.TOP_LEFT.y + GameLogic.WALL_SIZE);
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
        if (this.rightRoom == null)
            this.rightRoom = rightRoom;
    }

    public Room getLeftRoom() {
        return leftRoom;
    }

    public void setLeftRoom(Room leftRoom) {
        if (this.leftRoom == null)
            this.leftRoom = leftRoom;
    }

    public Room getTopRoom() {
        return topRoom;
    }

    public void setTopRoom(Room topRoom) {
        if (this.topRoom == null)
            this.topRoom = topRoom;
    }

    public Room getBottomRoom() {
        return bottomRoom;
    }

    public void setBottomRoom(Room bottomRoom) {
        if (this.topRoom == null)
            this.bottomRoom = bottomRoom;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
