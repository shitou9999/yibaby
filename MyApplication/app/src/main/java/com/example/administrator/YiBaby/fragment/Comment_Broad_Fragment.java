package com.example.administrator.YiBaby.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.administrator.YiBaby.MyApplication;
import com.example.administrator.YiBaby.MyStringRequest;
import com.example.administrator.YiBaby.R;
import com.example.administrator.YiBaby.ybEntity.BBS_Comment;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2016/1/5.
 */
public class Comment_Broad_Fragment extends Fragment
        implements RadioGroup.OnCheckedChangeListener{
    private RadioGroup rGoup;
    private Button button01;
    private Button button02;
    private MyApplication app;
    private BBS_Comment bbs_comment1;
    private BBS_Comment bbs_comment2;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        app=MyApplication.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.comment_broad_fragment_layout2,null);
        rGoup=(RadioGroup)view.findViewById(R.id.rGoup);
        button01= (Button) view.findViewById(R.id.button01);
        button02= (Button) view.findViewById(R.id.button02);
        initTotoalNum1();
        initTotoalNum2();
        rGoup.check(R.id.button01);
        rGoup.setOnCheckedChangeListener(this);
        return view;
    }
    public interface OnChangeFragment{
        void changeContentFragmentListener(int id);
    }
    private OnChangeFragment mOnChangeFragment;
    public void setChangeFragment(OnChangeFragment onChangeFragment){
        this.mOnChangeFragment=onChangeFragment;
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.button01:
                mOnChangeFragment.changeContentFragmentListener(0);
                break;
            case R.id.button02:
                mOnChangeFragment.changeContentFragmentListener(1);
                break;
        }
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
             switch (msg.what){
                 case 0:
                     button01.setText("\t\t全部话题\n\t\t"+bbs_comment1.getTotalCount()+"/"+bbs_comment1.getScore());
                     break;
                 case 1:
                     button02.setText("\t附近话题\n\t\t"+bbs_comment2.getTotalCount()+"/"+bbs_comment2.getScore());
                     break;
             }
        }
    };
    private void initTotoalNum1(){
        bbs_comment1=new BBS_Comment();
        String url="http://www.meiyibaby.cn/appbackend/forum/list.jsp?" +
                "is_page=1&page_size=10&start_page=1&sid=25&channel_id=7&user_id=4961&condition=&flag=0";
        MyStringRequest request=new MyStringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                bbs_comment1=gson.fromJson(response,BBS_Comment.class);
                handler.sendEmptyMessage(0);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        app.getRequestQueue().add(request);
    }
    private void initTotoalNum2(){
        bbs_comment2=new BBS_Comment();
        String url="http://www.meiyibaby.cn/appbackend/forum/list_fj.jsp" +
                "is_page=1&page_size=0&start_page=0&sid=25&province_id=100110&condition=";
        MyStringRequest request=new MyStringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                bbs_comment2=gson.fromJson(response,BBS_Comment.class);
                handler.sendEmptyMessage(1);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        app.getRequestQueue().add(request);
    }
}
