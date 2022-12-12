package logic;

import com.game.void_seekers.character.base.PlayableCharacter;
import com.game.void_seekers.character.derived.PlayerIsaac;
import com.game.void_seekers.render.GameScene;
import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public final class GameLogic {
    private static final GameLogic instance = new GameLogic();
    public static final int WIN_WIDTH = 1280;
    public static final int WIN_HEIGHT = 720;

    private GameScene gameScene;
    private final PlayableCharacter character;

    public BooleanProperty wPressed = new SimpleBooleanProperty(false);
    public BooleanProperty aPressed = new SimpleBooleanProperty(false);
    public BooleanProperty sPressed = new SimpleBooleanProperty(false);
    public BooleanProperty dPressed = new SimpleBooleanProperty(false);
    public BooleanProperty spacePressed = new SimpleBooleanProperty(false);

    public final AnimationTimer gameLoop;

    public GameLogic() {
        character = new PlayerIsaac();
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (wPressed.get())
                    character.getCoordinate().y -= character.getSpeed();
                if (aPressed.get())
                    character.getCoordinate().x -= character.getSpeed();
                if (sPressed.get())
                    character.getCoordinate().y += character.getSpeed();
                if (dPressed.get())
                    character.getCoordinate().x += character.getSpeed();
                gameScene.redraw();
            }
        };
    }

    public static GameLogic getInstance() {
        return instance;
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

    public PlayableCharacter getCharacter() {
        return character;
    }
}
