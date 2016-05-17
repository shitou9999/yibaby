package com.example.administrator.YiBaby;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.administrator.YiBaby.ybEntity.BBS_Comment;
import com.example.administrator.YiBaby.ybEntity.BBS_resultObject;
import com.example.administrator.YiBaby.ybEntity.H_CommentW;
import com.example.administrator.YiBaby.ybEntity.H_resultObjectZ;
import com.example.administrator.YiBaby.ybEntity.ResultObject;
import com.example.administrator.YiBaby.ybEntity.resourceList;
import com.example.administrator.YiBaby.ybEntity.ybHead;
import com.example.administrator.YiBaby.ybHttpURL.ybHttpURL;
import com.example.administrator.YiBaby.yb_Util.DialogTool;
import com.example.administrator.YiBaby.yb_Util.FaceConversionUtil;
import com.example.administrator.YiBaby.yb_Util.TimeUtil;
import com.example.administrator.YiBaby.ybadapter.MyAdapter2;
import com.example.administrator.YiBaby.ybadapter.yb_ImgPagerAdapter;
import com.example.administrator.YiBaby.zxing.BarCodeTestActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//RadioGroup.OnCheckedChangeListener,
public class yb_Main_Net_Test3 extends AppCompatActivity
        implements RequestPackage.callBackMainCommentNetLisener{
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
    private List<ResultObject> objectList;
    private List<Bitmap> bitmapList=new ArrayList<Bitmap>();
    private boolean isrunning =true;
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
    private SharedPreferences sp;
    private String userInfo;
    private TimeUtil timeUtil;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);
        initFindViewById();
        pac=new RequestPackage((MyApplication) this.getApplication(),this);
        app=MyApplication.getInstance();
        imgRes=new ArrayList<ImageView>();
        ybComment2List=new ArrayList<H_resultObjectZ>();
        objectList=new ArrayList<ResultObject>();
        FaceConversionUtil.getInstace().getFileText(this);
        sp=getSharedPreferences("myData", Context.MODE_PRIVATE);
        date=new Date();
//        sdf = new SimpleDateFormat("MM月dd日");
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        timeUtil=new TimeUtil();
        pac.setCallBackMainCommentNet(this);
        initViewListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initCheckNet();
        isrunning=true;
        userInfo=sp.getString("userInfo","");
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
        comment1Date.setText(timeUtil.formatDisplayTime(sdf.format(date), "yyyy-MM-dd HH:mm:ss"));//日期
