package com.game.void_seekers.room.base;

import com.game.void_seekers.character.base.EnemyCharacter;
import com.game.void_seekers.interfaces.Draw;
import com.game.void_seekers.item.base.Item;
import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import com.game.void_seekers.logic.GameUtils;
import com.game.void_seekers.obstacle.base.Obstacle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.ArrayList;

public abstract class Room implements Draw {
    protected int difficulty;

    protected Image[][] floorTiles;

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

    protected Image leftImage;
    protected Image rightImage;
    protected Image topImage;
    protected Image bottomImage;

    public Room(int difficulty) {
        this(difficulty,
                GameAssets.wallTopTemplateURL,
                GameAssets.wallTopTemplateURL,
                GameAssets.wallLeftTemplateURL,
                GameAssets.wallLeftTemplateURL
        );
    }

    public Room(int difficulty,
                URL topWallURL,
                URL bottomWallURL,
                URL leftWallURL,
                URL rightWallURL) {

        int x = 1, y = 1;
        for (int i = GameLogic.FLOOR_TOP_LEFT.x; i <= GameLogic.FLOOR_BOTTOM_RIGHT.x; i += GameLogic.TILE_SIZE)
            ++x;
        for (int j = GameLogic.FLOOR_TOP_LEFT.y; j <= GameLogic.FLOOR_BOTTOM_RIGHT.y; j += GameLogic.TILE_SIZE)
            ++y;

        this.floorTiles = GameUtils.tilesRandomizer(x, y);

        this.difficulty = Math.max(0, difficulty);
        this.topWallURL = topWallURL;
        this.bottomWallURL = bottomWallURL;
        this.leftWallURL = leftWallURL;
        this.rightWallURL = rightWallURL;

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

//        gc.drawImage(floorImage, GameLogic.WALL_SIZE, GameLogic.WALL_SIZE);
//      todo: tile gen. draw
        for (int i = GameLogic.FLOOR_TOP_LEFT.x, x = 0;
             i <= GameLogic.FLOOR_BOTTOM_RIGHT.x;
             i += GameLogic.TILE_SIZE, ++x) {

            for (int j = GameLogic.FLOOR_TOP_LEFT.y, y = 0;
                 j <= GameLogic.FLOOR_BOTTOM_RIGHT.y;
                 j += GameLogic.TILE_SIZE, ++y) {

                gc.drawImage(floorTiles[x][y], i, j);
            }
        }

        gc.drawImage(topImage, GameLogic.TOP_LEFT.x, GameLogic.TOP_LEFT.y);
        gc.drawImage(bottomImage, GameLogic.BOTTOM_LEFT.x, GameLogic.BOTTOM_LEFT.y - GameLogic.WALL_SIZE);
        gc.drawImage(leftImage, GameLogic.TOP_LEFT.x, GameLogic.TOP_LEFT.y + GameLogic.WALL_SIZE);
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

    public void setLeftImage(Image leftImage) {
        this.leftImage = leftImage;
    }

    public void setRightImage(Image rightImage) {
        this.rightImage = rightImage;
    }

    public void setTopImage(Image topImage) {
        this.topImage = topImage;
    }

    public void setBottomImage(Image bottomImage) {
        this.bottomImage = bottomImage;
    }
}
