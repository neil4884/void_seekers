package com.game.void_seekers.logic;

import com.game.void_seekers.character.base.GameCharacter;
import com.game.void_seekers.item.active.BookOfRage;
import com.game.void_seekers.item.base.Active;
import com.game.void_seekers.item.base.EffectItem;
import com.game.void_seekers.item.base.Item;
import com.game.void_seekers.item.base.Passive;
import com.game.void_seekers.item.passive.EternalBlessing;
import com.game.void_seekers.item.passive.LonelyEye;
import com.game.void_seekers.item.trinkets.LingeringFeather;
import com.game.void_seekers.obstacle.base.Obstacle;
import com.game.void_seekers.obstacle.derived.*;
import com.game.void_seekers.projectile.base.Projectile;
import com.game.void_seekers.room.base.Room;
import com.game.void_seekers.tools.Coordinates;
import com.game.void_seekers.tools.RandomIntRange;
import javafx.scene.image.Image;
import javafx.util.Pair;

import java.util.*;

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

    private static final ArrayList<Class<? extends Item>> itemList = new ArrayList<>(Arrays.asList(
            //Active
            BookOfRage.class,
            //Passive
            EternalBlessing.class,
            LonelyEye.class,
            //Trinket
            LingeringFeather.class
    ));

    private static Obstacle[] newObstacles() {
        return new Obstacle[]{
                new Bush(0),
                new Bush(1),
                new Crate(),
                new Mud(),
                new Spike(0),
                new Spike(1),
                new Water()
        };
    }

    public static boolean inBound(Coordinates coordinates, int width, int height) {
        return coordinates.x + width <= GameLogic.WALL_SIZE + GameLogic.FLOOR_WIDTH &&
                coordinates.x >= GameLogic.WALL_SIZE &&
                coordinates.y + height <= GameLogic.WALL_SIZE + GameLogic.FLOOR_HEIGHT &&
                coordinates.y >= GameLogic.WALL_SIZE;
    }

    public static boolean outOfBound(Coordinates coordinates, int width, int height) {
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

    public static ArrayList<Pair<Coordinates, Obstacle>> obstacleRandomizer(int maxRange) {
        ArrayList<Pair<Coordinates, Obstacle>> obstacles = new ArrayList<>();
        RandomIntRange randomizer = new RandomIntRange(0, Math.max(maxRange, 0));
        RandomIntRange obsRandomizer = new RandomIntRange(0, newObstacles().length - 1);

        for (int i = 0; i < randomizer.next(); ++i) {
            Obstacle ob = newObstacles()[obsRandomizer.next()];
            obstacles.add(new Pair<>(coordinatesRandomizer(), ob));
        }

        return obstacles;
    }

    public static Coordinates coordinatesRandomizer() {
        RandomIntRange xRandomizer = new RandomIntRange(GameLogic.FLOOR_BOTTOM_LEFT.x, GameLogic.FLOOR_BOTTOM_RIGHT.x);
        RandomIntRange yRandomizer = new RandomIntRange(GameLogic.FLOOR_BOTTOM_LEFT.y, GameLogic.FLOOR_TOP_LEFT.y);
        return new Coordinates(xRandomizer.next(), yRandomizer.next());
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

    public static boolean isWithinRange(Coordinates c1, Coordinates c2, int range) {
        return Math.sqrt(Math.pow((c1.x - c2.x), 2) + Math.pow((c1.y - c2.y), 2)) <= range;
    }

    public static EffectItem getRandomEffectItem() {
        int rnd = new Random().nextInt(itemList.size() - 1);
        try {
            EffectItem selected = (EffectItem) GameUtils.itemList.get(rnd).getDeclaredConstructor().newInstance();
            if (hasBeenUsed(selected)) {
                getRandomEffectItem();
            }
            return selected;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void addToUsed(EffectItem item) {
        GameLogic.usedItem.add(item);
    }

    public static void resetUsedItem() {
        GameLogic.usedItem.clear();
    }

    public static boolean hasBeenUsed(EffectItem item) {
        return GameLogic.usedItem.contains(item);
    }

    public static boolean isWithinRange(GameCharacter gc1, GameCharacter gc2, int range) {
        return isWithinRange(gc1.getCoordinate(), gc2.getCoordinate(), range);
    }

    public static boolean isWithinRange(Item item1, GameCharacter gc2, int range) {
        return isWithinRange(item1.getCoordinate(), gc2.getCoordinate(), range);
    }
}
