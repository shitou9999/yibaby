package com.example.administrator.YiBaby.ybEntity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/31.
 */
public class EnergySigin implements Serializable{

    /**
     * addDate : 1459351435000
     * addIp : 221.0.100.165
     * addUser : 石头
     * content : 心情一片大好啊!
     * id : 586
     * memberId : 4961
     * score : 1
     * sid : 25
     * signInType : 3
     * updateDate :
     * updateIp :
     * updateUser :
     * uuid : 97a14e01-4af2-44ae-979f-301b2e0fb451
     */
    private long addDate;
    private String addIp;
    private String addUser;
    private String content;
    private int id;
    private int memberId;
    private int score;
    private int sid;
    private int signInType;
    private String updateDate;
    private String updateIp;
    private String updateUser;
    private String uuid;

    public void setAddDate(long addDate) {
        this.addDate = addDate;
    }

    public void setAddIp(String addIp) {
        this.addIp = addIp;
    }

    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public void setSignInType(int signInType) {
        this.signInType = signInType;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public void setUpdateIp(String updateIp) {
        this.updateIp = updateIp;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getAddDate() {
        return addDate;
    }

    public String getAddIp() {
        return addIp;
    }

    public String getAddUser() {
        return addUser;
    }

    public String getContent() {
        return content;
    }

    public int getId() {
        return id;
    }

    public int getMemberId() {
        return memberId;
    }

    public int getScore() {
        return score;
    }

    public int getSid() {
        return sid;
    }

    public int getSignInType() {
        return signInType;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public String getUpdateIp() {
        return updateIp;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public String getUuid() {
        return uuid;
    }
}
