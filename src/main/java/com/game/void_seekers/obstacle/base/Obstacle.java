package com.game.void_seekers.obstacle.base;

public abstract class Obstacle {
    protected int height;
    protected String name;
    protected String textureURL;

    public Obstacle(int height, String textureURL) {
        this.height = height;
        this.textureURL = textureURL;
    }

    public String getTextureURL() {
        return textureURL;
    }

    public void setTextureURL(String textureURL) {
        this.textureURL = textureURL;
    }
}
