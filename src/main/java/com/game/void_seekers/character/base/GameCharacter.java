package com.game.void_seekers.character.base;

import com.game.void_seekers.interfaces.Draw;
import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import com.game.void_seekers.tools.Coordinates;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public abstract class GameCharacter extends CharacterHealth implements Draw {
    protected Image assetDefaultImage;
    protected Image assetImage;
    protected Image assetHurtAnimation = GameAssets.transparentImage;
    protected Image assetDeadAnimation;
    protected int speed = 0;
    protected int damage;
    protected String name;
    protected Coordinates coordinate;
    protected int width = GameLogic.CHARACTER_SIZE_DEFAULT;
    protected int height = GameLogic.CHARACTER_SIZE_DEFAULT;
    protected boolean isInvincible;

    public GameCharacter() {
        super(1, 1);
        setDamage(0);
        setCoordinate(new Coordinates());
        setName("Untitled Character");
    }

    public GameCharacter(String name, int health, int x, int y) {
        this(name, health, new Coordinates(x, y));
    }

    public GameCharacter(String name, int health, Coordinates coordinate) {
        super(health, 0);
        setCoordinate(coordinate);
        setName(name);
        setDamage(0);
    }

    public GameCharacter(String name, int health, int type, int x, int y) {
        this(name, health, type, new Coordinates(x, y));
    }

    public GameCharacter(String name, int health, int type, Coordinates coordinate) {
        super(health, type);
        setCoordinate(coordinate);
        setName(name);
        setDamage(0);
    }

    public GameCharacter(String name, int health1, int type1, int health2, int type2, int x, int y) {
        this(name, health1, type1, health2, type2, new Coordinates(x, y));
    }

    public GameCharacter(String name, int health1, int type1, int health2, int type2, Coordinates coordinate) {
        super(health1, type1, health2, type2);
        setCoordinate(coordinate);
        setName(name);
        setDamage(0);
    }

    public void drawShadow(GraphicsContext gc) {
        double a = gc.getGlobalAlpha();
        Paint p = gc.getFill();
        gc.setGlobalAlpha(0.5);
        gc.setFill(Color.BLACK);

        gc.fillOval(coordinate.x + (double) height * 0.02f,
                coordinate.y + (double) height * 0.8f,
                height * 0.8f,
                (double) height * 0.333333f);

        gc.setGlobalAlpha(a);
        gc.setFill(p);
    }

    public Coordinates getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinates coordinate) {
        this.coordinate = coordinate;
    }

    public void setCoordinate(int x, int y) {
        this.coordinate.x = x;
        this.coordinate.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = Math.max(damage, 1);
    }

    public Image getAssetDefaultImage() {
        return assetDefaultImage;
    }

    public void setAssetDefaultImage(Image assetDefaultImage) {
        this.assetDefaultImage = assetDefaultImage;
    }

    public Image getAssetImage() {
        return assetImage;
    }

    public void setAssetImage(Image assetImage) {
        this.assetImage = assetImage;
    }

    public Image getAssetHurtAnimation() {
        return assetHurtAnimation;
    }

    public void setAssetHurtAnimation(Image assetHurtAnimation) {
        this.assetHurtAnimation = assetHurtAnimation;
    }

    public Image getAssetDeadAnimation() {
        return assetDeadAnimation;
    }

    public void setAssetDeadAnimation(Image assetDeadAnimation) {
        this.assetDeadAnimation = assetDeadAnimation;
    }

    public void setRedHealth(int health, int max_health) {
        super.setMaxRedHealth(max_health);
        super.setRedHealth(health);
    }

    public void setBlueHealth(int health, int max_health) {
        super.setMaxBlueHealth(max_health);
        super.setBlueHealth(health);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isInvincible() {
        return isInvincible;
    }

    public void setInvincible(boolean invincible) {
        isInvincible = invincible;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = Math.max(Math.min(speed, 15), 4);
    }
}
