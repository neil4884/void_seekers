package com.game.void_seekers.logic;

import com.game.void_seekers.tools.Coordinates;

public final class GameUtils {
    public static boolean inBound(Coordinates coordinates, int width, int height) {
        return coordinates.x + width <= GameLogic.WIN_WIDTH &&
                coordinates.x >= 0 &&
                coordinates.y + width <= GameLogic.WIN_HEIGHT &&
                coordinates.y >= 0;
    }

    public static boolean outofBound(Coordinates coordinates, int width, int height) {
        return !inBound(coordinates, width, height);
    }
}
