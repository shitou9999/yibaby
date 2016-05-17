package com.example.administrator.YiBaby.ybEntity;

/**
 * Created by Administrator on 2016/3/6.
 */
public class ybMilkGroup {
    private int image;
    private String title;

    public ybMilkGroup(int image, String title) {
        this.image = image;
        this.title = title;
    }

    public ybMilkGroup() {
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
