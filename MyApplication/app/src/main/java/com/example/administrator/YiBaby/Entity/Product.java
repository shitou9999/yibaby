package com.example.administrator.YiBaby.Entity;

/**
 * Created by Administrator on 2016/1/3.
 */
public class Product {
    private int image1;
    private String text1;
    private String text2;
    private String text3;
    private int image2;
    private String classify;
    private int stype;
    public Product() {
    }

    public Product(int image1, String text1, String text2, String text3, int image2,int stype) {
        this.image1 = image1;
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
        this.image2 = image2;
        this.stype=stype;
    }

    public Product(String classify, int stype) {
        this.classify = classify;
        this.stype = stype;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public int getStype() {
        return stype;
    }

    public void setStype(int stype) {
        this.stype = stype;
    }

    public int getImage1() {
        return image1;
    }

    public String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }

    public String getText3() {
        return text3;
    }

    public int getImage2() {
        return image2;
    }

    public void setImage1(int image1) {
        this.image1 = image1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }

    public void setImage2(int image2) {
        this.image2 = image2;
    }
}
