package com.game.void_seekers.floor;

public class Floor {
    private int[][] grid = new int[13][7];
    private int level;
    private String floorURL;
    private String shade;

    public Floor(int level, String floorURL) {
        setLevel(level);
        setFloorURL(floorURL);
        makeFloorShade(floorURL, level);
    }

    //TODO: floor texture gets darker the deeper player goes
    public void makeFloorShade(String floorURL, int level) {}

    public int getSquare(int x, int y) {
        return grid[x][y];
    }
    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getFloorURL() {
        return floorURL;
    }

    public void setFloorURL(String floorURL) {
        this.floorURL = floorURL;
    }
}
