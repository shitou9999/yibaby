package com.example.administrator.YiBaby;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.YiBaby.adapter.yb_BBS_CommentBaseAdapter_Net;
import com.example.administrator.YiBaby.ybEntity.BBS_resultObject;
import com.example.administrator.YiBaby.ybEntity.H_CommentW;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class yb_MyTopicActivity extends AppCompatActivity implements PullToRefreshBase.OnRefreshListener,View.OnClickListener{
    private PullToRefreshListView vip;
    private ArrayList<BBS_resultObject> mResultObjectList;
    private yb_BBS_CommentBaseAdapter_Net adapter;
    private ImageView returns;
    private MyApplication app;
    private TextView topic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yb__my_topic);
        vip= (PullToRefreshListView) super.findViewById(R.id.vip);
        returns= (ImageView) super.findViewById(R.id.returns);
        topic= (TextView) super.findViewById(R.id.topic);
        returns.setOnClickListener(this);
        topic.setOnClickListener(this);
        mResultObjectList=new ArrayList<BBS_resultObject>();
        app=MyApplication.getInstance();
        initListViewData();
    }

    private void initListViewData() {
        mResultObjectList=new ArrayList<BBS_resultObject>();
        adapter=new yb_BBS_CommentBaseAdapter_Net(this,this,mResultObjectList);
        vip.setAdapter(adapter);
        vip.setOnRefreshListener(this);
        vip.setRefreshing();
    }

    /**
     * 刷新数据
     */
    public void notifyData(String obj){
        vip.setRefreshing();
    }

    @Override
    public void onRefresh(PullToRefreshBase refreshView) {
        send_requst_get_myTopic();
    }

    private void send_requst_get_myTopic(){
        String url="http://www.meiyibaby.cn/appbackend/forum/list.jsp?" +
                "is_page=1&page_size=20&start_page=1&sid=25&flag=0&user_id=4961&condition=add_user_id%3D4961";
        final StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                H_CommentW h_commentW=gson.fromJson(response,H_CommentW.class);
                String resultObject = h_commentW.getResultObject().replaceAll("\"addUserId\":\" \"", "\"addUserId\": 0").replaceAll("\"memberId\":\" \"", "\"memberId\": 0").replaceAll("\"isShow\":\" \"", "\"isShow\":1").replaceAll("\"channelId\":\" \"", "\"channelId\":0").replaceAll("\"commentId\":\" \"", "\"commentId\":0").replaceAll("\"sid\":\" \"", "\"sid\":25");
                List<BBS_resultObject> resultObjectList=gson.fromJson(resultObject, new TypeToken<List<BBS_resultObject>>(){}.getType());
                mResultObjectList.clear();
                mResultObjectList.addAll(resultObjectList);
                handler.sendEmptyMessage(1);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        app.getRequestQueue().add(request);
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    vip.onRefreshComplete();
                    break;
            }
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.topic:
                topic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(yb_MyTopicActivity.this,yb_NewTopic.class);
                        startActivity(intent);
                    }
                });
                break;
            case R.id.returns:
                returns.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        yb_MyTopicActivity.this.finish();
                        overridePendingTransition(R.anim.change_activity_in, R.anim.change_activity_out);
                    }
                });
                break;
        }

    }
}
