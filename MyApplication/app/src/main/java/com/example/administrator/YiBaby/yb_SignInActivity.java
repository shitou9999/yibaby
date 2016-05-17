package com.example.administrator.YiBaby;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.YiBaby.ybEntity.EnergySigin;
import com.example.administrator.YiBaby.ybEntity.H_CommentW;
import com.example.administrator.YiBaby.ybEntity.User;
import com.example.administrator.YiBaby.ybHttpURL.ybHttpURL;
import com.example.administrator.YiBaby.ybadapter.yb_SigInBaseAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class yb_SignInActivity extends AppCompatActivity implements PullToRefreshBase.OnRefreshListener {
    private PullToRefreshListView vip;
    private yb_SigInBaseAdapter adapter;
    private ArrayList<EnergySigin> energySiginList;
    private ImageView returns;
    private String userInfo;
    private User user;
    private MyApplication app;
    private SharedPreferences sp;
    private ImageView ivHead;
    private TextView tvNum;
    private H_CommentW h_commentW;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yb__sign_in);
        vip= (PullToRefreshListView) super.findViewById(R.id.vip);
        ivHead= (ImageView) super.findViewById(R.id.ivHead);
        returns=(ImageView)super.findViewById(R.id.returns);
        tvNum=(TextView)super.findViewById(R.id.tvNum);
        energySiginList=new ArrayList<EnergySigin>();
        app=MyApplication.getInstance();
        sp=getSharedPreferences("myData", Context.MODE_PRIVATE);
        userInfo=sp.getString("userInfo", "");
        Gson gson = new Gson();
        user = gson.fromJson(userInfo, User.class);
        app.getImageLoader().get(ybHttpURL.HOST + user.getHead_img(),
                ImageLoader.getImageListener(ivHead, R.mipmap.default_face_man, R.mipmap.default_face_man));
        initView();
        initReturnsListener();
    }

    private void initView() {
        adapter=new yb_SigInBaseAdapter(this,energySiginList);
        vip.setAdapter(adapter);
        vip.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        vip.setOnRefreshListener(this);
        vip.setRefreshing();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onRefresh(PullToRefreshBase refreshView) {
        btToSeeRecord();
    }
    public void btToSeeRecord(){
        String url="http://www.meiyibaby.cn/appbackend/common/index.jsp";
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                h_commentW=gson.fromJson(response,H_CommentW.class);
                List<EnergySigin> getEnergySiginList=gson.fromJson(h_commentW.getResultObject(),new TypeToken<List<EnergySigin>>(){}.getType());
//                tvNum.setText(h_commentW.getTotalCount());
                energySiginList.clear();
                energySiginList.addAll(getEnergySiginList);
                handler.sendEmptyMessage(1);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params=new HashMap<String, String>();
                params.put("is_page", 1+"");
                params.put("start_page", 1+"");
                params.put("page_size", 20+"");
                params.put("table_name","wcm_cms_sign_in");
                params.put("condition", "member_id="+user.getId());
                params.put("sid", 25+"");
                return params;
            }
        };
        app.getRequestQueue().add(request);
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    adapter.notifyDataSetChanged();
                    vip.onRefreshComplete();
                    break;
            }
        }
    };

    private void initReturnsListener(){
        returns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yb_SignInActivity.this.finish();
                overridePendingTransition(R.anim.change_activity_in, R.anim.change_activity_out);
            }
        });
    }
}
