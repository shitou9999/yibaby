package com.example.administrator.YiBaby;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.YiBaby.fragment.Comment_Broad_Fragment;
import com.example.administrator.YiBaby.ybEntity.BBS_resultObject;
import com.example.administrator.YiBaby.ybFragment.ybBBS_Fragment;
import com.example.administrator.YiBaby.ybFragment.ybBBS_Fragment_Near;
import com.example.administrator.YiBaby.ybHttpURL.ybHttpURL;
import com.example.administrator.YiBaby.yb_Util.DialogTool;

import java.util.ArrayList;

public class yb_BBS_FragmentManager extends AppCompatActivity
        implements Comment_Broad_Fragment.OnChangeFragment,View.OnClickListener{
    private FragmentManager fm;
    private Comment_Broad_Fragment commentFragment;
    private ImageView returns;
    private TextView topic;
    private ImageView iv_myTopic;
    private MyApplication app;
    private ArrayList<BBS_resultObject> mResultObjectList;
    private SharedPreferences sp;
    private String userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yi_baby__comment_bbs);
        fm=super.getSupportFragmentManager();
        iv_myTopic= (ImageView) super.findViewById(R.id.iv_myTopic);
        commentFragment=(Comment_Broad_Fragment)fm.findFragmentById(R.id.chooser);
        commentFragment.setChangeFragment(this);//自己写的接口的监听
        returns=(ImageView)super.findViewById(R.id.returns);
        topic= (TextView) super.findViewById(R.id.topic);
        mResultObjectList=new ArrayList<BBS_resultObject>();
        sp=getSharedPreferences("myData", Context.MODE_PRIVATE);
        iv_myTopic.setOnClickListener(this);
        app=MyApplication.getInstance();
        initTopicLisener();
        initView();
        initChangeActivityListener();
    }

    private ybBBS_Fragment ybBBS_fragment;
    private ybBBS_Fragment_Near ybBBS_fragment_near;
    private void initView(){
        ybBBS_fragment=ybBBS_fragment.newInstance(1);
        fm.beginTransaction().add(R.id.vip,ybBBS_fragment).commit();
    }

    @Override
    public void changeContentFragmentListener(int id) {
         switch (id){
             case 0:
                 ybBBS_fragment=ybBBS_fragment.newInstance(1);
                 fm.beginTransaction().replace(R.id.vip,ybBBS_fragment).commit();
                 break;
             case 1:
                 ybBBS_fragment_near=ybBBS_fragment_near.newInstance(ybHttpURL.NEAR_BBS);
                 fm.beginTransaction().replace(R.id.vip,ybBBS_fragment_near).commit();
                 break;
         }
    }

    @Override
    protected void onStart() {
        super.onStart();
        userInfo=sp.getString("userInfo", "");
    }

    /**
     * 发表主题
     */
    private void initTopicLisener() {
        topic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!"".equals(userInfo)){
                    Intent intent=new Intent(yb_BBS_FragmentManager.this,yb_NewTopic.class);
                    startActivity(intent);
                }else{
                    DialogTool dialogTool=new DialogTool(yb_BBS_FragmentManager.this);
                    dialogTool.getLoginDialogView(yb_BBS_FragmentManager.this, v);
                }
            }
        });
    }

    /**
     * 退出
     */
    private void initChangeActivityListener(){
        returns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yb_BBS_FragmentManager.this.finish();
                overridePendingTransition(R.anim.change_activity_in, R.anim.change_activity_out);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(!"".equals(userInfo)){
            showMyTopicPopWindow(v);
        }else{
            DialogTool dialogTool=new DialogTool(yb_BBS_FragmentManager.this);
            dialogTool.getLoginDialogView(yb_BBS_FragmentManager.this, v);
        }
    }
    private RelativeLayout rl_mytopic;
    private PopupWindow mPopupWindow;
    public void showMyTopicPopWindow(View viewComment){
        View view= LayoutInflater.from(this).inflate(R.layout.yb_mytopic_pop, null);
        rl_mytopic= (RelativeLayout) view.findViewById(R.id.rl_mytopic);
        int windth= getResources().getDisplayMetrics().widthPixels;
        int heigth=getResources().getDisplayMetrics().heightPixels/3;
        mPopupWindow=new PopupWindow(view,windth,heigth,true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        WindowManager.LayoutParams lp=getWindow().getAttributes();
        lp.alpha=0.5f;//0.0-1.0
        getWindow().setAttributes(lp);
        initPopWindowLisener();
        mPopupWindow.showAtLocation(iv_myTopic, Gravity.CENTER, 0, 0);
        //2.popupwindow消失之后，背景恢复
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1;
                getWindow().setAttributes(lp);
            }
        });
    }

    private void initPopWindowLisener() {
        rl_mytopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent=new Intent(yb_BBS_FragmentManager.this,yb_MyTopicActivity.class);
                    startActivity(intent);
                    mPopupWindow.dismiss();
            }
        });
    }
}
