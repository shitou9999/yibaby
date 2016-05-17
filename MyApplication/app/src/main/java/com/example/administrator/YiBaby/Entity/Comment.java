package com.example.administrator.YiBaby.Entity;

/**
 * Created by Administrator on 2016/1/3.
 */
public class Comment {
    private String title;
    private String date;
    private String name;
    private String sbTitle;
    private String clickZan;
    private int[] imgRes;
    private int StyleType;

    public Comment() {
    }

    public Comment(String title, String date, String name, String sbTitle, int[] imgRes,String  clickZan,int styleType) {
        this.title = title;
        this.date = date;
        this.name = name;
        this.sbTitle = sbTitle;
        this.clickZan=clickZan;
        StyleType = styleType;
        this.imgRes = imgRes;
    }

    public Comment(String title, String date, String name, int[] imgRes,String  clickZan, int styleType) {
        this.title = title;
        this.date = date;
        this.name = name;
        this.clickZan=clickZan;
        this.imgRes = imgRes;
        StyleType = styleType;
    }

    public String getClickZan() {
        return clickZan;
    }

    public void setClickZan(String  clickZan) {
        this.clickZan = clickZan;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSbTitle() {
        return sbTitle;
    }

    public void setSbTitle(String sbTitle) {
        this.sbTitle = sbTitle;
    }

    public int[] getImgRes() {
        return imgRes;
    }

    public void setImgRes(int[] imgRes) {
        this.imgRes = imgRes;
    }

    public int getStyleType() {
        return StyleType;
    }

    public void setStyleType(int styleType) {
        StyleType = styleType;
    }
}
