package com.example.administrator.YiBaby;

import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;

import com.example.administrator.YiBaby.ybEntity.User;
import com.google.gson.Gson;

public class JavaScriptCallTest {
    private AppCompatActivity mActitvty;
    private MyApplication app;
    public JavaScriptCallTest(AppCompatActivity mActitvty) {
        this.mActitvty=mActitvty;
        app=MyApplication.getInstance();
    }
    @JavascriptInterface
    private void clearCookie(){
        app.setUser(null);
    }
    @JavascriptInterface
    private void getUserInfo(String getUserInfo){
        if(getUserInfo!=null){
            Gson gson=new Gson();
            User user=gson.fromJson(getUserInfo,User.class);
            app.setUser(user);
        }
    }

//    private void initDialogView(){
//        AlertDialog.Builder builder=new AlertDialog.Builder(context);
//        builder.setTitle("温馨提示").setMessage("您现在还没有登录，现在就去登录？");
//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        AlertDialog dialog=builder.create();
//        dialog.show();
//    }
}