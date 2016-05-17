package com.example.administrator.YiBaby;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.administrator.YiBaby.ybEntity.resourceList;
import com.example.administrator.YiBaby.ybEntity.H_resultObjectZ;
import com.example.administrator.YiBaby.ybEntity.ResultObject;
import com.example.administrator.YiBaby.ybEntity.H_CommentW;
import com.example.administrator.YiBaby.ybEntity.ybHead;
import com.example.administrator.YiBaby.ybHttpURL.ybHttpURL;
import com.example.administrator.YiBaby.ybadapter.yb_ImgPagerAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 刚开始布局写的Activity,
 */
public class YiBaby_Main_Net_NoNeed extends AppCompatActivity
        implements RadioGroup.OnCheckedChangeListener{
    private ViewPager pagerVip;
    private yb_ImgPagerAdapter adapter;
    private RadioGroup rRroup;
    private RelativeLayout rl01;
    private RelativeLayout rl02;
    private RelativeLayout rl03;
    private RelativeLayout rlGo;
    private RelativeLayout rlMum;
    private RelativeLayout rlIam;
    private TextView  seeComment1;
    private TextView  seeComment2;
    private TextView more;
    private RelativeLayout line3;
    private RequestPackage pac;
    private ImageView image06;
    private Handler handler;
    private TextView comment;
    private TextView comment1Date;
    private TextView talk;
    private ImageView image08;
    private ImageView baby;
    private TextView comment2;
    private TextView date;
    private MyApplication application;
    private List<ImageView> imgRes;
    private List<H_resultObjectZ> ybComment2List;
    private H_resultObjectZ ybComment2y;
    private H_resultObjectZ ybComment22;
    private resourceList ybComment31;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_noneed);
        pagerVip=(ViewPager)super.findViewById(R.id.pagerVip);
        rRroup=(RadioGroup)super.findViewById(R.id.rRroup);
        rl01=(RelativeLayout)super.findViewById(R.id.rl01);
        rl02=(RelativeLayout)super.findViewById(R.id.rl02);
        rl03=(RelativeLayout)super.findViewById(R.id.rl03);
        rlGo=(RelativeLayout)super.findViewById(R.id.rlGo);
        rlMum=(RelativeLayout)super.findViewById(R.id.rlMum);
        rlIam=(RelativeLayout)super.findViewById(R.id.rlIam);
        seeComment1=(TextView)super.findViewById(R.id.seeComment1);
        seeComment2=(TextView)super.findViewById(R.id.seeComment2);
        line3=(RelativeLayout)super.findViewById(R.id.line3);
        handler = new Handler();
        more=(TextView)super.findViewById(R.id.more);
        image06= (ImageView) super.findViewById(R.id.image06);
        comment= (TextView) super.findViewById(R.id.comment);
        comment1Date= (TextView) super.findViewById(R.id.comment1Date);
        talk= (TextView) super.findViewById(R.id.talk);
        image08= (ImageView) super.findViewById(R.id.image08);
        baby= (ImageView) super.findViewById(R.id.baby);
        comment2= (TextView) super.findViewById(R.id.comment2);
        date= (TextView) super.findViewById(R.id.date);
        pac=new RequestPackage((MyApplication) this.getApplication(),this);//////////////////////
        application= (MyApplication) super.getApplication();
        ybComment2List=new ArrayList<H_resultObjectZ>();
        initGetCommentData();
        initView();
        initViewListener();
    }
    /**
     * 评论的2条数据
     */
    private void initGetCommentData(){
        String url=ybHttpURL.LIST_DT;
        MyStringRequest request=new MyStringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                H_CommentW ybComments=gson.fromJson(response, H_CommentW.class);
                List<H_resultObjectZ> ybComment2List=gson.fromJson(ybComments.getResultObject(), new TypeToken<List<H_resultObjectZ>>() {}.getType());
                Date date=new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
                //评论1
                ybComment2y=ybComment2List.get(1);
                comment.setText(ybComment2y.getTitle());
                date.setTime(ybComment2y.getAddDate());
                comment1Date.setText(sdf.format(date));
                talk.setText(ybComment2y.getContent());
                application.getImageLoader().get(ybHttpURL.HOST+ ybComment2y.getHeadImg(),
                        ImageLoader.getImageListener(image06, R.mipmap.default_face_man, R.mipmap.default_face_man));
                //评论2
                ybComment22=ybComment2List.get(0);
                application.getImageLoader().get(ybHttpURL.HOST + ybComment22.getHeadImg(),     //头像
                        ImageLoader.getImageListener(image08, R.mipmap.default_face_man, R.mipmap.default_face_man));
//                if(ybComment22.getResourceList().size()!=0){
//                    List<resourceList> ybComment3s= gson.fromJson(ybComment22.getResourceList(), new TypeToken<List<resourceList>>(){}.getType());
//                    ybComment31=ybComment3s.get(0);
//                    application.getImageLoader().get(ybComment31.getImgSmall(),
//                            ImageLoader.getImageListener(baby,0,0));
//                }
                comment2.setText(ybComment22.getTitle());
                date.setTime(ybComment22.getAddDate());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        application.getRequestQueue().add(request);
    }
    /**
     * ViewPager数据
     */
    private void initView(){
        imgRes=new ArrayList<ImageView>();
//        adapter=new yb_ImgPagerAdapter(this,imgRes);
//        pagerVip.setAdapter(adapter);
        getNetHeadData();
        // 开启自动切换图片
        initRunnable();
        initListenen();//监听事件
    }
    private void getNetHeadData(){
        String url= ybHttpURL.BANNER;
        MyStringRequest request=new MyStringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                ybHead ybHeadGson1=gson.fromJson(response, ybHead.class);//外层
                List<ResultObject> resultObjects=gson.fromJson(ybHeadGson1.getResultObject(),
                        new TypeToken<List<ResultObject>>(){}.getType());
                ImageView imageView=null;
                for(ResultObject img:resultObjects){
                    imageView=new ImageView(YiBaby_Main_Net_NoNeed.this);
                    application.getImageLoader().get(ybHttpURL.HOST + img.getImg(),
                            ImageLoader.getImageListener(imageView, 0, 0));
                    imgRes.add(imageView);
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        application.getRequestQueue().add(request);
    }
    //****************************************************************************************
    /**
     * 添加事件监听
     */
    private void initListenen(){
        pagerVip.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            boolean isScrolled = false;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                switch (position){
                     case 0:
                         rRroup.check(R.id.spot1);
                         break;
                     case 1:
                         rRroup.check(R.id.spot2);
                         break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case 1:// 手势滑动
                        isScrolled = false;
                        break;
                    case 2:// 界面切换
                        isScrolled = true;
                        break;
                    case 0:// 滑动结束
                        // 当前为最后一张，此时从右向左滑，则切换到第一张
                        if (pagerVip.getCurrentItem() == pagerVip.getAdapter()
                                .getCount() - 1 && !isScrolled)
                        {
                            pagerVip.setCurrentItem(0);
                        }
                        // 当前为第一张，此时从左向右滑，则切换到最后一张
                        else if (pagerVip.getCurrentItem() == 0 && !isScrolled)
                        {
                            pagerVip.setCurrentItem(pagerVip.getAdapter()
                                    .getCount() - 1);
                        }
                        break;
                }
            }
        });
    }
    private Runnable viewpagerRunnable;
