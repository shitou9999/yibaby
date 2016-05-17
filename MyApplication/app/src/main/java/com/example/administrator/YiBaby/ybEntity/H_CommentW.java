package com.example.administrator.YiBaby.ybEntity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/1/27.
 */
public class H_CommentW implements Serializable{
    private String resultCode;
    private String resultMessage;
    private String resultObject;
    private int totalCount;

    public H_CommentW() {
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

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
