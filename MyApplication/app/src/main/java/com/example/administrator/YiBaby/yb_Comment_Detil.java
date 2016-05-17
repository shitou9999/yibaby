package com.example.administrator.YiBaby;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.administrator.YiBaby.ybEntity.H_resultObjectZ;
import com.example.administrator.YiBaby.ybEntity.resourceList;
import com.example.administrator.YiBaby.ybHttpURL.ybHttpURL;
import com.example.administrator.YiBaby.yb_Util.DisplayUtil;
import com.example.administrator.YiBaby.ybadapter.MyAdapter2;
import com.example.administrator.YiBaby.ybadapter.yb_ImageBaseAdapter_NoNeed;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class yb_Comment_Detil extends AppCompatActivity {
    private ImageView returns;
    private CircleImageView image08;
    private RecyclerView baby;
    private TextView comment2;
    private TextView comment;
    private TextView data;
    private TextView name;
    private MyApplication app;
    private H_resultObjectZ ybComments;
    private RelativeLayout rlZan;
    private TextView zan;
    private RadioButton rg1;
    private PopupWindow mPopupWindow;
    private RelativeLayout WeixinFra;
    private RelativeLayout WeixniBest;
    private RelativeLayout QQFra;
    private RelativeLayout QQKong;
    private RelativeLayout SinaWeibo;
    private RelativeLayout Emil;
    private Button btCancel;
    private LinearLayoutManager mLayoutManager;
    private MyAdapter2 myAdapter2;
    private List<resourceList> ybComment3s;
    private SharedPreferences sp;
    private String getSpZan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yi_baby__comment__detil);
        initFindViewById();
        app= MyApplication.getInstance();
        sp=getSharedPreferences("myopot", Context.MODE_PRIVATE);
        getSpZan=sp.getString("Zan", "赞");
        zan.setText(getSpZan);
        initViewListener();
        initAcceptSendData();
    }

    private void initFindViewById() {
        returns=(ImageView)super.findViewById(R.id.returns);
        image08=(CircleImageView)super.findViewById(R.id.image08);
        baby=(RecyclerView)super.findViewById(R.id.baby);
        comment2= (TextView) super.findViewById(R.id.comment2);
        data= (TextView) super.findViewById(R.id.data);
        name= (TextView) super.findViewById(R.id.name);
        zan= (TextView) super.findViewById(R.id.zan);
        comment= (TextView) super.findViewById(R.id.comment);
        rlZan= (RelativeLayout) super.findViewById(R.id.rlZan);
        rg1= (RadioButton) super.findViewById(R.id.rg1);
    }

    private void initAcceptSendData() {
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        ybComments=(H_resultObjectZ)bundle.getSerializable("H_resultObjectZ");
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("MM月dd日");
        date.setTime(ybComments.getAddDate());
        comment2.setText(ybComments.getTitle());
        data.setText(sdf.format(date));
        name.setText(ybComments.getAddUser());
        comment.setText(ybComments.getContent());
        app.getImageLoader().get(ybHttpURL.HOST + ybComments.getHeadImg(),
                ImageLoader.getImageListener(image08, R.mipmap.default_face_man, R.mipmap.default_face_man));
        ybComment3s= ybComments.getResourceList();
        if(ybComment3s.size()==0){
            baby.setVisibility(View.GONE);
        }else{
            baby.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            myAdapter2=new MyAdapter2(this,ybComment3s);
            mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            baby.setLayoutManager(mLayoutManager);
            baby.setAdapter(myAdapter2);
        }
    }
    private int count=0;
    private void initViewListener(){
        rg1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                /////////////////////////////点赞
                count++;
                zan.setText(count+"");
                sp.edit().putString("Zan", count+"").commit();
            }
        });
        returns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yb_Comment_Detil.this.finish();
                overridePendingTransition(R.anim.change_activity_in, R.anim.change_activity_out);
            }
        });
    }

    /**
     * 社交分享
     */
    private void initSharePopWindow(){
        View view= LayoutInflater.from(this).inflate(R.layout.yibaby_share_gradview_layout, null);
        WeixinFra= (RelativeLayout) view.findViewById(R.id.WeixinFra);
        WeixniBest= (RelativeLayout) view.findViewById(R.id.WeixniBest);
        QQFra= (RelativeLayout) view.findViewById(R.id.QQFra);
        QQKong= (RelativeLayout) view.findViewById(R.id.QQKong);
        SinaWeibo= (RelativeLayout) view.findViewById(R.id.SinaWeibo);
        Emil= (RelativeLayout) view.findViewById(R.id.Emil);
        btCancel= (Button) view.findViewById(R.id.btCancel);
        mPopupWindow=new PopupWindow(view);
        //是否让Popupwindow可以点击,必须在PopupWindow设置了背景的情况
        mPopupWindow.setFocusable(true);//点击外侧关bi
        ColorDrawable colorDrawable=new ColorDrawable(0);
        mPopupWindow.setBackgroundDrawable(colorDrawable);
        mPopupWindow.setAnimationStyle(R.style.popWindowShareShow);
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
    }
    public void showSharePopWindow(View view){
        initSharePopWindow();
        int width= DisplayUtil.getScreenWindth(this);
        int heigth= DisplayUtil.getScreenHeight(this)/3;
        mPopupWindow.setWidth(width);
        mPopupWindow.setHeight(heigth);
        mPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);//相对于整个屏幕位置
    }

    /**
     * 评论
     */
    private ImageView smallHeadImg;
    private Button btUnchoose;
    private Button btSend;
    private EditText edit;
    public void showCommentPopWindow(View view){
        initConmmentPopWindow();
        initPopWindowLisener();
        int windth=DisplayUtil.getScreenWindth(this);
        int heigth=DisplayUtil.getScreenHeight(this)/3;
        mPopupWindow.setWidth(windth);
        mPopupWindow.setHeight(heigth);
        mPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 50);
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
    private void initConmmentPopWindow(){
        View view=LayoutInflater.from(this).inflate(R.layout.yb_comment_dialog, null);
//        smallHeadImg= (ImageView) view.findViewById(R.id.smallHeadImg);
        edit= (EditText) view.findViewById(R.id.edit);
        btUnchoose= (Button) view.findViewById(R.id.btUnchoose);
        btSend= (Button) view.findViewById(R.id.btSend);
        mPopupWindow=new PopupWindow(view);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        //1.弹出popupwindow，背景变暗
        WindowManager.LayoutParams lp=getWindow().getAttributes();
        lp.alpha=0.5f;//0.0-1.0
        getWindow().setAttributes(lp);
    }


    /**
     * 小头像的pop
     */
    private GridView vip;
    private yb_ImageBaseAdapter_NoNeed adapter;
    private Integer[] integers;
    private void initSmallHeadPopWindow(){
        View view=LayoutInflater.from(this).inflate(R.layout.pop_qq__head_, null);
        vip= (GridView) super.findViewById(R.id.vip);
        initGridView();
        btSend= (Button) view.findViewById(R.id.btSend);
        mPopupWindow=new PopupWindow(view);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        //1.弹出popupwindow，背景变暗
        WindowManager.LayoutParams lp=getWindow().getAttributes();
        lp.alpha=0.5f;//0.0-1.0
        getWindow().setAttributes(lp);
    }

    public void showSmallHeadPopWindow(View view){
        initSmallHeadPopWindow();
        initPopWindowLisener();
        int windth=DisplayUtil.getScreenWindth(this);
        int heigth=DisplayUtil.getScreenHeight(this)/3;
        mPopupWindow.setWidth(windth);
        mPopupWindow.setHeight(heigth);
        mPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
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
    private void initGridView() {
        integers= new Integer[]{
                R.mipmap.h001, R.mipmap.h002,R.mipmap.h003, R.mipmap.h004, R.mipmap.h005, R.mipmap.h006,R.mipmap.h007, R.mipmap.h008,
                R.mipmap.h009, R.mipmap.h010,R.mipmap.h011, R.mipmap.h012, R.mipmap.h013, R.mipmap.h014,R.mipmap.h015, R.mipmap.h016,
                R.mipmap.h017, R.mipmap.h018,R.mipmap.h019, R.mipmap.h020, R.mipmap.h021, R.mipmap.h022,R.mipmap.h023, R.mipmap.h024,
                R.mipmap.h025, R.mipmap.h026,R.mipmap.h027, R.mipmap.h028, R.mipmap.h029, R.mipmap.h030,R.mipmap.h031, R.mipmap.h032,
                R.mipmap.h031, R.mipmap.h034,R.mipmap.h035, R.mipmap.h036, R.mipmap.h037, R.mipmap.h038,R.mipmap.h039, R.mipmap.h040,
                R.mipmap.h041, R.mipmap.h042,R.mipmap.h043, R.mipmap.h044, R.mipmap.h045, R.mipmap.h046,R.mipmap.h047, R.mipmap.h048,
                R.mipmap.h049, R.mipmap.h050,R.mipmap.h051, R.mipmap.h052, R.mipmap.h053, R.mipmap.h054,R.mipmap.h055, R.mipmap.h056,
                R.mipmap.h057, R.mipmap.h058,R.mipmap.h059, R.mipmap.h060, R.mipmap.h061, R.mipmap.h062,R.mipmap.h063, R.mipmap.h064,
                R.mipmap.h065, R.mipmap.h066,R.mipmap.h067, R.mipmap.h068, R.mipmap.h069, R.mipmap.h070,R.mipmap.h071, R.mipmap.h072,
                R.mipmap.h073, R.mipmap.h074,R.mipmap.h075, R.mipmap.h076, R.mipmap.h077, R.mipmap.h078,R.mipmap.h079, R.mipmap.h080,
                R.mipmap.h081, R.mipmap.h082,R.mipmap.h083, R.mipmap.h084, R.mipmap.h085, R.mipmap.h086,R.mipmap.h087, R.mipmap.h088,
                R.mipmap.h089, R.mipmap.h090
        };
        adapter=new yb_ImageBaseAdapter_NoNeed(this,integers);
        vip.setAdapter(adapter);
    }
    /**
     * PopWindow监听
     */
    private void initPopWindowLisener() {
//        smallHeadImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(yb_Comment_Detil.this,yb_QQ_Head_Activity.class);
//                startActivityForResult(intent,0);
//            }
//        });
        btUnchoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送评论/////////////////////////////////////////////////
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0&&resultCode==1){
//            edit.setText(data.getIntArrayExtra("head"));
        }
    }
}
