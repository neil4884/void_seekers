package com.game.void_seekers.logic;

import com.game.void_seekers.character.base.EnemyCharacter;
import com.game.void_seekers.character.base.GameCharacter;
import com.game.void_seekers.character.base.PlayableCharacter;
import com.game.void_seekers.interfaces.Attack;
import com.game.void_seekers.render.*;
import com.game.void_seekers.room.base.Room;
import com.game.void_seekers.room.base.RoomDirection;
import com.game.void_seekers.room.derived.SpawnRoom;
import com.game.void_seekers.tools.Coordinates;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public final class GameLogic {
    //  Window Resolution
    public static final int WIN_WIDTH = 1280;
    public static final int WIN_HEIGHT = 800;

    public static final int WALL_SIZE = 80;
    public static final int TILE_SIZE = 4 * 16;
    public static final int CHARACTER_SIZE_DEFAULT = 80;

    public static final int FLOOR_WIDTH = WIN_WIDTH - 2 * WALL_SIZE; // 1120
    public static final int FLOOR_HEIGHT = WIN_HEIGHT - 2 * WALL_SIZE; // 640

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

    public static final Coordinates FLOOR_TOP_LEFT = new Coordinates(WALL_SIZE, WALL_SIZE);
    public static final Coordinates FLOOR_TOP_RIGHT = new Coordinates(WIN_WIDTH - WALL_SIZE, WALL_SIZE);
    public static final Coordinates FLOOR_BOTTOM_LEFT = new Coordinates(WALL_SIZE, WIN_HEIGHT - WALL_SIZE);
    public static final Coordinates FLOOR_BOTTOM_RIGHT = new Coordinates(WIN_WIDTH - WALL_SIZE, WIN_HEIGHT - WALL_SIZE);

    // Room doors hitboxes
    public static final int DOOR_LENGTH = 48;
    public static final int DOOR_THRESHOLD = 10;
    public static final Coordinates TOP_DOOR = TOP_CENTER.add(-DOOR_LENGTH / 2, DOOR_THRESHOLD);
    public static final Coordinates BOTTOM_DOOR = BOTTOM_CENTER.add(-DOOR_LENGTH / 2, -WALL_SIZE - DOOR_THRESHOLD);
    public static final Coordinates LEFT_DOOR = MIDDLE_LEFT.add(DOOR_THRESHOLD, -DOOR_LENGTH / 2);
    public static final Coordinates RIGHT_DOOR = MIDDLE_RIGHT.add(-WALL_SIZE - DOOR_THRESHOLD, -DOOR_LENGTH / 2);
    public static final Coordinates HORZ_DOOR_SIZE = new Coordinates(DOOR_LENGTH, WALL_SIZE);
    public static final Coordinates VERT_DOOR_SIZE = new Coordinates(WALL_SIZE, DOOR_LENGTH);

    //  Instance static instantiation
    private static final GameLogic instance = new GameLogic();

    //  Game scene and root pane
    private Stage stage;
    private AbstractScene currentScene;
    private GameScene gameScene;
    private MenuScene menuScene;
    private EndGameScene endGameScene;

    private HealthBar healthBar;
    private InventoryBar inventoryBar;
    private Pane rootPane;

    //  Score
    private static int score = 0;

    //  Main character and entities
    private PlayableCharacter character;
    private volatile Room currentRoom;

    //  Key pressed booleans
    private static final BooleanProperty wPressed = new SimpleBooleanProperty(false);
    private static final BooleanProperty aPressed = new SimpleBooleanProperty(false);
    private static final BooleanProperty sPressed = new SimpleBooleanProperty(false);
    private static final BooleanProperty dPressed = new SimpleBooleanProperty(false);
    private static final BooleanProperty spacePressed = new SimpleBooleanProperty(false);
    private static final BooleanProperty escPressed = new SimpleBooleanProperty(false);
    private static final BooleanProperty spaceFlag = new SimpleBooleanProperty(false);

    //  Game loops and events
    public final GameEvent gameEvent;
    public final EnemyEvent enemyEvent;
    public final AnimationTimer pollingLoop;
    public final Thread gameLoop;
    public final Thread enemyLoop;

    public GameLogic() {
        pollingLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (currentScene instanceof GameScene) {
                    GameLogic.getInstance().pollInputsInGame();
                    GameLogic.getInstance().enemiesTargetPlayer();
                    healthBar.redraw();
                    inventoryBar.redraw();
                } else if (currentScene instanceof MenuScene) {
                    GameLogic.getInstance().pollInputsInMenu();
                }

                currentScene.redraw();
            }
        };

        gameEvent = new GameEvent();
        gameLoop = new Thread(gameEvent);
        enemyEvent = new EnemyEvent();
        enemyLoop = new Thread(enemyEvent);
    }

    public static GameLogic getInstance() {
        return instance;
    }

    public void init(PlayableCharacter playableCharacter) {
        playableCharacter.setCoordinate(
                MIDDLE_CENTER.minus(new Coordinates(playableCharacter.getWidth() / 2))
        );
        Room r = new SpawnRoom(0);

        GameLogic.getInstance().setCharacter(playableCharacter);
        GameLogic.getInstance().setCurrentRoom(r);


        GameLogic.getInstance().gameLoop.start();
        GameLogic.getInstance().enemyLoop.start();
    }

    public void endGame() {
        //  todo: END GAME POPUP WINDOW
        System.out.println("Player died.");
        pollingLoop.stop();
        gameLoop.interrupt();
        gameEvent.kill();
        enemyLoop.interrupt();
        enemyEvent.kill();
    }

    public void exit() {
        pollingLoop.stop();
        gameLoop.interrupt();
        gameEvent.kill();
        enemyLoop.interrupt();
        enemyEvent.kill();
        Platform.exit();
        System.exit(0);
    }

    public void pollInputsInMenu() {

    }

    public void pollInputsInGame() {
        PlayableCharacter player = GameLogic.getInstance().getCharacter();

        if (escPressed.get()) {
            exit();
        }
        if (wPressed.get()) {
            int new_y = player.getCoordinate().y - player.getSpeed();
            if (GameUtils.inBound(new Coordinates(player.getCoordinate().x, new_y),
                    player.getWidth(), player.getHeight()))
                player.getCoordinate().y = new_y;
        }
        if (aPressed.get()) {
            int new_x = player.getCoordinate().x - player.getSpeed();
            if (GameUtils.inBound(new Coordinates(new_x, player.getCoordinate().y),
                    player.getWidth(), player.getHeight()))
                player.getCoordinate().x = new_x;
        }
        if (sPressed.get()) {
            int new_y = player.getCoordinate().y + player.getSpeed();
            if (GameUtils.inBound(new Coordinates(player.getCoordinate().x, new_y),
                    player.getWidth(), player.getHeight()))
                player.getCoordinate().y = new_y;
        }
        if (dPressed.get()) {
            int new_x = player.getCoordinate().x + player.getSpeed();
            if (GameUtils.inBound(new Coordinates(new_x, player.getCoordinate().y),
                    player.getWidth(), player.getHeight()))
                player.getCoordinate().x = new_x;
        }
        if (spaceFlag.get()) {
            for (EnemyCharacter enemy : GameLogic.getInstance().getCurrentRoom().getEnemyCharacters())
                GameLogic.getInstance().attack(player, enemy);
            spaceFlag.set(false);
        }
    }

    public void removeDeadEnemies(ArrayList<EnemyCharacter> enemies) {
//      Play dead animation
        Thread deadAnimation = new Thread(() -> {
            for (EnemyCharacter enemy : enemies) {
                enemy.setAssetImage(enemy.getAssetDeadAnimation());
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            GameLogic.getInstance().getCurrentRoom().getEnemyCharacters().removeAll(enemies);
        });

        deadAnimation.start();
    }

    public void enemiesTargetPlayer() {
//      Player targeting system (run as fast as player's input)
        for (EnemyCharacter enemy : GameLogic.getInstance().getCurrentRoom().getEnemyCharacters()) {
            if (enemy.isDead())
                continue;
            Coordinates playerPosition = GameLogic.getInstance().getCharacter().getCoordinate();
            Coordinates enemyPosition = enemy.getCoordinate();
            int dx = playerPosition.x - enemyPosition.x;
            int dy = playerPosition.y - enemyPosition.y;
            double los = Math.sqrt(dx * dx + dy * dy);
            if (los == 0.0)
                continue;
            enemy.setCoordinate(
                    (int) (enemyPosition.x + enemy.getSpeed() * 0.5 * (dx / los)),
                    (int) (enemyPosition.y + enemy.getSpeed() * 0.5 * (dy / los))
            );
        }
    }

    public void attack(Attack attackableCharacter, GameCharacter characterToAttack) {
        if (!GameUtils.isCollided((GameCharacter) attackableCharacter, characterToAttack))
            return;
        if (characterToAttack.isInvincible())
            return;

        attackableCharacter.attack(characterToAttack);

        Thread hurtAnimation = new Thread(() -> {
            for (int i = 0; i < 4; ++i) {
                characterToAttack.setAssetImage(characterToAttack.getAssetHurtAnimation());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {
                }
                characterToAttack.setAssetImage(characterToAttack.getAssetDefaultImage());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {
                }
            }

            characterToAttack.setAssetImage(characterToAttack.getAssetDefaultImage());
        });

        if (characterToAttack instanceof PlayableCharacter) {
            Thread invincibleFrame = new Thread(() -> {
                characterToAttack.setInvincible(true);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {
                }
                characterToAttack.setInvincible(false);
            });

            Thread healthWarning = new Thread(() -> {
                for (int i = 0; i < 4; ++i) {
                    GameLogic.getInstance().getHealthBar().setHealthColor(Color.RED);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ignored) {
                    }
                    GameLogic.getInstance().getHealthBar().setHealthColor(Color.WHITE);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ignored) {
                    }
                }
                GameLogic.getInstance().getHealthBar().setHealthColor(Color.WHITE);
            });

            invincibleFrame.start();
            healthWarning.start();
        }

        if (!characterToAttack.isDead())
            hurtAnimation.start();
    }

    public void transitionToNextRoom(RoomDirection direction) {
        Room nextRoom = GameLogic.getInstance().getCurrentRoom();
        PlayableCharacter player = GameLogic.getInstance().getCharacter();

        switch (direction) {
            case TOP: {
                Room topRoom = GameLogic.getInstance().getCurrentRoom().getTopRoom();
                if (topRoom == null)
                    topRoom = new SpawnRoom(GameLogic.getInstance().getCurrentRoom().getDifficulty() + 1);

                nextRoom = topRoom;
                GameLogic.getInstance().getCurrentRoom().setTopRoom(topRoom);
                topRoom.setBottomRoom(GameLogic.getInstance().getCurrentRoom());
                player.setCoordinate(player.getCoordinate().x, FLOOR_BOTTOM_RIGHT.y - 2 * player.getHeight());
                break;
            }
            case BOTTOM: {
                Room bottomRoom = GameLogic.getInstance().getCurrentRoom().getBottomRoom();
                if (bottomRoom == null)
                    bottomRoom = new SpawnRoom(GameLogic.getInstance().getCurrentRoom().getDifficulty() + 1);

                nextRoom = bottomRoom;
                GameLogic.getInstance().getCurrentRoom().setBottomRoom(bottomRoom);
                bottomRoom.setTopRoom(GameLogic.getInstance().getCurrentRoom());
                player.setCoordinate(player.getCoordinate().x, FLOOR_TOP_LEFT.y + player.getHeight());
                break;
            }
            case LEFT: {
                Room leftRoom = GameLogic.getInstance().getCurrentRoom().getLeftRoom();
                if (leftRoom == null)
                    leftRoom = new SpawnRoom(GameLogic.getInstance().getCurrentRoom().getDifficulty() + 1);

                nextRoom = leftRoom;
                GameLogic.getInstance().getCurrentRoom().setLeftRoom(leftRoom);
                leftRoom.setRightRoom(GameLogic.getInstance().getCurrentRoom());
                player.setCoordinate(FLOOR_BOTTOM_RIGHT.x - 2 * player.getHeight(), player.getCoordinate().y);
                break;
            }
            case RIGHT: {
                Room rightRoom = GameLogic.getInstance().getCurrentRoom().getRightRoom();
                if (rightRoom == null)
                    rightRoom = new SpawnRoom(GameLogic.getInstance().getCurrentRoom().getDifficulty() + 1);

                nextRoom = rightRoom;
                GameLogic.getInstance().getCurrentRoom().setRightRoom(rightRoom);
                rightRoom.setLeftRoom(GameLogic.getInstance().getCurrentRoom());
                player.setCoordinate(FLOOR_TOP_LEFT.x + player.getHeight(), player.getCoordinate().y);
                break;
            }
            default:
                break;
        }

        GameLogic.getInstance().setCurrentRoom(nextRoom);
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
        if (e.getCode() == KeyCode.ESCAPE)
            escPressed.set(property);
    }

    public void keyPressedHandler(KeyEvent e) {
        keyHandler(e, true);
    }

    public void keyReleasedHandler(KeyEvent e) {
        keyHandler(e, false);
        if (e.getCode() == KeyCode.SPACE)
            spaceFlag.set(true);
    }

    public void switchScene(AbstractScene nextScene) {
        GameLogic.getInstance().setCurrentScene(nextScene);
        GameLogic.getInstance().getStage().setScene(GameLogic.getInstance().getCurrentScene());
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void setCurrentScene(AbstractScene currentScene) {
        this.currentScene = currentScene;
    }

    public GameScene getGameScene() {
        return gameScene;
    }

    public void setGameScene(GameScene gameScene) {
        this.gameScene = gameScene;
    }

    public MenuScene getMenuScene() {
        return menuScene;
    }

    public void setMenuScene(MenuScene menuScene) {
        this.menuScene = menuScene;
    }

    public EndGameScene getEndGameScene() {
        return endGameScene;
    }

    public void setEndGameScene(EndGameScene endGameScene) {
        this.endGameScene = endGameScene;
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }

    public void setHealthBar(HealthBar healthBar) {
        this.healthBar = healthBar;
    }

    public InventoryBar getInventoryBar() {
        return inventoryBar;
    }

    public void setInventoryBar(InventoryBar inventoryBar) {
        this.inventoryBar = inventoryBar;
    }

    public Pane getRootPane() {
        return rootPane;
    }

    public void setRootPane(Pane rootPane) {
        this.rootPane = rootPane;
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

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        GameLogic.score = Math.max(score, 0);
    }

    public static void addScore(int score) {
        setScore(getScore() + score);
    }
}
