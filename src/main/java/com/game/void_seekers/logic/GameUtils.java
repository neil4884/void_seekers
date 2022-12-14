package com.game.void_seekers.logic;

import com.game.void_seekers.character.base.GameCharacter;
import com.game.void_seekers.item.base.Item;
import com.game.void_seekers.room.base.Room;
import com.game.void_seekers.tools.Coordinates;
import com.game.void_seekers.tools.RandomIntRange;
import javafx.scene.image.Image;

public final class GameUtils {
    private static final Image[] FLOOR_TILES = {
            GameAssets.tileImage1,
            GameAssets.tileImage2,
            GameAssets.tileImage3,
            GameAssets.tileImage4,
            GameAssets.tileImage5,
            GameAssets.tileImage6,
            GameAssets.tileImage7,
            GameAssets.tileImage8
    };


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
        int xDistance = (gc1.getCoordinate().x + gc1.getWidth() / 2) - (gc2.getCoordinate().x + gc2.getWidth() / 2);
        int yDistance = (gc1.getCoordinate().y + gc1.getHeight() / 2) - (gc2.getCoordinate().y + gc2.getHeight() / 2);
        boolean xCheck = Math.abs(xDistance) * 2 <= (gc1.getWidth() + gc2.getWidth());
        boolean yCheck = Math.abs(yDistance) * 2 <= (gc1.getHeight() + gc2.getHeight());

        return xCheck && yCheck;
    }

    public static boolean isCollided(GameCharacter gc, Coordinates hitBoxCoord, Coordinates hitBoxSize) {
        int xDistance = (gc.getCoordinate().x + gc.getWidth() / 2) - (hitBoxCoord.x + hitBoxSize.x / 2);
        int yDistance = (gc.getCoordinate().y + gc.getHeight() / 2) - (hitBoxCoord.y + hitBoxSize.y / 2);
        boolean xCheck = Math.abs(xDistance) * 2 <= (gc.getWidth() + hitBoxSize.x);
        boolean yCheck = Math.abs(yDistance) * 2 <= (gc.getHeight() + hitBoxSize.y);

        return xCheck && yCheck;
    }

    public static boolean isCollided(GameCharacter gc, Item item) {
        int xDistance = (gc.getCoordinate().x + gc.getWidth() / 2) - (item.getCoordinate().x + item.getSize() / 2);
        int yDistance = (gc.getCoordinate().y + gc.getHeight() / 2) - (item.getCoordinate().y + item.getSize() / 2);
        boolean xCheck = Math.abs(xDistance) * 2 <= (gc.getWidth() + item.getSize());
        boolean yCheck = Math.abs(yDistance) * 2 <= (gc.getHeight() + item.getSize());

        return xCheck && yCheck;
    }

    public static Room randomizeNextRoom() {
        RandomIntRange randomizer = new RandomIntRange(1, 10);
        return null;
    }

    public static Image[][] tilesRandomizer(int sizeX, int sizeY) {
        Image[][] img = new Image[sizeX][sizeY];

        RandomIntRange randomizer = new RandomIntRange(0, 7);
        for (int i = 0; i < sizeX; ++i) {
            for (int j = 0; j < sizeY; ++j) {
                img[i][j] = FLOOR_TILES[randomizer.next() % FLOOR_TILES.length];
            }
        }
        return img;
    }
}
