package com.example.administrator.YiBaby.Entity;

/**
 * Created by Administrator on 2016/1/5.
 */
public class Mood2 {
    private String title;
    private String sbtitle;
    private int imgRes;
    private int type;
    public Mood2() {
    }

    public Mood2(String title, String sbtitle,int imgRes,int type) {
        this.title = title;
        this.sbtitle=sbtitle;
        this.imgRes = imgRes;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSbtitle() {
        return sbtitle;
    }

    public void setSbtitle(String sbtitle) {
        this.sbtitle = sbtitle;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }
}
