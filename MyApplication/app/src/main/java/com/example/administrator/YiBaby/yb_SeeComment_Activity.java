package com.example.administrator.YiBaby;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableString;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.administrator.YiBaby.ybEntity.BBS_oplogsList;
import com.example.administrator.YiBaby.ybEntity.BBS_resultObject;
import com.example.administrator.YiBaby.ybEntity.H_CommentW;
import com.example.administrator.YiBaby.ybEntity.PublishTopic;
import com.example.administrator.YiBaby.ybEntity.resourceList;
import com.example.administrator.YiBaby.ybEntity.ybFace;
import com.example.administrator.YiBaby.ybHttpURL.ybHttpURL;
import com.example.administrator.YiBaby.yb_Util.ChatEmojiDbutil;
import com.example.administrator.YiBaby.yb_Util.DialogTool;
import com.example.administrator.YiBaby.yb_Util.DisplayUtil;
import com.example.administrator.YiBaby.yb_Util.FaceConversionUtil;
import com.example.administrator.YiBaby.ybadapter.MyAdapter_bbs;
import com.example.administrator.YiBaby.ybadapter.yb_ImageBaseAdapter;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class yb_SeeComment_Activity extends AppCompatActivity implements View.OnClickListener{
    private ImageView image08;
    private TextView comment2;
    private TextView data;
    private TextView name;
    private TextView comment;
    private RecyclerView baby;
    private LinearLayout moreComment;
    private BBS_resultObject bbs_resultObj;
    private BBS_resultObject bbs_resultObj_BB;
    private  Date date;
    private SimpleDateFormat sdf;
    private MyApplication app;
    private LinearLayoutManager mLayoutManager;
    private MyAdapter_bbs myAdapter_bbs;
    private TextView tvAddUser;
    private ImageView returns;
    private TextView commCount;
    private TextView zan;
    private PopupWindow mPopupWindow;
    private RelativeLayout WeixinFra;
    private RelativeLayout WeixniBest;
    private RelativeLayout QQFra;
    private RelativeLayout QQKong;
    private RelativeLayout SinaWeibo;
    private RelativeLayout Emil;
    private RelativeLayout rlComment;
    private Button btCancel;
    private SharedPreferences sp;
    private String userInfo;
    private Button rg1;
    private View ComView;
    private BBS_oplogsList endComment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yb__see_comment_);
        image08= (ImageView) super.findViewById(R.id.image08);
        comment2= (TextView) super.findViewById(R.id.comment2);
        data= (TextView) super.findViewById(R.id.data);
        name= (TextView) super.findViewById(R.id.name);
        comment= (TextView) super.findViewById(R.id.comment);
        baby= (RecyclerView) super.findViewById(R.id.baby);
        moreComment= (LinearLayout) super.findViewById(R.id.moreComment);
        returns= (ImageView) super.findViewById(R.id.returns);
        commCount= (TextView) super.findViewById(R.id.commCount);
        rlComment= (RelativeLayout) super.findViewById(R.id.rlComment);
        rg1= (Button) super.findViewById(R.id.rg1);
        zan= (TextView) super.findViewById(R.id.zan);
        app=MyApplication.getInstance();
        returns.setOnClickListener(this);
        rg1.setOnClickListener(this);
        date=new Date();
        sdf=new SimpleDateFormat();
        FaceConversionUtil.getInstace().getFileText(this);
        Intent intent=super.getIntent();
        bbs_resultObj = (BBS_resultObject) intent.getSerializableExtra("bbs_resultObj");
        sp=getSharedPreferences("myData", Context.MODE_PRIVATE);
        bbs_resultObj_BB=new BBS_resultObject();
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        userInfo=sp.getString("userInfo", "");
    }

    private void initView() {
        name.setText(bbs_resultObj.getAddUser());//名字
        comment2.setText(bbs_resultObj.getTitle());
        SpannableString spannableString =
                FaceConversionUtil.getInstace().getExpressionString(this, bbs_resultObj.getContent());
        comment.setText(spannableString);
        date.setTime(bbs_resultObj.getAddDate());
        data.setText(sdf.format(date));
        app.getImageLoader().get(ybHttpURL.HOST + bbs_resultObj.getHeadImg(),
                ImageLoader.getImageListener(image08, R.mipmap.default_face_man, R.mipmap.default_face_man));
        if(bbs_resultObj.getReplyCount()!=0){
            commCount.setText(bbs_resultObj.getReplyCount()+"");
        }else{
            commCount.setText("");
        }
        zan.setText(String.valueOf(bbs_resultObj.getPraiseCount()));
        List<resourceList> bbsResList= bbs_resultObj.getResourceList();
        if(bbsResList.size()==0){
            baby.setVisibility(View.GONE);
        }else{
            baby.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
//            myAdapter_bbs=new MyAdapter_bbs(this,bbsResList);
            myAdapter_bbs=new MyAdapter_bbs(this, (ArrayList<resourceList>) bbsResList);
            mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            baby.setLayoutManager(mLayoutManager);
            baby.setAdapter(myAdapter_bbs);
        }
        for(final BBS_oplogsList oplogs:bbs_resultObj.getOplogsList()){
            ComView= LayoutInflater.from(this).inflate(R.layout.comment_detail_layout2, null);
            tvAddUser = (TextView) ComView.findViewById(R.id.tvAddUser);
            String htmlContent="<font color='black'>"+oplogs.getAddUser()+"</font><font color='gray'>回复了</font><font color='black'>"+oplogs.getMemberName()
                    +":</font><font color='gray'>"+oplogs.getContent()+"</font>";
            CharSequence c= FaceConversionUtil.getInstace().getExpressionString(this,Html.fromHtml(htmlContent));
            tvAddUser.setText(c);
            ComView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!"".equals(userInfo)){
                        showCommentPopWindow(v,oplogs);
                        edit.setHint("回复"+oplogs.getAddUser()+":");  //石头回复了
                        edit.setTextSize(16);
                    }else{
                        DialogTool dialogTool=new DialogTool(yb_SeeComment_Activity.this);
                        dialogTool.getLoginDialogView(yb_SeeComment_Activity.this, v);
                    }
                }
            });
            moreComment.addView(ComView);
        }
        moreComment.setVisibility(View.VISIBLE);
        rlComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!"".equals(userInfo)){
                    showCommentPopWindow(v,null);
                }else{
                    DialogTool dialogTool=new DialogTool(yb_SeeComment_Activity.this);
                    dialogTool.getLoginDialogView(yb_SeeComment_Activity.this, v);
                }

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
        int width= DisplayUtil.getScreenWindth(this);
        int heigth= DisplayUtil.getScreenHeight(this)/3;
        mPopupWindow=new PopupWindow(view,width,heigth,true);
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
        mPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);//相对于整个屏幕位置
    }
    /**
     * 评论
     */
    private ImageView smallHeadImg;
    private Button btUnchoose;
    private Button btSend;
    private EditText edit;
    private PopupWindow cPopupWindow;
    public void showCommentPopWindow(View views,final BBS_oplogsList oplogs) {
        View view = LayoutInflater.from(this).inflate(R.layout.yb_comment_dialog, null);
        smallHeadImg = (ImageView) view.findViewById(R.id.smallHeadImg);
        edit = (EditText) view.findViewById(R.id.edit);//输入框
        btUnchoose = (Button) view.findViewById(R.id.btUnchoose);//取消
        btSend = (Button) view.findViewById(R.id.btSend);//发送
        int windth = DisplayUtil.getScreenWindth(this);
        int heigth = DisplayUtil.getScreenHeight(this) / 3;
        cPopupWindow = new PopupWindow(view, windth, heigth, true);//mPopupWindow.setFocusable(true);
        cPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;//0.0-1.0
        getWindow().setAttributes(lp);
        cPopupWindow.showAtLocation(views, Gravity.BOTTOM, 0, 0);
        cPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1;
                getWindow().setAttributes(lp);
            }
        });
        smallHeadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSmallHeadPopWindow();
            }
        });
        btUnchoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cPopupWindow.dismiss();
            }
        });
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = null;
                int comment_id = 0;
                int topic_id = 0;
                if (oplogs != null) {
                    comment_id = oplogs.getId();
                    topic_id=oplogs.getTopicId();
                } else {
                    comment_id = 0;
                    topic_id=bbs_resultObj.getId();
                }
                url = "http://www.meiyibaby.cn/appbackend/forum/insert.jsp?" +
                        "sid=25&obj.sid=25&obj.add_user_id=4961&add_user=%E7%9F%B3%E5%A4%B4&" +
                        "obj.member_id=0&obj.channel_id=0&obj.topic_id=" + topic_id + "&obj.comment_id=" + comment_id + "&" +
                        "obj.content=" + String.valueOf(edit.getText()) + "&obj.is_show=1&table_name=wcm_cms_topic_comment";
                MyStringRequest request = new MyStringRequest(url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        PublishTopic topic = gson.fromJson(response, PublishTopic.class);
                        if (topic.getResultCode().equals("0001") && topic.getResultMessage().equals("操作成功")) {
                            Toast.makeText(yb_SeeComment_Activity.this, "操作成功", Toast.LENGTH_SHORT).show();
                                getCommentNetData();
                            }
                            cPopupWindow.dismiss();
                        }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                app.getRequestQueue().add(request);
            }
        });
    }
    /**
     * 小头像的pop
     */
    private GridView vip;
    private yb_ImageBaseAdapter adapter;
    private List<ybFace> faceEntityList;
    private ybFace ybFace;
    private PopupWindow nPopupWindow;
    public void showSmallHeadPopWindow(){
        View view=LayoutInflater.from(this).inflate(R.layout.pop_qq__head_, null);
        vip= (GridView) view.findViewById(R.id.vip);
        initGridView();
        btSend= (Button) view.findViewById(R.id.btSend);
        int windth=DisplayUtil.getScreenWindth(this);
        int heigth=DisplayUtil.getScreenHeight(this)/2;
        nPopupWindow=new PopupWindow(view,windth,heigth,true);
        nPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        //1.弹出popupwindow，背景变暗
        WindowManager.LayoutParams lp=getWindow().getAttributes();
        lp.alpha=0.5f;//0.0-1.0
        getWindow().setAttributes(lp);
        nPopupWindow.showAtLocation(commCount, Gravity.BOTTOM, 0, 0);
    }
    private void initGridView() {
        faceEntityList=new ArrayList<ybFace>();
        faceEntityList.addAll(ChatEmojiDbutil.getChatEmojiList());
        adapter=new yb_ImageBaseAdapter(this,faceEntityList);
        vip.setAdapter(adapter);
        vip.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ybFace = faceEntityList.get(position);
                int index = edit.getSelectionStart();
                Editable editable = edit.getText();
                editable.insert(index, ybFace.getImgRes());
                edit.setText(FaceConversionUtil.getInstace().getExpressionString(yb_SeeComment_Activity.this, editable));
                nPopupWindow.dismiss();
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.returns:
                yb_SeeComment_Activity.this.finish();
                overridePendingTransition(R.anim.change_activity_in, R.anim.change_activity_out);
                break;
            case R.id.rg1:
                sendDianZan();
                break;
        }
    }
    private void sendDianZan(){
        String url=ybHttpURL.URL+"forum/insert.jsp?sid=25&obj.sid=25" +
                "&obj.add_user_id=4961&add_user=%E7%9F%B3%E5%A4%B4&obj.topic_id="+bbs_resultObj.getId()+"&obj.log_type=praise" +
                "&table_name=wcm_cms_topic_logs";
        MyStringRequest request=new MyStringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                PublishTopic topic=gson.fromJson(response, PublishTopic.class);
                if(topic.getResultCode().equals("0001")&&topic.getResultMessage().equals("操作成功")){
                    Toast.makeText(yb_SeeComment_Activity.this, "操作成功", Toast.LENGTH_SHORT).show();
//                    zan.setText(String.valueOf(Integer.valueOf(zan.getText().toString()) + 1));
                    zan.setText(String.valueOf(bbs_resultObj.getPraiseCount()+1));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        app.getRequestQueue().add(request);
    }
    private  void getCommentNetData(){
        String url = ybHttpURL.MORE_DETIL+"sid=25&id="+bbs_resultObj.getId();
        MyStringRequest request = new MyStringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                H_CommentW h_commentW = gson.fromJson(response, H_CommentW.class);
                String resultObject = h_commentW.getResultObject().replaceAll("\"memberId\":\" \"", "\"memberId\": 0").replaceAll("\"isShow\":\" \"", "\"isShow\":1").replaceAll("\"channelId\":\" \"", "\"channelId\":0").replaceAll("\"commentId\":\" \"", "\"commentId\":0").replaceAll("\"sid\":\" \"", "\"sid\":25");
                bbs_resultObj_BB = gson.fromJson(resultObject,BBS_resultObject.class);
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
                    endComment=bbs_resultObj_BB.getOplogsList().get(bbs_resultObj_BB.getOplogsList().size()-1);
                    ComView = LayoutInflater.from(yb_SeeComment_Activity.this).inflate(R.layout.comment_detail_layout2, null);
                    tvAddUser = (TextView) ComView.findViewById(R.id.tvAddUser);
                    String htmlContent = "<font color='black'>" + endComment.getAddUser() + "</font><font color='gray'>回复了</font><font color='black'>" + endComment.getMemberName()
                            + ":</font><font color='gray'>" + endComment.getContent() + "</font>";
                    CharSequence c = FaceConversionUtil.getInstace().getExpressionString(yb_SeeComment_Activity.this, Html.fromHtml(htmlContent));
                    tvAddUser.setText(c);
                    moreComment.addView(ComView);
                    if(bbs_resultObj_BB.getReplyCount()!=0){
                        commCount.setText(bbs_resultObj_BB.getReplyCount()+"");
                    }else{
                        commCount.setText("");
                    }
                    break;
            }
        }
    };
}
