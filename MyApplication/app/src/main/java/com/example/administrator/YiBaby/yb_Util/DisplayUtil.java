package com.example.administrator.YiBaby.yb_Util;

import android.content.Context;

/**
 * Created by Administrator on 2016/1/30.
 */
public class DisplayUtil {
    public static int getScreenWindth(Context context){
        return context.getResources().getDisplayMetrics().widthPixels;
    }
    public static int getScreenHeight(Context context){
        return context.getResources().getDisplayMetrics().heightPixels;
    }

}
