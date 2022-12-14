package com.game.void_seekers.room.base;

import com.game.void_seekers.character.base.EnemyCharacter;
import com.game.void_seekers.character.derived.EnemyBobo;
import com.game.void_seekers.character.derived.EnemyGaper;
import com.game.void_seekers.character.derived.EnemyNecromancer;
import com.game.void_seekers.character.derived.EnemySwamper;
import com.game.void_seekers.interfaces.Draw;
import com.game.void_seekers.item.base.Item;
import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import com.game.void_seekers.logic.GameUtils;
import com.game.void_seekers.obstacle.base.Obstacle;
import com.game.void_seekers.projectile.base.Projectile;
import com.game.void_seekers.tools.Coordinates;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Pair;

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
    protected ArrayList<Projectile> projectiles;

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
                GameAssets.wallBottomTemplateURL,
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

        topImage = GameAssets.loadImage(topWallURL, GameLogic.FLOOR_WIDTH, GameLogic.WALL_SIZE);
        bottomImage = GameAssets.loadImage(bottomWallURL, GameLogic.FLOOR_WIDTH, GameLogic.WALL_SIZE);
        leftImage = GameAssets.loadImage(leftWallURL, GameLogic.WALL_SIZE, GameLogic.WIN_HEIGHT);
        rightImage = GameAssets.loadImage(rightWallURL, GameLogic.WALL_SIZE, GameLogic.WIN_HEIGHT);

        this.setEnemyCharacters(new ArrayList<>());
        this.setItems(new ArrayList<>());
        this.setObstacles(new ArrayList<>());
        this.setProjectiles(new ArrayList<>());

        generateRoom();
    }

    public abstract void generateRoom();

    @Override
    public void draw() {
        GraphicsContext gc = GameLogic.getGraphicsContext();

        for (int i = GameLogic.FLOOR_TOP_LEFT.x, x = 0;
             i <= GameLogic.FLOOR_BOTTOM_RIGHT.x;
             i += GameLogic.TILE_SIZE, ++x) {

            for (int j = GameLogic.FLOOR_TOP_LEFT.y, y = 0;
                 j <= GameLogic.FLOOR_BOTTOM_RIGHT.y;
                 j += GameLogic.TILE_SIZE, ++y) {

                gc.drawImage(floorTiles[x][y], i, j);
            }
        }

        gc.drawImage(
                GameAssets.arrowTopImage,
                GameLogic.TOP_CENTER.x - (double) GameLogic.TILE_SIZE * 0.75f,
                GameLogic.FLOOR_TOP_RIGHT.y + (double) GameLogic.TILE_SIZE * 0.5f
        );
        gc.drawImage(
                GameAssets.arrowBottomImage,
                GameLogic.TOP_CENTER.x - (double) GameLogic.TILE_SIZE * 0.75f,
                GameLogic.FLOOR_BOTTOM_LEFT.y - (double) GameLogic.TILE_SIZE * 1.5f
        );
        gc.drawImage(
                GameAssets.arrowLeftImage,
                GameLogic.FLOOR_TOP_LEFT.x,
                GameLogic.MIDDLE_RIGHT.y
        );
        gc.drawImage(
                GameAssets.arrowRightImage,
                GameLogic.FLOOR_TOP_RIGHT.x - GameLogic.TILE_SIZE,
                GameLogic.MIDDLE_RIGHT.y
        );

        gc.drawImage(topImage, GameLogic.FLOOR_TOP_LEFT.x, GameLogic.TOP_LEFT.y);
        gc.drawImage(bottomImage, GameLogic.FLOOR_BOTTOM_LEFT.x, GameLogic.FLOOR_BOTTOM_LEFT.y);
        gc.drawImage(leftImage, GameLogic.TOP_LEFT.x, GameLogic.TOP_LEFT.y);
        gc.drawImage(rightImage, GameLogic.FLOOR_TOP_RIGHT.x, GameLogic.TOP_RIGHT.y);
    }

    protected void generateObstacles() {
        ArrayList<Pair<Coordinates, Obstacle>> obstacles = GameUtils.obstacleRandomizer(32);
        ArrayList<Obstacle> obs = new ArrayList<>();
        for (Pair<Coordinates, Obstacle> pair : obstacles) {
            pair.getValue().setCoordinates(pair.getKey());
            obs.add(pair.getValue());
        }
        setObstacles(obs);
    }

    protected void generateEnemies() {
        ArrayList<EnemyCharacter> enemies = new ArrayList<>();
        int totalEnemies = difficulty;

        EnemyCharacter ec = new EnemyBobo();
        ec.setDamage(ec.getDamage() + difficulty / 4);
        ec.setCoordinate(GameUtils.coordinatesRandomizer());
        enemies.add(ec);

        if (difficulty >= 2) {
            ec = new EnemyNecromancer();
            ec.setCoordinate(GameUtils.coordinatesRandomizer());
            enemies.add(ec);
            --totalEnemies;
        }

        if (difficulty >= 4) {
            ec = new EnemySwamper();
            ec.setCoordinate(GameUtils.coordinatesRandomizer());
            enemies.add(ec);
            --totalEnemies;
        }

        for (int i = 0; i < totalEnemies; ++i) {
            ec = new EnemyGaper();
            ec.setDamage(ec.getDamage() + difficulty / 2);
            ec.setCoordinate(GameUtils.coordinatesRandomizer());
            enemies.add(ec);
        }
        setEnemyCharacters(enemies);
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
        this.difficulty = Math.max(difficulty, 0);
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

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void setProjectiles(ArrayList<Projectile> projectiles) {
        this.projectiles = projectiles;
    }
}
