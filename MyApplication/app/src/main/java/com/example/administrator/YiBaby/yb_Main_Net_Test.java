package com.example.administrator.YiBaby;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.administrator.YiBaby.ybEntity.H_resultObjectZ;
import com.example.administrator.YiBaby.ybEntity.ResultObject;
import com.example.administrator.YiBaby.ybEntity.resourceList;
import com.example.administrator.YiBaby.ybEntity.ybHead;
import com.example.administrator.YiBaby.ybHttpURL.ybHttpURL;
import com.example.administrator.YiBaby.ybadapter.MyAdapter2;
import com.example.administrator.YiBaby.ybadapter.yb_ImgPagerAdapter;
import com.example.administrator.YiBaby.zxing.BarCodeTestActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class yb_Main_Net_Test extends AppCompatActivity
        implements RadioGroup.OnCheckedChangeListener,RequestPackage.callBackMainCommentNetLisener{
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
    private ImageView zxing;
    private TextView comment;
    private TextView comment1Date;
    private TextView talk;
    private ImageView image08;
    private RecyclerView baby;
    private RecyclerView baby1;
    private TextView comment2;
    private TextView date2;
    private MyApplication app;
    private List<ImageView> imgRes;
    private List<H_resultObjectZ> ybComment2List;
    private H_resultObjectZ ybComment2y;
    private H_resultObjectZ ybComment22;
    private resourceList ybComment31;
    private RelativeLayout reView01;
    private RelativeLayout reView02;
    private TextView content2;
    private Date date;
    private SimpleDateFormat sdf;
    private List<resourceList> ybComment3s;
    private List<resourceList> ybComment3ss;
    private MyAdapter2 myAdapter2;
    private LinearLayoutManager mLayoutManager;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);
        initFindViewById();
        pac=new RequestPackage((MyApplication) this.getApplication(),this);
        app=MyApplication.getInstance();

        handler = new Handler();
        imgRes=new ArrayList<ImageView>();
        ybComment2List=new ArrayList<H_resultObjectZ>();
//        ybComment3s=new ArrayList<resourceList>();
//        ybComment3ss=new ArrayList<resourceList>();
        date=new Date();
        sdf = new SimpleDateFormat("MM月dd日");
        pac.setCallBackMainCommentNet(this);
        initViewListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initCheckNet();
    }

    private void initCheckNet(){
        if(app.checkNetWorkConnectionState(this)){
            initViewPagerNetData();
            reView01.setVisibility(View.VISIBLE);
            reView02.setVisibility(View.VISIBLE);
            pac.getMainCommentByGet();
        }else{
            reView01.setVisibility(View.GONE);
            reView02.setVisibility(View.GONE);
        }
    }
    /**
     * 评论的2条数据(回调)
     */
    @Override
    public void callBackMainCommentNet(List list) {
        ybComment2List.addAll(list);
        ybComment2y=ybComment2List.get(1);
        comment.setText(ybComment2y.getTitle());//名字
        date.setTime(ybComment2y.getAddDate());
        comment1Date.setText(sdf.format(date));//日期
        talk.setText(ybComment2y.getContent());//评论内容
        app.getImageLoader().get(ybHttpURL.HOST + ybComment2y.getHeadImg(),
                ImageLoader.getImageListener(image06, R.mipmap.default_face_man, R.mipmap.default_face_man));
        ybComment3s= ybComment2y.getResourceList();
        if(ybComment3s.size()==0){
            baby1.setVisibility(View.GONE);
        }else{
            baby1.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            myAdapter2=new MyAdapter2(this,ybComment3s);
            mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            baby1.setLayoutManager(mLayoutManager);
            baby1.setAdapter(myAdapter2);
        }
        //评论2
        ybComment22=ybComment2List.get(0);
        ybComment3ss= ybComment22.getResourceList();
        app.getImageLoader().get(ybHttpURL.HOST + ybComment22.getHeadImg(), ImageLoader.getImageListener(image08, R.mipmap.default_face_man, R.mipmap.default_face_man));
        if(ybComment3ss.size()==0){
            baby.setVisibility(View.GONE);
        }else{
            baby.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            myAdapter2=new MyAdapter2(this,ybComment3ss);
            mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            baby.setLayoutManager(mLayoutManager);
            baby.setAdapter(myAdapter2);
        }
        comment2.setText(ybComment22.getTitle());
        date.setTime(ybComment22.getAddDate());
        date2.setText(sdf.format(date));
        content2.setText(ybComment22.getContent());
    }
    /**
     * ViewPager数据
     */
    private void initViewPagerNetData(){
//        adapter=new yb_ImgPagerAdapter(this,imgRes);
//        pagerVip.setAdapter(adapter);
        getNetHeadData();
        // 开启自动切换图片
        initRunnable();
        initViewPagerListenen();//监听事件
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
                    imageView=new ImageView(yb_Main_Net_Test.this);
                    app.getImageLoader().get(ybHttpURL.HOST + img.getImg(),
                            ImageLoader.getImageListener(imageView, R.mipmap.default_img_middle, R.mipmap.default_img_middle));
                    imgRes.add(imageView);
                }
                adapter.notifyDataSetChanged();//应该发个消息
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        app.getRequestQueue().add(request);
    }
    //****************************************************************************************
    /**
     * 添加事件监听
     */
    private void initViewPagerListenen(){
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
    private static Handler handler;
    private static final int TIME = 3000;
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
        zxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(yb_Main_Net_Test.this, BarCodeTestActivity.class);
                startActivity(intent);
            }
        });
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
                Intent intent=new Intent(yb_Main_Net_Test.this,yb_Mood.class);
                startActivity(intent);
            }
        });
        rlGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(yb_Main_Net_Test.this,yb_Go_Milk_Fg_Ms.class);
                startActivity(intent);
            }
        });
        rlMum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(yb_Main_Net_Test.this, yb_Mum_Circle.class);
                startActivity(intent);
            }
        });
        rlIam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(yb_Main_Net_Test.this, yb_Login_Activity.class);
                startActivity(intent);
            }
        });
        seeComment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(yb_Main_Net_Test.this,yb_Comment_Detil.class);
                intent.putExtra("H_resultObjectZ",ybComment2y);
