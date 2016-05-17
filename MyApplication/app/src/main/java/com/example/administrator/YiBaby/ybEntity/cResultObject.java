package com.example.administrator.YiBaby.ybEntity;

import java.io.Serializable;

/**
 * 这是第2层
 * Created by Administrator on 2016/1/27.
 */
public class cResultObject implements Serializable{
     private String applyTo;
    private int brandId;
    private String catName;
    private String content;
    private int id;
    private String img;
    private com.example.administrator.YiBaby.ybEntity.imgList[] imgList;////////////[]//////////////////////////
    private String intro;
    private int isDisable;
    private int isShow;
    private double marketPrice;
    private String name;
    private int price;
    private String scope;
    private String service;
    private int sid;
    private String title;
    private int typeId;
    private String typeName;

    public cResultObject() {
    }

    public cResultObject(String typeName, String name, int price, String scope) {
        this.typeName = typeName;
        this.name = name;
        this.price = price;
        this.scope = scope;
    }

    public com.example.administrator.YiBaby.ybEntity.imgList[] getImgList() {
        return imgList;
    }

    public void setImgList(com.example.administrator.YiBaby.ybEntity.imgList[] imgList) {
        this.imgList = imgList;
    }

    public String getApplyTo() {
        return applyTo;
    }

    public void setApplyTo(String applyTo) {
        this.applyTo = applyTo;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(int isDisable) {
        this.isDisable = isDisable;
    }

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
