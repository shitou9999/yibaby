package com.example.administrator.YiBaby;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.YiBaby.DBUtil.DBUtil_Mood;
import com.example.administrator.YiBaby.Entity.Mood;
import com.example.administrator.YiBaby.Entity.Mood2;
import com.example.administrator.YiBaby.adapter.YiBaby_MoodBaseAdapter;
import com.example.administrator.YiBaby.ybEntity.User;
import com.example.administrator.YiBaby.ybEntity.ybSinIn;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class yb_Mood extends AppCompatActivity {
    private HeaderGridView vip;
    private YiBaby_MoodBaseAdapter adapter;
    private List<Mood> moodList;
    private ImageView image;
    private TextView title;
    private TextView sbtitle;
    private List<Mood2> mood2List;
    private ImageView returns;
    private EditText etContent;
    private SharedPreferences sp;
    private String userInfo;
    private User user;
    private int pos;
    private MyApplication app;
    private Mood2 mood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yi_baby__mood);
        vip=(HeaderGridView)super.findViewById(R.id.vip);
        returns=(ImageView)super.findViewById(R.id.returns);
        sp=getSharedPreferences("myData", Context.MODE_PRIVATE);
        app=MyApplication.getInstance();
        initView();
        initMoodclickListener();
        initReturnsListener();
    }
    private void initView(){
        moodList=new ArrayList<Mood>();
        moodList.addAll(DBUtil_Mood.getMoodData01());
        adapter=new YiBaby_MoodBaseAdapter(this,moodList);
        View view= LayoutInflater.from(this).inflate(R.layout.activity_mood__detil_head,null);
        etContent= (EditText) view.findViewById(R.id.etContent);
        title=(TextView)view.findViewById(R.id.title);
        sbtitle=(TextView)view.findViewById(R.id.sbtitle);
        image=(ImageView)view.findViewById(R.id.image);
        vip.addHeaderView(view);
        vip.setAdapter(adapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        userInfo=sp.getString("userInfo","");
        Gson gson = new Gson();
        user = gson.fromJson(userInfo, User.class);
    }
    private void initMoodclickListener(){
        vip.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                mood2List = new ArrayList<Mood2>();
                mood2List.addAll(DBUtil_Mood.getMood2Data());
                mood = mood2List.get(position - 3);
                title.setText(mood.getTitle());
                sbtitle.setText(mood.getSbtitle());
                image.setImageResource(mood.getImgRes());
            }
        });
    }
    public void clickSignIntoNet(View view){
        if(etContent!=null){
            String url="http://www.meiyibaby.cn/appbackend/signIn/index.jsp";
            StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Gson gson=new Gson();
                    ybSinIn ybSinInEntity=gson.fromJson(response, ybSinIn.class);
                    if("0002".equals(ybSinInEntity.getResultCode())&&"今日已签到".equals(ybSinInEntity.getResultMessage())){
                        Toast.makeText(yb_Mood.this, "今日已签到", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(yb_Mood.this, "签到成功", Toast.LENGTH_SHORT).show();
                        yb_Mood.this.finish();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<String,String>();
                    params.put("sid", String.valueOf(25));
                    params.put("user_id", String.valueOf(user.getId()));
                    params.put("content", String.valueOf(etContent.getText()));
                    params.put("type", String.valueOf(mood.getType()));
                    return params;
                }
            };
            app.getRequestQueue().add(request);
        }
    }
    public void btToSeeRecord(View view){
        Intent intent=new Intent(yb_Mood.this,yb_SignInActivity.class);
        startActivity(intent);
    }

    private void initReturnsListener(){
        returns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yb_Mood.this.finish();
                overridePendingTransition(R.anim.change_activity_in, R.anim.change_activity_out);
            }
        });
    }
}
