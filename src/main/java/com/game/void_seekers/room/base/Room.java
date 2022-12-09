package com.game.void_seekers.room.base;

public abstract class Room {
    protected String floorURL;
    protected String upperWallURL;
    protected String lowerWallURL;
    protected String leftWallURL;
    protected String rightWallURL;
    protected boolean open;

    public Room(String floorURL, String upperWallURL, String lowerWallURL, String leftWallURL, String rightWallURL) {
        this.floorURL = floorURL;
        this.upperWallURL = upperWallURL;
        this.lowerWallURL = lowerWallURL;
        this.leftWallURL = leftWallURL;
        this.rightWallURL = rightWallURL;
        setOpen(true);
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    //TODO: implements if room is not open when enemy is inside
}
