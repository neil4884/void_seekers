package com.game.void_seekers.character.base;

import com.game.void_seekers.character.derived.CharacterHealth;
import com.game.void_seekers.interfaces.Draw;
import com.game.void_seekers.interfaces.Movable;
import com.game.void_seekers.tools.Coordinates;
import javafx.scene.image.Image;

public abstract class GameCharacter extends CharacterHealth implements Movable, Draw {
    protected Image assetImage;
    protected Image assetAnimation;
    protected int damage;
    protected String name;
    protected Coordinates coordinate;
    protected int width = 100;
    protected int height = 100;

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

    public Coordinates getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinates coordinate) {
        this.coordinate = coordinate;
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

    public Image getAssetImage() {
        return assetImage;
    }

    public void setAssetImage(Image assetImage) {
        this.assetImage = assetImage;
    }

    public Image getAssetAnimation() {
        return assetAnimation;
    }

    public void setAssetAnimation(Image assetAnimation) {
        this.assetAnimation = assetAnimation;
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
}
