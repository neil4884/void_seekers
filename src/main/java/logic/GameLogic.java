package logic;

import com.game.void_seekers.render.GameScene;
import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public final class GameLogic {
    private static final GameLogic instance = new GameLogic();

    private GameScene gameScene;

    public BooleanProperty wPressed = new SimpleBooleanProperty(false);
    public BooleanProperty aPressed = new SimpleBooleanProperty(false);
    public BooleanProperty sPressed = new SimpleBooleanProperty(false);
    public BooleanProperty dPressed = new SimpleBooleanProperty(false);
    public BooleanProperty spacePressed = new SimpleBooleanProperty(false);

    public final AnimationTimer gameLoop = new AnimationTimer() {
        @Override
        public void handle(long now) {
            if (wPressed.get())
                gameScene.y -= 2;
            if (aPressed.get())
                gameScene.x -= 2;
            if (sPressed.get())
                gameScene.y += 2;
            if (dPressed.get())
                gameScene.x += 2;
            gameScene.redraw();
        }
    };

    public GameLogic() {
        System.out.println("Logic Instantiated");
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
}
