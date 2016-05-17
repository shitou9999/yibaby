package com.example.administrator.YiBaby.ybEntity;
import java.io.Serializable;
import java.util.List;
/**
 * Created by Administrator on 2016/1/27.
 */
public class H_resultObjectZ implements Serializable {
    private long addDate;
    private String addUser;
    private String content;
    private String headImg;
    private int id;
    private int praiseCount;
    private int replyCount;
    private List<resourceList> resourceList;
    private String title;

    public List<resourceList> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<resourceList> resourceList) {
        this.resourceList = resourceList;
    }

    public long getAddDate() {
        return addDate;
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



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(int praiseCount) {
        this.praiseCount = praiseCount;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }




    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
