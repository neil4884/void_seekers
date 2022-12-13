package com.game.void_seekers.logic;

import com.game.void_seekers.character.base.EnemyCharacter;
import com.game.void_seekers.character.base.PlayableCharacter;
import com.game.void_seekers.character.derived.PlayerIsaac;
import com.game.void_seekers.render.GameScene;
import com.game.void_seekers.room.base.Room;
import com.game.void_seekers.room.derived.SpawnRoom;
import com.game.void_seekers.tools.Coordinates;
import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public final class GameLogic {
    //  Window Resolution
    public static final int WIN_WIDTH = 1280;
    public static final int WIN_HEIGHT = 800;
    public static final int FLOOR_WIDTH = 1080;
    public static final int FLOOR_HEIGHT = 600;
    public static final int WALL_SIZE = 100;

    //  Coordinate presets
    public static final Coordinates TOP_LEFT = new Coordinates(0, 0);
    public static final Coordinates TOP_CENTER = new Coordinates(WIN_WIDTH / 2, 0);
    public static final Coordinates TOP_RIGHT = new Coordinates(WIN_WIDTH, 0);

    public static final Coordinates BOTTOM_LEFT = new Coordinates(0, WIN_HEIGHT);
    public static final Coordinates BOTTOM_CENTER = new Coordinates(WIN_WIDTH / 2, WIN_HEIGHT);
    public static final Coordinates BOTTOM_RIGHT = new Coordinates(WIN_WIDTH, WIN_HEIGHT);

    public static final Coordinates MIDDLE_LEFT = new Coordinates(0, WIN_HEIGHT / 2);
    public static final Coordinates MIDDLE_CENTER = new Coordinates(WIN_WIDTH / 2, WIN_HEIGHT / 2);
    public static final Coordinates MIDDLE_RIGHT = new Coordinates(WIN_WIDTH, WIN_HEIGHT / 2);

    //  Instance static instantiation
    private static final GameLogic instance = new GameLogic();

    //  Game scene
    private GameScene gameScene;

    //  Main character and entities
    private PlayableCharacter character;
    private Room currentRoom;

    //  Key pressed booleans
    public BooleanProperty wPressed = new SimpleBooleanProperty(false);
    public BooleanProperty aPressed = new SimpleBooleanProperty(false);
    public BooleanProperty sPressed = new SimpleBooleanProperty(false);
    public BooleanProperty dPressed = new SimpleBooleanProperty(false);
    public BooleanProperty spacePressed = new SimpleBooleanProperty(false);
    private BooleanProperty spaceFlag = new SimpleBooleanProperty(false);

    //  Game loops and events
    public final GameEvent gameEvent;
    public final AnimationTimer inputLoop;
    public final Thread gameLoop;

    public GameLogic() {
        inputLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                boolean anyCollision = false;

                for (EnemyCharacter enemy : currentRoom.getEnemyCharacters()) {
                    if (GameUtils.isCollided(character, enemy)) {
                        anyCollision = true;
                        break;
                    }
                }

                if (!anyCollision) {
                    if (wPressed.get()) {
                        int new_y = character.getCoordinate().y - character.getSpeed();
                        if (GameUtils.inBound(new Coordinates(character.getCoordinate().x, new_y),
                                character.getWidth(), character.getHeight()))
                            character.getCoordinate().y = new_y;
                    }
                    if (aPressed.get()) {
                        int new_x = character.getCoordinate().x - character.getSpeed();
                        if (GameUtils.inBound(new Coordinates(new_x, character.getCoordinate().y),
                                character.getWidth(), character.getHeight()))
                            character.getCoordinate().x = new_x;
                    }
                    if (sPressed.get()) {
                        int new_y = character.getCoordinate().y + character.getSpeed();
                        if (GameUtils.inBound(new Coordinates(character.getCoordinate().x, new_y),
                                character.getWidth(), character.getHeight()))
                            character.getCoordinate().y = new_y;
                    }
                    if (dPressed.get()) {
                        int new_x = character.getCoordinate().x + character.getSpeed();
                        if (GameUtils.inBound(new Coordinates(new_x, character.getCoordinate().y),
                                character.getWidth(), character.getHeight()))
                            character.getCoordinate().x = new_x;
                    }
                }

                if (spaceFlag.get()) {
                    System.out.println("Attacked!");
                    spaceFlag.set(false);
                }
                gameScene.redraw();
            }
        };
        gameEvent = new GameEvent();
        gameLoop = new Thread(gameEvent);
    }

    public static GameLogic getInstance() {
        return instance;
    }

    public void init() {
        PlayableCharacter p = new PlayerIsaac();
        Room r = new SpawnRoom(0);

        p.setCoordinate(MIDDLE_CENTER.minus(new Coordinates(p.getWidth() / 2)));

        GameLogic.getInstance().setCharacter(p);
        GameLogic.getInstance().setCurrentRoom(r);
    }

    public void transitionToNextRoom(Room nextRoom) {
        System.out.println("Next room!");
    }

    public static GraphicsContext getGraphicsContext() {
        return GameLogic.getInstance().getCanvas().getGraphicsContext2D();
    }

    public void keyHandler(KeyEvent e, boolean property) {
        if (e.getCode() == KeyCode.W)
            wPressed.set(property);
        if (e.getCode() == KeyCode.A)
            aPressed.set(property);
        if (e.getCode() == KeyCode.S)
            sPressed.set(property);
        if (e.getCode() == KeyCode.D)
            dPressed.set(property);
        if (e.getCode() == KeyCode.SPACE)
            spacePressed.set(property);
    }

    public void keyPressedHandler(KeyEvent e) {
        keyHandler(e, true);
    }

    public void keyReleasedHandler(KeyEvent e) {
        keyHandler(e, false);
        if (e.getCode() == KeyCode.SPACE)
            spaceFlag.set(true);
    }

    public GameScene getGameScene() {
        return gameScene;
    }

    public void setGameScene(GameScene gameScene) {
        this.gameScene = gameScene;
    }

    public Canvas getCanvas() {
        return gameScene.getCanvas();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public PlayableCharacter getCharacter() {
        return character;
    }

    public void setCharacter(PlayableCharacter character) {
        this.character = character;
    }
}
