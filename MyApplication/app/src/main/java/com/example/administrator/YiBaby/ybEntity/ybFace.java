package com.example.administrator.YiBaby.ybEntity;

/**
 * Created by Administrator on 2016/3/30.
 */
public class ybFace {
    private int icon;
    private String imgRes;

    public ybFace(int icon, String imgRes) {
        this.icon = icon;
        this.imgRes = imgRes;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getImgRes() {
        return imgRes;
    }

    public void setImgRes(String imgRes) {
        this.imgRes = imgRes;
    }
}
