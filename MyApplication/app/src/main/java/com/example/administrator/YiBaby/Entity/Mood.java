package com.example.administrator.YiBaby.Entity;

/**
 * Created by Administrator on 2016/1/5.
 */
public class Mood {
    private String title;
    private int imgRes;

    public Mood() {
    }

    public Mood(String title, int imgRes) {
        this.title = title;
        this.imgRes = imgRes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }
}
