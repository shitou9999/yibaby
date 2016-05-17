package com.example.administrator.YiBaby.ybEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/3/17.
 */
public class BBS_resultObject implements Serializable{
    private String memberName;
    private long addDate;
    private String addUser;
    private String content;
    private String headImg;
    private int id;
    private int praiseCount;
    private int replyCount;
//    private int shareCount;
//    private int sid;
    private String title;
//    private String uuid;
//    private int viewCount;

    private List<BBS_oplogsList> oplogsList;

    private List<resourceList> resourceList;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public List<BBS_oplogsList> getOplogsList() {
        return oplogsList;
    }

    public void setOplogsList(List<BBS_oplogsList> oplogsList) {
        this.oplogsList = oplogsList;
    }

    public List<resourceList> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<resourceList> resourceList) {
        this.resourceList = resourceList;
    }


    public void setAddDate(long addDate) {
        this.addDate = addDate;
    }

    public String getAddUser() {
        return addUser;
    }

    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }




    public void setContent(String content) {
        this.content = content;
    }



    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public void setId(int id) {
        this.id = id;
    }




    public void setPraiseCount(int praiseCount) {
        this.praiseCount = praiseCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }



    public void setTitle(String title) {
        this.title = title;
    }





    public long getAddDate() {
        return addDate;
    }

    public String getContent() {
        return content;
    }


    public String getHeadImg() {
        return headImg;
    }

    public int getId() {
        return id;
    }






    public int getPraiseCount() {
        return praiseCount;
    }

    public int getReplyCount() {
        return replyCount;
    }



    public String getTitle() {
        return title;
    }







}
