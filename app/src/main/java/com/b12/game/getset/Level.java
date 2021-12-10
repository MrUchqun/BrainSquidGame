package com.b12.game.getset;

public class Level {
    private String levelNumber;
    private int levelStars;


    public Level() {
    }

    public Level(String levelNumber, int levelStars) {
        this.levelNumber = levelNumber;
        this.levelStars = levelStars;
    }

    public String getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(String levelNumber) {
        this.levelNumber = levelNumber;
    }

    public int getLevelStars() {
        return levelStars;
    }

    public void setLevelStars(int levelStars) {
        this.levelStars = levelStars;
    }
}
