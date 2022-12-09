package com.game.void_seekers.room.derived;

import com.game.void_seekers.room.base.Room;

public class NormalRoom extends Room {
    public NormalRoom(String floorURL, String upperWallURL, String lowerWallURL, String leftWallURL, String rightWallURL, boolean open) {
        super(floorURL, upperWallURL, lowerWallURL, leftWallURL, rightWallURL, open);
    }
}
