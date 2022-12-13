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

    //  Heart
    public static final URL blueHeartURL = getURL("heart/blueHeart.png");
    public static final URL redHeartURL = getURL("heart/redHeart.png");
    public static final URL halfBlueHeartURL = getURL("heart/halfBlueHeart.png");
    public static final URL halfRedHeartURL = getURL("heart/halfRedHeart.png");

    //  Passive
    public static final URL eternalBlessingURL = getURL("passive/eternalBlessing.png");
    public static final URL loneEyeURL = getURL("passive/loneEye.png");

    //  Player
    public static final URL isaacURL = getURL("player/isaac.png");

    //  Room: Template
    public static final URL floorTemplateURL = getURL("room/floor_template.png");
    public static final URL wallLeftTemplateURL = getURL("room/wall_left_template.png");
    public static final URL wallTopTemplateURL = getURL("room/wall_top_template.png");

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
}
