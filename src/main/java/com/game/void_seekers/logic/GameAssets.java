package com.game.void_seekers.logic;

import javafx.scene.image.Image;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.Objects;

public final class GameAssets {
    private static final String resourcesPath = "com/game/void_seekers/";

    //  Game Font
    public static final URL gameFontURL = getURL("font/ThaleahFat.ttf");

    //  Battery
    public static final URL normalBatteryURL = getURL("battery/normalBattery.png");
    public static final URL largeBatteryURL = getURL("battery/largeBattery.png");

    //  Bomb
    public static final URL bombURL = getURL("bomb/bomb.png");
    public static final URL explodingBombURL = getURL("bomb/explodingBomb.png");

    //  Coin
    public static final URL dimeURL = getURL("coin/dime.png");
    public static final URL nickelURL = getURL("coin/nickel.png");
    public static final URL pennyURL = getURL("coin/penny.png");

    //  Enemy
    public static final URL gaperURL = getURL("enemy/gaper.png");
    public static final URL gaperHurtURL = getURL("enemy/gaper_hurt.png");
    public static final URL gaperDeadURL = getURL("enemy/gaper_dead.png");

    //  Health
    public static final URL emptyRedHealthURL = getURL("health/emptyRedHealth.png");
    public static final URL fullBlueHealthURL = getURL("health/fullBlueHealth.png");
    public static final URL fullRedHealthURL = getURL("health/fullRedHealth.png");
    public static final URL halfBlueHealthURL = getURL("health/halfBlueHealth.png");
    public static final URL halfRedHealthURL = getURL("health/halfRedHealth.png");

    //  Heart
    public static final URL blueHeartURL = getURL("heart/blueHeart.png");
    public static final URL redHeartURL = getURL("heart/redHeart.png");
    public static final URL halfBlueHeartURL = getURL("heart/halfBlueHeart.png");
    public static final URL halfRedHeartURL = getURL("heart/halfRedHeart.png");

    // Active
    public static final URL bookOfRageURL = getURL("active/bookOfRage.png");

    //  Passive
    public static final URL eternalBlessingURL = getURL("passive/eternalBlessing.png");
    public static final URL loneEyeURL = getURL("passive/loneEye.png");

    // Trinkets
    public static final URL lingeringFeatherURL = getURL("trinket/lingeringFeather.png");

    //  Player
    public static final URL isaacURL = getURL("player/isaac.png");
    public static final URL soulURL = getURL("player/soul.png");
    public static final URL jaredURL = getURL("player/jared.png");

    //  Room: Template
    public static final URL floorTemplateURL = getURL("room/floor_template.png");
    public static final URL wallLeftTemplateURL = getURL("room/wall_left_art.png");
    public static final URL wallTopTemplateURL = getURL("room/wall_top_art.png");
    public static final URL wallBottomTemplateURL = getURL("room/wall_bottom_art.png");

    //  Icon
    public static final URL coinIconURL = getURL("icons/coinIcon.png");
    public static final URL bombIconURL = getURL("icons/bombIcon.png");
    public static final URL transparentURL = getURL("transparent/transparent.png");
    public static final Image transparentImage = loadImage(transparentURL, GameLogic.CHARACTER_SIZE_DEFAULT);

    //  Projectile
    public static final URL normalProjectileURL = getURL("projectile/normalProjectile.png");
    public static final URL redProjectileURL = getURL("projectile/redProjectile.png");
    public static final URL whiteProjectileURL = getURL("projectile/redProjectile.png");

    // Obstacle
    //public static final URL normalProjectileURL = getURL("projectile/normalProjectile.png");
    public static final URL crateURL = getURL("obstacle/crate.png");


    // Floor tiles (Image, read-only)
    public static final Image tileImage1 = GameAssets.fromPath("tiles/floor_1.png", GameLogic.TILE_SIZE);
    public static final Image tileImage2 = GameAssets.fromPath("tiles/floor_2.png", GameLogic.TILE_SIZE);
    public static final Image tileImage3 = GameAssets.fromPath("tiles/floor_3.png", GameLogic.TILE_SIZE);
    public static final Image tileImage4 = GameAssets.fromPath("tiles/floor_4.png", GameLogic.TILE_SIZE);
    public static final Image tileImage5 = GameAssets.fromPath("tiles/floor_5.png", GameLogic.TILE_SIZE);
    public static final Image tileImage6 = GameAssets.fromPath("tiles/floor_6.png", GameLogic.TILE_SIZE);
    public static final Image tileImage7 = GameAssets.fromPath("tiles/floor_7.png", GameLogic.TILE_SIZE);
    public static final Image tileImage8 = GameAssets.fromPath("tiles/floor_8.png", GameLogic.TILE_SIZE);
    public static final Image arrowLeftImage = GameAssets.fromPath("room/arrow_left.png", GameLogic.TILE_SIZE);
    public static final Image arrowRightImage = GameAssets.fromPath("room/arrow_right.png", GameLogic.TILE_SIZE);
    public static final Image arrowTopImage = GameAssets.fromPath("room/arrow_top.png", GameLogic.TILE_SIZE);
    public static final Image arrowBottomImage = GameAssets.fromPath("room/arrow_bottom.png", GameLogic.TILE_SIZE);

    private static URL getURL(String relativePath) {
        return Objects.requireNonNull(
                GameAssets.class.getClassLoader().getResource(resourcesPath + relativePath)
        );
    }

    public static Image loadImage(URL url, double fitLength) {
        return loadImage(url, fitLength, true);
    }

    public static Image loadImage(URL url, double fitLength, boolean mode) {
        if (mode)
            return new Image(url.toExternalForm(), 0, fitLength, true, true);
        else
            return new Image(url.toExternalForm(), fitLength, 0, true, true);
    }

    public static Image loadImage(URL url, double fitWidth, double fitHeight) {
        return new Image(url.toExternalForm(), fitWidth, fitHeight, false, true);
    }

    public static Image fromPath(String relativePath, double fitLength) {
        return loadImage(getURL(relativePath), fitLength);
    }

    public static Font loadGameFont(double size) {
        return Font.loadFont(gameFontURL.toExternalForm(), size);
    }
}
