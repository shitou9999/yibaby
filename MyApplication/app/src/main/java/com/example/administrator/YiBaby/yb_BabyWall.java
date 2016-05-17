package com.example.administrator.YiBaby;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * 宝宝墙
 */
public class yb_BabyWall extends AppCompatActivity {
    private ImageView returns;
    private ImageView showTheme;
    private RadioGroup rGoup;
    private ViewPager vip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yi_baby__baby_wall);
        returns=(ImageView)super.findViewById(R.id.returns);
        showTheme=(ImageView)super.findViewById(R.id.showTheme);
        rGoup=(RadioGroup)super.findViewById(R.id.rGoup);
        vip= (ViewPager) super.findViewById(R.id.vip);
        initViewPager();
        rGoup.check(R.id.bt1);
        initReturnsListener();
    }

    private void initViewPager() {
//        vip.setAdapter();
    }


    //关闭动画
    private void initReturnsListener(){
        returns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yb_BabyWall.this.finish();
                overridePendingTransition(R.anim.change_activity_in, R.anim.change_activity_out);
            }
        });
    }
    //弹出登录每怡的对话框
    public void initCustomDialogView(View view){
        final AlertDialog dialog=new AlertDialog.Builder(this).create();
        dialog.show();
        Window window=dialog.getWindow();
        window.setContentView(R.layout.custom_dialog_view);
        TextView text2= (TextView) window.findViewById(R.id.text2);
        TextView text3= (TextView) window.findViewById(R.id.text3);
        TextView text4= (TextView) window.findViewById(R.id.text4);
        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        //还没哟注册监听
    }
}
