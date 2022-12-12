package com.game.void_seekers.obstacle.base;

import com.game.void_seekers.interfaces.Draw;

public abstract class Obstacle implements Draw {
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
