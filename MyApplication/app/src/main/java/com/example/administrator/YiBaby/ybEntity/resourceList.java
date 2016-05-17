package com.example.administrator.YiBaby.ybEntity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/1/27.
 */
public class resourceList implements Serializable{
//    \"resourceList\": [
//    {
//        \"fileName\": \"ef38c292a3624c18bd2e6849fd70bd36.png\",
//        \"fileSize\": \"379987\",
//        \"height\": 506,
//        \"id\": 669,
//        \"imgSmall\": \"http://xxlimg.21-sun.com/upload/25/image/2015/11/25/20151125214640_152.png\",
//        \"sid\": 25,
//        \"topicId\": \"799\",
//        \"width\": 380
//    }
//    ],
//    private String fileName;
//    private String fileSize;
//    private int height;
    private int id;
    private String imgSmall;
//    private int sid;
    private String topicId;
//    private int width;

    public resourceList() {
    }







    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgSmall() {
        return imgSmall;
    }

    public void setImgSmall(String imgSmall) {
        this.imgSmall = imgSmall;
    }



    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }


}
