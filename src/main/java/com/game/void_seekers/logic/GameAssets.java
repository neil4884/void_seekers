package com.game.void_seekers.logic;

import javafx.scene.image.Image;

import java.net.URL;
import java.util.Objects;

public final class GameAssets {
    private static final String resourcesPath = "com/game/void_seekers/";

    //  Battery
    public static final URL normalBatteryURL = getURL("battery/normalBattery.png");
    public static final URL largeBatteryURL = getURL("battery/largeBattery.png");

    //  Bomb
    public static final URL bombURL = getURL("bomb/bomb.png");

    //  Coin
    public static final URL dimeURL = getURL("coin/dime.png");
    public static final URL nickelURL = getURL("coin/nickel.png");
    public static final URL pennyURL = getURL("coin/penny.png");

    //  Enemy
    public static final URL gaperURL = getURL("enemy/gaper.png");

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
    public static final URL wallLeftTemplateURL = getURL("room/wall_left_template.png");
    public static final URL wallTopTemplateURL = getURL("room/wall_top_template.png");

    // Icon
    public static final URL coinIconURL = getURL("icons/coinIcon.png");
    public static final URL bombIconURL = getURL("icons/bombIcon.png");
    public static final URL transparentURL = getURL("transparent/transparent.png");
    //Projectile
    public static final URL normalProjectileURL = getURL("projectile/normalProjectile.png");
    public static final URL redProjectileURL = getURL("projectile/redProjectile.png");
    public static final URL whiteProjectileURL = getURL("projectile/redProjectile.png");

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
}
