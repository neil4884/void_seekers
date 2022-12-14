package com.game.void_seekers.logic;

import com.game.void_seekers.character.base.EnemyCharacter;
import com.game.void_seekers.character.base.GameCharacter;
import com.game.void_seekers.character.base.PlayableCharacter;
import com.game.void_seekers.character.derived.PlayerIsaac;
import com.game.void_seekers.character.derived.PlayerJared;
import com.game.void_seekers.character.derived.PlayerSoul;
import com.game.void_seekers.interfaces.Attack;
import com.game.void_seekers.item.base.EffectItem;
import com.game.void_seekers.item.derived.Exploding;
import com.game.void_seekers.obstacle.base.Obstacle;
import com.game.void_seekers.projectile.base.Projectile;
import com.game.void_seekers.projectile.derived.NormalProjectile;
import com.game.void_seekers.render.*;
import com.game.void_seekers.room.base.Room;
import com.game.void_seekers.room.base.RoomDirection;
import com.game.void_seekers.room.derived.EnemyRoom;
import com.game.void_seekers.room.derived.SpawnRoom;
import com.game.void_seekers.tools.Coordinates;
import com.game.void_seekers.tools.RandomIntRange;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public final class GameLogic {
    //  Window Resolution
    private static final boolean USE_HIGH_RES = false;

    public static final int WIN_WIDTH = USE_HIGH_RES ? 1920 : 1280;
    public static final int WIN_HEIGHT = USE_HIGH_RES ? 1200 : 800;

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

    //  Room doors hitboxes
    public static final int DOOR_LENGTH = 48;
    public static final int DOOR_THRESHOLD = 10;
    public static final Coordinates TOP_DOOR = TOP_CENTER.add(-DOOR_LENGTH / 2, DOOR_THRESHOLD);
    public static final Coordinates BOTTOM_DOOR = BOTTOM_CENTER.add(-DOOR_LENGTH / 2, -WALL_SIZE - DOOR_THRESHOLD);
    public static final Coordinates LEFT_DOOR = MIDDLE_LEFT.add(DOOR_THRESHOLD, -DOOR_LENGTH / 2);
    public static final Coordinates RIGHT_DOOR = MIDDLE_RIGHT.add(-WALL_SIZE - DOOR_THRESHOLD, -DOOR_LENGTH / 2);
    public static final Coordinates HORZ_DOOR_SIZE = new Coordinates(DOOR_LENGTH, WALL_SIZE);
    public static final Coordinates VERT_DOOR_SIZE = new Coordinates(WALL_SIZE, DOOR_LENGTH);

    //  Other parameters
    public static final int EXPLODE_DURATION = 2000;
    public static final int EXPLODE_ANIMATION_COUNT = 16;

    //  Instance static instantiation
    private static final GameLogic instance = new GameLogic();
    private static GameState state = GameState.MENU;
    public static final Set<EffectItem> usedItem = new HashSet<>();

    //  Game scene and root pane
    private Stage stage;
    private AbstractScene currentScene;
    private GameScene gameScene;
    private MenuScene menuScene;
    private EndGameScene endGameScene;

    private HealthBar healthBar;
    private InventoryBar inventoryBar;
    private Pane rootPane;
    private ActiveBar activeBar;
    private TrinketBar trinketBar;

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
    private static final BooleanProperty ePressed = new SimpleBooleanProperty(false);

    private static final BooleanProperty spacePressed = new SimpleBooleanProperty(false);
    private static final BooleanProperty escPressed = new SimpleBooleanProperty(false);

    private static final BooleanProperty aFlag = new SimpleBooleanProperty(false);
    private static final BooleanProperty eFlag = new SimpleBooleanProperty(false);
    private static final BooleanProperty dFlag = new SimpleBooleanProperty(false);
    private static final BooleanProperty spaceFlag = new SimpleBooleanProperty(false);

    //  Game loops and events
    public GameEvent gameEvent;
    public EnemyEvent enemyEvent;
    public AnimationTimer pollingLoop;
    public Thread gameLoop;
    public Thread enemyLoop;

    public GameLogic() {
        GameAssets.bgMediaPlayer.setOnEndOfMedia(() -> GameAssets.bgMediaPlayer.seek(Duration.ZERO));

        pollingLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                switch (state) {
                    case ONGOING -> {
//                      Game mechanics
                        GameLogic.getInstance().pollInputsInGame();
                        GameLogic.getInstance().enemiesTargetPlayer();
                        GameLogic.getInstance().pollProjectiles();
                        GameLogic.getInstance().pollObstacles();

//                      Draw bars
                        healthBar.redraw();
                        inventoryBar.redraw();
                        trinketBar.redraw();
                        activeBar.redraw();
                    }
                    case MENU -> GameLogic.getInstance().pollInputsInMenu();
                    case END -> GameLogic.getInstance().pollInputsInEnd();
                }

                GameLogic.getInstance().getCurrentScene().redraw();
            }
        };
    }

    public static GameLogic getInstance() {
        return instance;
    }

    public void init(PlayableCharacter playableCharacter) {
        GameAssets.bgMediaPlayer.seek(Duration.ZERO);
        GameAssets.bgMediaPlayer.play();

        score = 0;
        setState(GameState.ONGOING);
        switchScene(GameLogic.getInstance().getGameScene());

        gameEvent = new GameEvent();
        gameLoop = new Thread(gameEvent);
        enemyEvent = new EnemyEvent();
        enemyLoop = new Thread(enemyEvent);

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
        GameAssets.bgMediaPlayer.stop();
        GameAssets.diedMediaPlayer.seek(Duration.ZERO);
        GameAssets.diedMediaPlayer.play();

        gameLoop.interrupt();
        gameEvent.kill();
        enemyLoop.interrupt();
        enemyEvent.kill();

        setState(GameState.END);
        switchScene(GameLogic.getInstance().getEndGameScene());
    }

    public void exit() {
        if (pollingLoop != null)
            pollingLoop.stop();
        if (gameLoop != null)
            gameLoop.interrupt();
        if (gameEvent != null)
            gameEvent.kill();
        if (enemyLoop != null)
            enemyLoop.interrupt();
        if (enemyEvent != null)
            enemyEvent.kill();

        Platform.exit();
        System.exit(0);
    }

    public void pollInputsInMenu() {
        if (escPressed.get()) {
            exit();
        }

        if (aFlag.get()) {
            GameLogic.getInstance().getMenuScene().moveSelection(-1);
            aFlag.set(false);
        }

        if (dFlag.get()) {
            GameLogic.getInstance().getMenuScene().moveSelection(1);
            dFlag.set(false);
        }

        if (spaceFlag.get()) {
            String selection = GameLogic.getInstance().getMenuScene().getSelection();
            PlayableCharacter p;
            switch (selection) {
                case "JARED" -> p = new PlayerJared();
                case "SOUL" -> p = new PlayerSoul();
                default -> p = new PlayerIsaac();
            }
            GameLogic.getInstance().init(p);
            spaceFlag.set(false);
        }
    }

    public void pollInputsInEnd() {
        if (escPressed.get()) {
            exit();
        }

        if (spaceFlag.get()) {
            GameLogic.setState(GameState.MENU);
            GameLogic.getInstance().switchScene(GameLogic.getInstance().getMenuScene());
            GameAssets.diedMediaPlayer.stop();
            spaceFlag.set(false);
        }
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
        if (eFlag.get()) {
            if (GameLogic.getInstance().getCharacter().hasBomb()) {
                useBomb();
            }
            eFlag.set(false);
        }

        if (spaceFlag.get()) {
            boolean w = wPressed.get();
            boolean a = aPressed.get();
            boolean s = sPressed.get();
            boolean d = dPressed.get();

            if (!w && !a && !s && !d)
                a = true;

            if (w && s)
                s = false;

            if (a && d)
                d = false;

            boolean[] directions = {w, a, s, d};

            GameLogic.getInstance().shootProjectile(
                    new NormalProjectile(GameLogic.getInstance().getCharacter().getCoordinate().clone()),
                    directions
            );

            for (EnemyCharacter enemy : GameLogic.getInstance().getCurrentRoom().getEnemyCharacters())
                GameLogic.getInstance().attack(player, enemy);
            spaceFlag.set(false);
        }
    }

    public void shootProjectile(Projectile projectile, boolean[] directions) {
//        boolean of directions: {W, A, S, D}
        projectile.setDirections(directions);
        GameLogic.getInstance().getCurrentRoom().getProjectiles().add(projectile);
    }

    public void pollObstacles() {
        ArrayList<Obstacle> toRemove = new ArrayList<>();
        for (Obstacle obstacle : GameLogic.getInstance().getCurrentRoom().getObstacles()) {
            if (!GameUtils.isCollided(
                    GameLogic.getInstance().getCharacter(),
                    obstacle.getCoordinates(),
                    new Coordinates(obstacle.getSize()))
            ) continue;

            toRemove.add(obstacle);
        }

        GameLogic.getInstance().getCurrentRoom().getObstacles().removeAll(toRemove);
    }

    public void pollProjectiles() {
        ArrayList<Projectile> toRemove = new ArrayList<>();
        for (Projectile projectile : GameLogic.getInstance().getCurrentRoom().getProjectiles()) {
            int projectileSpeed = projectile.getSpeed();
            boolean w = projectile.getDirections()[0];
            boolean a = projectile.getDirections()[1];
            boolean s = projectile.getDirections()[2];
            boolean d = projectile.getDirections()[3];
            if (!GameUtils.inBound(projectile.getCoordinate(), projectile.getSize(), projectile.getSize()))
                toRemove.add(projectile);

            for (EnemyCharacter enemy : GameLogic.getInstance().getCurrentRoom().getEnemyCharacters()) {
                if (!GameUtils.isCollided(
                        enemy,
                        projectile.getCoordinate(),
                        new Coordinates(projectile.getSize()))) continue;
                enemy.reduceHealth(projectile.getDamage());

                Thread hurtAnimation = new Thread(() -> {
                    for (int i = 0; i < 8; ++i) {
                        enemy.setAssetImage(enemy.getAssetHurtAnimation());
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException ignored) {
                        }
                        enemy.setAssetImage(enemy.getAssetDefaultImage());
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException ignored) {
                        }
                    }

                    enemy.setAssetImage(enemy.getAssetDefaultImage());
                });

                hurtAnimation.start();
                toRemove.add(projectile);
            }
            if (w)
                projectile.setCoordinate(projectile.getCoordinate().minus(0, projectileSpeed));
            if (a)
                projectile.setCoordinate(projectile.getCoordinate().minus(projectileSpeed, 0));
            if (s)
                projectile.setCoordinate(projectile.getCoordinate().add(0, projectileSpeed));
            if (d)
                projectile.setCoordinate(projectile.getCoordinate().add(projectileSpeed, 0));
        }

        GameLogic.getInstance().getCurrentRoom().getProjectiles().removeAll(toRemove);
    }

    public void useBomb() {
        PlayableCharacter player = GameLogic.getInstance().getCharacter();
        Coordinates playerPos = player.getCoordinate();

        player.setBombs(player.getBombs() - 1);

        Exploding exp = new Exploding();
        exp.setCoordinate(playerPos.clone());
        GameLogic.getInstance().getCurrentRoom().getItems().add(exp);
        long duration = (long) (0.5 * GameLogic.EXPLODE_DURATION / GameLogic.EXPLODE_ANIMATION_COUNT);
        long shakeDuration = GameLogic.EXPLODE_DURATION / 128;
        Coordinates originalCoordinate = new Coordinates(exp.getCoordinate());

        Thread subThread = new Thread(() -> {
            RandomIntRange randomizer = new RandomIntRange(-8, 8);
            for (int i = 0; i < GameLogic.EXPLODE_DURATION; ++i) {
                try {
                    exp.setCoordinate(originalCoordinate.add(randomizer.next(), randomizer.next()));
                    Thread.sleep(shakeDuration);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread bombCountdown = new Thread(() -> {
            for (int i = 0; i < GameLogic.EXPLODE_ANIMATION_COUNT; ++i) {
                try {

                    exp.setAssetImage(GameAssets.loadImage(GameAssets.explodingBombURL, exp.getSize()));
                    Thread.sleep(duration);
                    exp.setAssetImage(GameAssets.loadImage(GameAssets.bombURL, exp.getSize()));
                    Thread.sleep(duration);
                } catch (InterruptedException ignored) {
                }
            }
            explode(exp);
        });

        subThread.start();
        bombCountdown.start();
    }

    public void explode(Exploding exp) {
        for (EnemyCharacter enemy : GameLogic.getInstance().getCurrentRoom().getEnemyCharacters()) {
            if (GameUtils.isWithinRange(exp, enemy, 150)) {
                enemy.reduceHealth(40);
            }
        }
        if (GameUtils.isWithinRange(exp, GameLogic.getInstance().getCharacter(), 150)) {
            GameLogic.getInstance().getCharacter().reduceHealth(2);
        }

        Thread explodeAnimation = new Thread(() -> {
            try {
                exp.setAssetImage(GameAssets.loadImage(GameAssets.explodeParticleURL, 3 * exp.getSize()));
                Thread.sleep(500);
                GameLogic.getInstance().getCurrentRoom().getItems().remove(exp);
            } catch (InterruptedException ignored) {
            }
        });
        explodeAnimation.start();
    }

    public void removeDeadEnemies(ArrayList<EnemyCharacter> enemies) {
//      Play dead animation
        Thread deadAnimation = new Thread(() -> {
            for (EnemyCharacter enemy : enemies) {
                enemy.setAssetImage(enemy.getAssetDeadAnimation());
            }

            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (EnemyCharacter enemy : enemies) {
                GameLogic.addScore(enemy.getDamage());
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
            double dx = playerPosition.x - enemyPosition.x - (new RandomIntRange(-100, 100).next());
            double dy = playerPosition.y - enemyPosition.y - (new RandomIntRange(-100, 100).next());
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
                    topRoom = new EnemyRoom(GameLogic.getInstance().getCurrentRoom().getDifficulty() + 1);

                nextRoom = topRoom;
                GameLogic.getInstance().getCurrentRoom().setTopRoom(topRoom);
                topRoom.setBottomRoom(GameLogic.getInstance().getCurrentRoom());
                player.setCoordinate(player.getCoordinate().x, FLOOR_BOTTOM_RIGHT.y - 2 * player.getHeight());
                break;
            }
            case BOTTOM: {
                Room bottomRoom = GameLogic.getInstance().getCurrentRoom().getBottomRoom();
                if (bottomRoom == null)
                    bottomRoom = new EnemyRoom(GameLogic.getInstance().getCurrentRoom().getDifficulty() + 1);

                nextRoom = bottomRoom;
                GameLogic.getInstance().getCurrentRoom().setBottomRoom(bottomRoom);
                bottomRoom.setTopRoom(GameLogic.getInstance().getCurrentRoom());
                player.setCoordinate(player.getCoordinate().x, FLOOR_TOP_LEFT.y + player.getHeight());
                break;
            }
            case LEFT: {
                Room leftRoom = GameLogic.getInstance().getCurrentRoom().getLeftRoom();
                if (leftRoom == null)
                    leftRoom = new EnemyRoom(GameLogic.getInstance().getCurrentRoom().getDifficulty() + 1);

                nextRoom = leftRoom;
                GameLogic.getInstance().getCurrentRoom().setLeftRoom(leftRoom);
                leftRoom.setRightRoom(GameLogic.getInstance().getCurrentRoom());
                player.setCoordinate(FLOOR_BOTTOM_RIGHT.x - 2 * player.getHeight(), player.getCoordinate().y);
                break;
            }
            case RIGHT: {
                Room rightRoom = GameLogic.getInstance().getCurrentRoom().getRightRoom();
                if (rightRoom == null)
                    rightRoom = new EnemyRoom(GameLogic.getInstance().getCurrentRoom().getDifficulty() + 1);

                nextRoom = rightRoom;
                GameLogic.getInstance().getCurrentRoom().setRightRoom(rightRoom);
                rightRoom.setLeftRoom(GameLogic.getInstance().getCurrentRoom());
                player.setCoordinate(FLOOR_TOP_LEFT.x + player.getHeight(), player.getCoordinate().y);
                break;
            }
            default:
                break;
        }
        GameLogic.getGraphicsContext().clearRect(0, 0,
                GameLogic.getInstance().getCanvas().getWidth(),
                GameLogic.getInstance().getCanvas().getHeight());

        Platform.runLater(() -> {
            FadeTransition ft = new FadeTransition();
            ft.setDuration(Duration.millis(500));
            ft.setFromValue(0.75d);
            ft.setToValue(1d);
            ft.setAutoReverse(true);
            ft.setNode(GameLogic.getInstance().getRootPane());
            ft.play();

            GameLogic.getInstance().getStage().setScene(GameLogic.getInstance().getCurrentScene());
        });

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
        if (e.getCode() == KeyCode.E)
            ePressed.set(property);
    }

    public void keyPressedHandler(KeyEvent e) {
        keyHandler(e, true);
    }

    public void keyReleasedHandler(KeyEvent e) {
        keyHandler(e, false);
        if (e.getCode() == KeyCode.SPACE)
            spaceFlag.set(true);
        if (state == GameState.MENU) {
            if (e.getCode() == KeyCode.A)
                aFlag.set(true);
            if (e.getCode() == KeyCode.D)
                dFlag.set(true);
        } else if (state == GameState.ONGOING) {
            if (e.getCode() == KeyCode.E)
                eFlag.set(true);
        }
    }

    public void switchScene(AbstractScene nextScene) {
        GameLogic.getInstance().setRootPane((Pane) nextScene.getRoot());
        GameLogic.getInstance().setCurrentScene(nextScene);
        Platform.runLater(() -> {
            FadeTransition ft = new FadeTransition();
            ft.setDuration(Duration.millis(1000));
            ft.setFromValue(0.5d);
            ft.setToValue(1d);
            ft.setAutoReverse(true);
            ft.setNode(GameLogic.getInstance().getRootPane());
            ft.play();

            GameLogic.getInstance().getStage().setScene(GameLogic.getInstance().getCurrentScene());
        });
    }

    public AbstractScene getCurrentScene() {
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

    public static GameState getState() {
        return state;
    }

    public static void setState(GameState state) {
        GameLogic.state = state;
    }

    public ActiveBar getActiveBar() {
        return activeBar;
    }

    public void setActiveBar(ActiveBar activeBar) {
        this.activeBar = activeBar;
    }

    public TrinketBar getTrinketBar() {
        return trinketBar;
    }

    public void setTrinketBar(TrinketBar trinketBar) {
        this.trinketBar = trinketBar;
    }
}
