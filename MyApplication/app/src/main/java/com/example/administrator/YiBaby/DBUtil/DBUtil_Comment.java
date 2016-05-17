package com.example.administrator.YiBaby.DBUtil;

import com.example.administrator.YiBaby.Entity.Comment;
import com.example.administrator.YiBaby.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/4.
 */
public class DBUtil_Comment {
    public static List<Comment> getCommentData(){
        List<Comment> commentList=new ArrayList<Comment>();
//        (String title, String date, String name, String sbTitle, int styleType, int[] imgRes
//        String title, String date, String name, int[] imgRes, int styleType
        commentList.add(new Comment("宝宝","11月25日","刘晓玲",new int[]{R.mipmap.default_face_man,R.mipmap.home_baby_bg},"2",0));
        commentList.add(new Comment("在哪里看积分啊","11月25日","谢飞","刚下载的不知道怎么玩？",new int[]{R.mipmap.default_face_man},"7",1));
        return commentList;
    }









}