//                Bundle bundle=new Bundle();
//                bundle.putSerializable("H_resultObjectZ",ybComment2y);
//                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        seeComment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(yb_Main_Net_Test.this, yb_Comment_Detil.class);
                intent.putExtra("H_resultObjectZ",ybComment22);
                startActivity(intent);
            }
        });
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(yb_Main_Net_Test.this, yb_BBS_FragmentManager.class);
                startActivity(intent);
            }
        });
    }
    private void initFindViewById(){
        pagerVip=(ViewPager)super.findViewById(R.id.pagerVip);
        rRroup=(RadioGroup)super.findViewById(R.id.rRroup);
        rl01=(RelativeLayout)super.findViewById(R.id.rl01);
        rl02=(RelativeLayout)super.findViewById(R.id.rl02);
        rl03=(RelativeLayout)super.findViewById(R.id.rl03);
        rlGo=(RelativeLayout)super.findViewById(R.id.rlGo);
        rlMum=(RelativeLayout)super.findViewById(R.id.rlMum);
        rlIam=(RelativeLayout)super.findViewById(R.id.rlIam);
        reView01=(RelativeLayout)super.findViewById(R.id.reView01);
        reView02=(RelativeLayout)super.findViewById(R.id.reView02);
        seeComment1=(TextView)super.findViewById(R.id.seeComment1);
        seeComment2=(TextView)super.findViewById(R.id.seeComment2);
        line3=(RelativeLayout)super.findViewById(R.id.line3);
        more=(TextView)super.findViewById(R.id.more);
        image06= (ImageView) super.findViewById(R.id.image06);
        comment= (TextView) super.findViewById(R.id.comment);
        comment1Date= (TextView) super.findViewById(R.id.comment1Date);
        talk= (TextView) super.findViewById(R.id.talk);
        image08= (ImageView) super.findViewById(R.id.image08);
        baby1= (RecyclerView) super.findViewById(R.id.baby1);
        baby= (RecyclerView) super.findViewById(R.id.baby);
        comment2= (TextView) super.findViewById(R.id.comment2);
        date2= (TextView) super.findViewById(R.id.date2);
        content2= (TextView) super.findViewById(R.id.content2);
        zxing= (ImageView) super.findViewById(R.id.zxing);
    }
}