//        comment1Date.setText(sdf.format(date));//日期
        SpannableString spannableString =
                FaceConversionUtil.getInstace().getExpressionString(this, ybComment2y.getContent());
        talk.setText(spannableString);//评论内容
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
//        date2.setText(sdf.format(date));
        date2.setText(timeUtil.formatDisplayTime(sdf.format(date), "yyyy-MM-dd HH:mm:ss"));
        SpannableString spannableStrings =
                FaceConversionUtil.getInstace().getExpressionString(this, ybComment22.getContent());
        content2.setText(spannableStrings);
    }
    /**
     * ViewPager数据
     */
    private void initViewPagerNetData(){
        adapter=new yb_ImgPagerAdapter(this,bitmapList);
        pagerVip.setAdapter(adapter);
        getNetHeadData();
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
                objectList.addAll(resultObjects);
                ImageView imageView;
                final List<Bitmap> bitmapList=new ArrayList<Bitmap>();
                for(int i=0;i<objectList.size();i++){
                    imageView=new ImageView(yb_Main_Net_Test3.this);
                    String imgUrl=ybHttpURL.HOST+objectList.get(i).getImg();
                    app.getImageLoader().get(imgUrl, new ImageLoader.ImageListener() {
                        @Override
                        public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                            Bitmap bitmap=response.getBitmap();
                            if(bitmap!=null){
                                bitmapList.add(bitmap);
                                if(bitmapList.size()==2){
                                    Message message=handler.obtainMessage();
                                    message.obj=bitmapList;
                                    message.what=2;
                                    handler.sendMessage(message);
                                }
                            }
                        }
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(yb_Main_Net_Test3.this, "网络不好", Toast.LENGTH_LONG).show();
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
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position % 2) {
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

            }
        });
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    BBS_resultObject bbs_resultObj1= (BBS_resultObject) msg.obj;
                    Intent intent1 = new Intent(yb_Main_Net_Test3.this, yb_SeeComment_Activity.class);
                    intent1.putExtra("bbs_resultObj", bbs_resultObj1);
                    startActivity(intent1);
                    break;
                case 1:
                    BBS_resultObject bbs_resultObj2= (BBS_resultObject) msg.obj;
                    Intent intent2 = new Intent(yb_Main_Net_Test3.this, yb_SeeComment_Activity.class);
                    intent2.putExtra("bbs_resultObj", bbs_resultObj2);
                    startActivity(intent2);
                    break;
                case 2:
                    List<Bitmap> blist=(List<Bitmap>)msg.obj;
                    bitmapList.addAll(blist);
                    adapter.notifyDataSetChanged();
                    handler.sendEmptyMessageDelayed(3, 5000);
                    break;
                case 3:
                    //让ViewPager滑到下一页
                    pagerVip.setCurrentItem(pagerVip.getCurrentItem()+1);
                    //延时，循环调用handler
                    if(isrunning){
                        handler.sendEmptyMessageDelayed(3, 5000);
                    }
                    break;
            }
    }
    };
    private void initViewListener(){
        zxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(yb_Main_Net_Test3.this, BarCodeTestActivity.class);
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
                if(!userInfo.equals("")){
                    Intent intent=new Intent(yb_Main_Net_Test3.this,yb_Mood.class);
                    startActivity(intent);
                }else{
                    DialogTool dialogTool=new DialogTool(yb_Main_Net_Test3.this);
                    dialogTool.getLoginDialogView(yb_Main_Net_Test3.this,v);
                }
            }
        });
        rlGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(yb_Main_Net_Test3.this,yb_Go_Milk_Fg_Ms.class);
                startActivity(intent);
            }
        });
        rlMum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(yb_Main_Net_Test3.this, yb_Mum_Circle.class);
                startActivity(intent);
            }
        });
        rlIam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(yb_Main_Net_Test3.this, yb_Login_Activity.class);
                startActivity(intent);
            }
        });

        seeComment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ybComment2y!=null){
                    String url = "http://www.meiyibaby.cn/appbackend/forum/detail.jsp?sid=25&id="+ybComment2y.getId();
                    MyStringRequest request = new MyStringRequest(url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Gson gson = new Gson();
                            H_CommentW h_commentW = gson.fromJson(response, H_CommentW.class);
                            String resultObject = h_commentW.getResultObject().replaceAll("\"memberId\":\" \"", "\"memberId\": 0").replaceAll("\"isShow\":\" \"", "\"isShow\":1").replaceAll("\"channelId\":\" \"", "\"channelId\":0").replaceAll("\"commentId\":\" \"", "\"commentId\":0").replaceAll("\"sid\":\" \"", "\"sid\":25");
                            BBS_resultObject bbs_resultObj1 = gson.fromJson(resultObject,BBS_resultObject.class);
                            Message message=new Message();
                            message.obj=bbs_resultObj1;
                            message.what=0;
                            handler.sendMessage(message);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    app.getRequestQueue().add(request);
                }
            }
        });
        seeComment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ybComment22 != null) {
                    String url = "http://www.meiyibaby.cn/appbackend/forum/detail.jsp?sid=25&id=" + ybComment22.getId();
                    MyStringRequest request = new MyStringRequest(url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Gson gson = new Gson();
                            BBS_Comment h_commentW = gson.fromJson(response, BBS_Comment.class);
                            String resultObject = h_commentW.getResultObject().replaceAll("\"memberId\":\" \"", "\"memberId\": 0").replaceAll("\"isShow\":\" \"", "\"isShow\":1").replaceAll("\"channelId\":\" \"", "\"channelId\":0").replaceAll("\"commentId\":\" \"", "\"commentId\":0").replaceAll("\"sid\":\" \"", "\"sid\":25");
                            BBS_resultObject bbs_resultObj2 = gson.fromJson(resultObject, BBS_resultObject.class);
                            Message message = new Message();
                            message.obj = bbs_resultObj2;
                            message.what = 1;
                            handler.sendMessage(message);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    app.getRequestQueue().add(request);
                }
            }
        });
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(yb_Main_Net_Test3.this, yb_BBS_FragmentManager.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        isrunning=false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isrunning=false;
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
