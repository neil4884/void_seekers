package com.game.void_seekers.obstacle.base;

import com.game.void_seekers.interfaces.Draw;
import com.game.void_seekers.tools.Coordinates;
import javafx.scene.image.Image;

public abstract class Obstacle implements Draw {
    protected int height;
    protected String name;
    protected Image image;
    protected int size;
    protected Coordinates coordinates;

    public Obstacle(int height) {
        setHeight(height);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = Math.min(0, height);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
