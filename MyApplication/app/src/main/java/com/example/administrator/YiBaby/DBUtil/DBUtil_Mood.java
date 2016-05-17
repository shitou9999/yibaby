package com.example.administrator.YiBaby.DBUtil;

import com.example.administrator.YiBaby.Entity.Mood;
import com.example.administrator.YiBaby.Entity.Mood2;
import com.example.administrator.YiBaby.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/5.
 */
public class DBUtil_Mood {
    public static List<Mood> getMoodData01(){
        List<Mood> moodList=new ArrayList<Mood>();
        moodList.add(new Mood("奋斗",R.mipmap.reg_ch));
        moodList.add(new Mood("开心",R.drawable.mood_shape));
        moodList.add(new Mood("衰",R.mipmap.reg_kx));
        moodList.add(new Mood("无聊",R.mipmap.reg_ng));
        moodList.add(new Mood("擦汗",R.mipmap.reg_nu));
        moodList.add(new Mood("怒",R.mipmap.reg_shuai));
        moodList.add(new Mood("郁闷",R.mipmap.reg_wl));
        moodList.add(new Mood("慵懒",R.mipmap.reg_yl));
        moodList.add(new Mood("难过",R.mipmap.reg_ym));
        return moodList;
    }
    public static List<Mood2> getMood2Data(){
        List<Mood2> mood2List=new ArrayList<Mood2>();
        mood2List.add(new Mood2("奋斗","管你给不给加班费，当牛做马无所谓！",R.mipmap.reg_ch,1));
        mood2List.add(new Mood2("开心","艾玛，开森的一天！",R.mipmap.reg_fd,2));
        mood2List.add(new Mood2("衰","哎呀，今天真倒霉！到底是谁在惦记我！",R.mipmap.reg_kx,3));
        mood2List.add(new Mood2("无聊","生活的最高境界是人人早高峰，我自无聊中",R.mipmap.reg_ng,4));
        mood2List.add(new Mood2("擦汗","压力山大！我勒个去，太崩溃了！",R.mipmap.reg_nu,5));
        mood2List.add(new Mood2("怒","想被揍是不是？拳头知道不？想试试吗？",R.mipmap.reg_shuai,6));
        mood2List.add(new Mood2("郁闷","为什么受伤的总是我！难道我的泪花真是不值钱吗？",R.mipmap.reg_wl,7));
        mood2List.add(new Mood2("慵懒","任他忙里忙外千百遍，我自逍遥似神仙，嘿嘿！",R.mipmap.reg_yl,8));
        mood2List.add(new Mood2("难过","别管我！哭一下，哭完就好了。",R.mipmap.reg_ym,9));
        return mood2List;
    }
}
