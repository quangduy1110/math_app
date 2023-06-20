package com.example.math_app.model;

public class ListItem {
    private int imageResId;
    private String itemName;
    private int videoResId;

    public ListItem(int imageResId, String itemName, int videoResId) {
        this.imageResId = imageResId;
        this.itemName = itemName;
        this.videoResId = videoResId;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getVideoResId() {
        return videoResId;
    }
}