//    private static Handler handler;
    private static final int TIME = 2500;
    /**
     * 定时切换
     */
    protected void initRunnable() {
        viewpagerRunnable = new Runnable() {
            @Override
            public void run() {
                int nowIndex = pagerVip.getCurrentItem();
                int count = pagerVip.getAdapter().getCount();
                // 如果下一张的索引大于最后一张，则切换到第一张
                if (nowIndex + 1 >= count) {
                    pagerVip.setCurrentItem(0);
                } else {
                    pagerVip.setCurrentItem(nowIndex + 1);
                }
                handler.postDelayed(viewpagerRunnable, TIME);////////////////////////////////
            }
        };
        handler.postDelayed(viewpagerRunnable, TIME);////////////////////////////////
    }

    //****************************************************************************************
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.spot1:
                pagerVip.setCurrentItem(0);
                break;
            case R.id.spot2:
                pagerVip.setCurrentItem(1);
                break;
        }
    }
    //****************************************************************************************
    private void initViewListener(){
        line3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("tel:400-688-2175");
                Intent intent =new Intent();
                intent.setAction("android.intent.action.DIAL");
                intent.setData(uri);
                startActivity(intent);
            }
        });
        rl01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(YiBaby_Main_Net_NoNeed.this,yb_Mood.class);
                startActivity(intent);
            }
        });
        rlGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(YiBaby_Main_Net_NoNeed.this,yb_Go_Milk_Fg_Ms.class);
                startActivity(intent);
            }
        });
        rlMum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YiBaby_Main_Net_NoNeed.this, yb_Mum_Circle.class);
                startActivity(intent);
            }
        });
        rlIam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YiBaby_Main_Net_NoNeed.this, yb_Login_Activity.class);
                startActivity(intent);
            }
        });
        seeComment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(YiBaby_Main_Net_NoNeed.this,yb_Comment1_Detil_NoNeed.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("H_resultObjectZ",ybComment2y);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        seeComment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YiBaby_Main_Net_NoNeed.this, yb_Comment2_Detil_NoNeed.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("H_resultObjectZ",ybComment22);
                bundle.putSerializable("ybComment31",ybComment31);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YiBaby_Main_Net_NoNeed.this, yb_BBS_FragmentManager.class);
                startActivity(intent);
            }
        });
    }

}
