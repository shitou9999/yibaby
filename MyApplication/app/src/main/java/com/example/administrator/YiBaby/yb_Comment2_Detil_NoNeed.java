package com.example.administrator.YiBaby;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.administrator.YiBaby.ybEntity.resourceList;
import com.example.administrator.YiBaby.ybEntity.H_resultObjectZ;
import com.example.administrator.YiBaby.ybHttpURL.ybHttpURL;
import com.example.administrator.YiBaby.yb_Util.DisplayUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class yb_Comment2_Detil_NoNeed extends AppCompatActivity {
    private ImageView returns;
    private ImageView image08;
    private ImageView baby;
    private TextView comment2;
    private TextView data;
    private TextView name;
    private MyApplication app;
    private H_resultObjectZ ybComments;
    private resourceList[] ybComment31;
    private RelativeLayout rlZan;
    private TextView zan;
    private RadioButton rg1;
    private PopupWindow mPopupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yi_baby__comment2__detil);
        returns=(ImageView)super.findViewById(R.id.returns);
        image08=(ImageView)super.findViewById(R.id.image08);
        baby=(ImageView)super.findViewById(R.id.baby);
        comment2= (TextView) super.findViewById(R.id.comment2);
        data= (TextView) super.findViewById(R.id.data);
        name= (TextView) super.findViewById(R.id.name);
        zan= (TextView) super.findViewById(R.id.zan);
        rlZan= (RelativeLayout) super.findViewById(R.id.rlZan);
        rg1= (RadioButton) super.findViewById(R.id.rg1);
        app= MyApplication.getInstance();
        initViewListener();
        initAcceptSendData();
    }

    private void initAcceptSendData() {
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        ybComments=(H_resultObjectZ)bundle.getSerializable("H_resultObjectZ");
        ybComment31= (resourceList[]) bundle.getSerializable("ybComment31");
        ybComment31= (resourceList[]) intent.getSerializableExtra("ybComment31");
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("MM月dd日");
        date.setTime(ybComments.getAddDate());
        comment2.setText(ybComments.getTitle());
        data.setText(sdf.format(date));
        name.setText(ybComments.getAddUser());
        app.getImageLoader().get(ybHttpURL.HOST + ybComments.getHeadImg(),
                ImageLoader.getImageListener(image08, R.mipmap.default_face_man, R.mipmap.default_face_man));
        app.getImageLoader().get(ybHttpURL.HOST+ ybComment31[0].getImgSmall(),
                ImageLoader.getImageListener(baby, 0, 0));
    }
    private void initViewListener(){
        rg1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                /////////////////////////////点赞
            }
        });
        returns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yb_Comment2_Detil_NoNeed.this.finish();
                overridePendingTransition(R.anim.change_activity_in, R.anim.change_activity_out);
            }
        });
    }

    private void initSharePopWindow(){
        View view= LayoutInflater.from(this).inflate(R.layout.yibaby_share_gradview_layout, null);
        mPopupWindow=new PopupWindow(view);
        //是否让Popupwindow可以点击,必须在PopupWindow设置了背景的情况
        mPopupWindow.setFocusable(true);//点击外侧关bi

        ColorDrawable colorDrawable=new ColorDrawable(0);
        mPopupWindow.setBackgroundDrawable(colorDrawable);
        mPopupWindow.setAnimationStyle(R.style.popWindowShareShow);
    }
    public void showSharePopWindow(View view){
        initSharePopWindow();
        int width= DisplayUtil.getScreenWindth(this);
        int heigth= DisplayUtil.getScreenHeight(this)/3;
        mPopupWindow.setWidth(width);
        mPopupWindow.setHeight(heigth);
        mPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);//相对于整个屏幕位置
    }

    private void initConmmentPopWindow(){
        View view=LayoutInflater.from(this).inflate(R.layout.yb_comment_dialog, null);
        mPopupWindow=new PopupWindow(view);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        //1.弹出popupwindow，背景变暗
        WindowManager.LayoutParams lp=getWindow().getAttributes();
        lp.alpha=0.5f;//0.0-1.0
        getWindow().setAttributes(lp);
    }
    public void showCommentPopWindow(View view){
        initConmmentPopWindow();
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
}
