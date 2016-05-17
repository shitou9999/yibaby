package com.example.administrator.YiBaby.ybSQLite;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 判断网络是否连接
 * Created by Administrator on 2016/3/1.
 */
public class CheckNet {
    public static boolean checkNetWorkConnectionState(Context context){
        ConnectivityManager connMgr=(ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connMgr.getActiveNetworkInfo();
        if(networkInfo!=null&&networkInfo.isConnected()){
            return true;
        }else {
            return false;
        }
    }
}
