package com.example.administrator.YiBaby.ybEntity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/17.
 */
public class BBS_oplogsList implements Serializable{
    /**
     * addDate : 1436314597000
     * addUser : 小腾子
     * addUserId : 4693
     * channelId : 7
     * commentId : 0
     * content : 好可爱，跟我家宝宝很搭
     * id : 335
     * img :
     * isShow : 1
     * memberId : 4693
     * memberName :
     * sid : 25
     * topicId : 717
     * uuid: 0cbc2aa4-617f-4714-94b3-fc1cc153d90e
     */
    private long addDate;
    private String addUser;
    private int addUserId;
    private int channelId;
    private int commentId;
    private String content;
    private int id;
    private String img;
    private int isShow;
    private int memberId;
    private String memberName;
    private int sid;
    private int topicId;
    private String uuid;

    public void setAddDate(long addDate) {
        this.addDate = addDate;
    }

    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }

    public void setAddUserId(int addUserId) {
        this.addUserId = addUserId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getAddDate() {
        return addDate;
    }

    public String getAddUser() {
        return addUser;
    }

    public int getAddUserId() {
        return addUserId;
    }

    public int getChannelId() {
        return channelId;
    }

    public int getCommentId() {
        return commentId;
    }

    public String getContent() {
        return content;
    }

    public int getId() {
        return id;
    }

    public String getImg() {
        return img;
    }

    public int getIsShow() {
        return isShow;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public int getSid() {
        return sid;
    }

    public int getTopicId() {
        return topicId;
    }

    public String getUuid() {
        return uuid;
    }
}
