package com.game.void_seekers.item.derived;

import com.game.void_seekers.item.base.PocketItem;
import com.game.void_seekers.logic.GameAssets;
import com.game.void_seekers.logic.GameLogic;
import javafx.scene.canvas.GraphicsContext;

public class Battery extends PocketItem {
    private int power;

    public Battery(int type) {
        super(type <= 0 ? "normalBattery" : "largeBattery");
        setPower(type <= 0 ? 2 : 999);
        addAssets(type);
    }

    public void addAssets(int type) {
        if (type == 0) {
            setAssetImage(GameAssets.loadImage(GameAssets.normalBatteryURL, size));
        } else {
            setAssetImage(GameAssets.loadImage(GameAssets.largeBatteryURL, size));
        }
    }

    public Battery() {
        super("battery");
        setPower(2);
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public void draw() {
        GraphicsContext gc = GameLogic.getGraphicsContext();
        gc.drawImage(super.getAssetImage(), coordinate.x, coordinate.y);
    }
}
