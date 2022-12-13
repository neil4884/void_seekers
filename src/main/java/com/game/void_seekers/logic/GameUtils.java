package com.game.void_seekers.logic;

import com.game.void_seekers.character.base.GameCharacter;
import com.game.void_seekers.tools.Coordinates;

public final class GameUtils {
    public static boolean inBound(Coordinates coordinates, int width, int height) {
        return coordinates.x + width <= GameLogic.WALL_SIZE + GameLogic.FLOOR_WIDTH &&
                coordinates.x >= GameLogic.WALL_SIZE &&
                coordinates.y + height <= GameLogic.WALL_SIZE + GameLogic.FLOOR_HEIGHT &&
                coordinates.y >= GameLogic.WALL_SIZE;
    }

    public static boolean outofBound(Coordinates coordinates, int width, int height) {
        return !inBound(coordinates, width, height);
    }

    public static boolean isCollided(GameCharacter gc1, GameCharacter gc2) {

    }
}
