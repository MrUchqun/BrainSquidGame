package com.b12.game.getset;

public class FirstGameItem {
    private int image;
    private int imageCount;

    public FirstGameItem(int image) {
        this.image = image;
    }

    public FirstGameItem(int image, int imageCount) {
        this.image = image;
        this.imageCount = imageCount;

    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
