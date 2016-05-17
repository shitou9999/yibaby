package com.example.administrator.YiBaby.ybEntity;

/**
 * Created by Administrator on 2016/3/17.
 */
public class BBS_Comment {
//    {"count":44,"resultCode":"0001","resultMessage":"操作成功","resultObject":"[{
//        viewCount\":0}]","score":57,"totalCount":44}
    private int count;
    private String resultCode;
    private String resultMessage;
    private String resultObject;
    private int score;
    private int totalCount;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getResultObject() {
        return resultObject;
    }

    public void setResultObject(String resultObject) {
        this.resultObject = resultObject;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
