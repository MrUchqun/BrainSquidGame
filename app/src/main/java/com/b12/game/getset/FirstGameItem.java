package com.b12.game.getset;

public class FirstGameItem {
    private int image;
    private int id;

    public FirstGameItem(int image, int id) {
        this.image = image;
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
