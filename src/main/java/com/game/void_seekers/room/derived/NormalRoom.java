package com.game.void_seekers.room.derived;

import com.game.void_seekers.room.base.Room;

import java.net.URL;

public abstract class NormalRoom extends Room {
    public NormalRoom(int difficulty) {
        super(difficulty);
    }
    public NormalRoom(int difficulty,
                      URL floorURL,
                      URL topWallURL,
                      URL bottomWallURL,
                      URL leftWallURL,
                      URL rightWallURL) {
        super(difficulty, floorURL, topWallURL, bottomWallURL, leftWallURL, rightWallURL);
    }
}
