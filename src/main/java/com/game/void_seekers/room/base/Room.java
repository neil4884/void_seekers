package com.game.void_seekers.room.base;

import com.game.void_seekers.character.base.EnemyCharacter;
import com.game.void_seekers.item.base.Item;
import com.game.void_seekers.obstacle.base.Obstacle;

import java.util.ArrayList;

public abstract class Room {
    protected String floorURL;

    protected String upperWallURL;
    protected String lowerWallURL;
    protected String leftWallURL;
    protected String rightWallURL;

    protected ArrayList<EnemyCharacter> enemyCharacters;
    protected ArrayList<Obstacle> obstacles;
    protected ArrayList<Item> items;

    protected Room rightRoom;
    protected Room leftRoom;
    protected Room topRoom;
    protected Room bottomRoom;

    public Room(String floorURL, String upperWallURL, String lowerWallURL, String leftWallURL, String rightWallURL) {
        this.floorURL = floorURL;
        this.upperWallURL = upperWallURL;
        this.lowerWallURL = lowerWallURL;
        this.leftWallURL = leftWallURL;
        this.rightWallURL = rightWallURL;
    }

    public void generateRoom() {

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
