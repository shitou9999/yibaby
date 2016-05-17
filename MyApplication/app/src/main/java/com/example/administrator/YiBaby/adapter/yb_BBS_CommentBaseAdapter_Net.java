package com.example.administrator.YiBaby.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableString;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.administrator.YiBaby.MyApplication;
import com.example.administrator.YiBaby.MyStringRequest;
import com.example.administrator.YiBaby.R;
import com.example.administrator.YiBaby.ybEntity.BBS_oplogsList;
import com.example.administrator.YiBaby.ybEntity.BBS_resultObject;
import com.example.administrator.YiBaby.ybEntity.H_CommentW;
import com.example.administrator.YiBaby.ybEntity.PublishTopic;
import com.example.administrator.YiBaby.ybEntity.resourceList;
import com.example.administrator.YiBaby.ybEntity.ybFace;
import com.example.administrator.YiBaby.ybHttpURL.ybHttpURL;
import com.example.administrator.YiBaby.yb_SeeComment_Activity;
import com.example.administrator.YiBaby.yb_Util.ChatEmojiDbutil;
import com.example.administrator.YiBaby.yb_Util.DialogTool;
import com.example.administrator.YiBaby.yb_Util.DisplayUtil;
import com.example.administrator.YiBaby.yb_Util.FaceConversionUtil;
import com.example.administrator.YiBaby.ybadapter.yb_ImageBaseAdapter;
import com.google.gson.Gson;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/1/4.
 */
public class yb_BBS_CommentBaseAdapter_Net extends BaseAdapter {
    private Object object;
    private Context context;
    private List<BBS_resultObject> commentList;
    private MyApplication app;
    private Date date;
    private SimpleDateFormat sdf;
    private TextView tvAddUser;
    private BBS_resultObject bbs_resultObj;
    private BBS_oplogsList oplogss;
    private View viewDetil;
    private SharedPreferences sp;
    private String userInfo;
    private StringBuffer sb;
    public yb_BBS_CommentBaseAdapter_Net() {
    }
    public yb_BBS_CommentBaseAdapter_Net(Context context,Object object, List<BBS_resultObject> commentList) {
        this.context = context;
        this.object=object;
        this.commentList = commentList;
        app=MyApplication.getInstance();
        date=new Date();
        sdf = new SimpleDateFormat("MM月dd日");
        FaceConversionUtil.getInstace().getFileText(context);
        sp=context.getSharedPreferences("myData", Context.MODE_PRIVATE);
        sb=new StringBuffer();
    }

    @Override
    public int getCount() {
        return commentList.size();
    }

    @Override
    public Object getItem(int position) {
        return commentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder{
        public TextView tvTitle;//标题
        public TextView tvDate;//时间
        public TextView commentDtil;//评论内容
        public ImageView imgHead;//头像
        public ImageView imgRes1;
        public ImageView imgRes2;
        public ImageView imgRes3;
        public ImageView imgRes4;
        public ImageView imgRes5;
        public TextView tvName;//名字
        public TextView commCount;//评论数
        private TextView zan;
        private TextView seeComment; //查看更多评论
        private LinearLayout moreComment;
        private RelativeLayout rlShare;
        private RelativeLayout rlComment;
        private RelativeLayout rlZan;
        private RelativeLayout commentDetil;
        private RadioButton rg1;
    }
    ViewHolder holder=null;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      final BBS_resultObject commentEntity=commentList.get(position);
        if(convertView==null){
            holder=new ViewHolder();
                    convertView= LayoutInflater.from(context).inflate(R.layout.bbs_comment_layout, null);
                    holder.tvTitle=(TextView)convertView.findViewById(R.id.comment2);//标题
                    holder.tvDate=(TextView)convertView.findViewById(R.id.data);//时间
                    holder.tvName=(TextView)convertView.findViewById(R.id.name);
                    holder.commentDtil=(TextView)convertView.findViewById(R.id.comment);
                    holder.imgHead=(ImageView)convertView.findViewById(R.id.image08);//头像
                    holder.imgRes1=(ImageView)convertView.findViewById(R.id.baby1);
                    holder.imgRes2=(ImageView)convertView.findViewById(R.id.baby2);
                    holder.imgRes3=(ImageView)convertView.findViewById(R.id.baby3);
                    holder.imgRes4=(ImageView)convertView.findViewById(R.id.baby4);
                    holder.imgRes5=(ImageView)convertView.findViewById(R.id.baby5);
                    holder.commCount=(TextView)convertView.findViewById(R.id.commCount);//评论数
                    holder.zan= (TextView) convertView.findViewById(R.id.zan);
                    holder.moreComment= (LinearLayout) convertView.findViewById(R.id.moreComment);
                    holder.seeComment= (TextView) convertView.findViewById(R.id.seeComment);
                    holder.rg1= (RadioButton) convertView.findViewById(R.id.rg1);
            holder.rlShare= (RelativeLayout) convertView.findViewById(R.id.rlShare);
            holder.rlComment= (RelativeLayout) convertView.findViewById(R.id.rlComment);
            holder.rlZan= (RelativeLayout) convertView.findViewById(R.id.rlZan);
            holder.commentDetil= (RelativeLayout) convertView.findViewById(R.id.commentDetil);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        holder.tvTitle.setText(commentEntity.getTitle());
        date.setTime(commentEntity.getAddDate());
        holder.tvDate.setText(sdf.format(date));
        holder.tvName.setText(commentEntity.getAddUser());
        SpannableString spannableString =
                FaceConversionUtil.getInstace().getExpressionString(context, commentEntity.getContent());
        holder.commentDtil.setText(spannableString);
        if(commentEntity.getReplyCount()!=0){
            holder.commCount.setText(commentEntity.getReplyCount()+"");
        }else {
            holder.commCount.setText("");
        }
        holder.zan.setText(String.valueOf(commentEntity.getPraiseCount()));//赞
        app.getImageLoader().get(ybHttpURL.HOST + commentEntity.getHeadImg(),
                ImageLoader.getImageListener(holder.imgHead, R.mipmap.default_face_man, R.mipmap.default_face_man));
        List<resourceList> bbsResourceListList=commentEntity.getResourceList();
        if(commentEntity.getReplyCount()<3){
            holder.seeComment.setVisibility(View.GONE);
        }else{
            holder.seeComment.setVisibility(View.VISIBLE);
            holder.seeComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = ybHttpURL.MORE_DETIL+"sid=25&id="+commentEntity.getId(); ///////////////////////////////////////////////
                    MyStringRequest request = new MyStringRequest(url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Gson gson = new Gson();
                            H_CommentW h_commentW = gson.fromJson(response, H_CommentW.class);
                            Log.i("jereh",h_commentW.getResultObject());
                            String resultObject = h_commentW.getResultObject().replaceAll("\"memberId\":\" \"", "\"memberId\": 0").replaceAll("\"isShow\":\" \"", "\"isShow\":1").replaceAll("\"channelId\":\" \"", "\"channelId\":0").replaceAll("\"commentId\":\" \"", "\"commentId\":0").replaceAll("\"sid\":\" \"", "\"sid\":25");
                            bbs_resultObj = gson.fromJson(resultObject,BBS_resultObject.class);
                            Message message=new Message();
                            message.obj=bbs_resultObj;
                            message.what=1;
                            handler.sendMessage(message);
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

        if(commentEntity.getOplogsList().size()==0){   //如果评论为0隐藏查看更多评论
            holder.seeComment.setVisibility(View.GONE);
            holder.moreComment.setVisibility(View.GONE);
        }else{
            holder.moreComment.removeAllViewsInLayout(); ////////////////////////////
              for(final BBS_oplogsList oplogs:commentEntity.getOplogsList()){
                  oplogss=oplogs;
                  viewDetil=LayoutInflater.from(context).inflate(R.layout.comment_detail_layout, null);
                  tvAddUser = (TextView) viewDetil.findViewById(R.id.tvAddUser);
                  String htmlContent="<font color='black'>"+oplogs.getAddUser()+"</font><font color='gray'>回复了</font><font color='black'>"+oplogs.getMemberName()
                          +":</font><font color='gray'>"+oplogs.getContent()+"</font>";
                  CharSequence c= FaceConversionUtil.getInstace().getExpressionString(context,Html.fromHtml(htmlContent));
                  tvAddUser.setText(c);
                  //评论区域回复
                  viewDetil.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          userInfo=sp.getString("userInfo", "");
                          if(!"".equals(userInfo)){
                              showCommentPopWindow(v,oplogs,commentEntity);
                              edit.setHint("回复"+oplogs.getAddUser()+":");  //石头回复了
                              edit.setTextSize(16);
                          }else{
                              DialogTool dialogTool=new DialogTool(context);
                              dialogTool.getLoginDialogView(context, v);
                          }
                      }
                  });
                  holder.moreComment.addView(viewDetil);
              }
            holder.moreComment.setVisibility(View.VISIBLE);

        }
        /**
         * 控制图片的显示与隐藏
         */
        switch (bbsResourceListList.size()){
            case 0:
                holder.imgRes1.setVisibility(View.GONE);
                holder.imgRes2.setVisibility(View.GONE);
                holder.imgRes3.setVisibility(View.GONE);
                holder.imgRes4.setVisibility(View.GONE);
                holder.imgRes5.setVisibility(View.GONE);
                break;
            case 1:
                holder.imgRes1.setVisibility(View.VISIBLE);
                app.getImageLoader().get(commentEntity.getResourceList().get(0).getImgSmall(),
                        ImageLoader.getImageListener(holder.imgRes1, R.mipmap.default_img_middle, R.mipmap.default_img_middle));
                holder.imgRes2.setVisibility(View.GONE);
                holder.imgRes3.setVisibility(View.GONE);
                holder.imgRes4.setVisibility(View.GONE);
                holder.imgRes5.setVisibility(View.GONE);
                break;
            case 2:
                holder.imgRes1.setVisibility(View.VISIBLE);
                holder.imgRes2.setVisibility(View.VISIBLE);
                app.getImageLoader().get(commentEntity.getResourceList().get(0).getImgSmall(),
                        ImageLoader.getImageListener(holder.imgRes1, R.mipmap.default_img_middle, R.mipmap.default_img_middle));
                app.getImageLoader().get(commentEntity.getResourceList().get(1).getImgSmall(),
                        ImageLoader.getImageListener(holder.imgRes2, R.mipmap.default_img_middle, R.mipmap.default_img_middle));
                holder.imgRes3.setVisibility(View.GONE);
                holder.imgRes4.setVisibility(View.GONE);
                holder.imgRes5.setVisibility(View.GONE);
                break;
            case 3:
                holder.imgRes1.setVisibility(View.VISIBLE);
                holder.imgRes2.setVisibility(View.VISIBLE);
                holder.imgRes3.setVisibility(View.VISIBLE);
                app.getImageLoader().get(commentEntity.getResourceList().get(0).getImgSmall(),
                        ImageLoader.getImageListener(holder.imgRes1, R.mipmap.default_img_middle, R.mipmap.default_img_middle));
                app.getImageLoader().get(commentEntity.getResourceList().get(1).getImgSmall(),
                        ImageLoader.getImageListener(holder.imgRes2, R.mipmap.default_img_middle, R.mipmap.default_img_middle));
                app.getImageLoader().get(commentEntity.getResourceList().get(2).getImgSmall(),
                        ImageLoader.getImageListener(holder.imgRes3, R.mipmap.default_img_middle, R.mipmap.default_img_middle));
                holder.imgRes4.setVisibility(View.GONE);
                holder.imgRes5.setVisibility(View.GONE);
                break;
            case 4:
                holder.imgRes1.setVisibility(View.VISIBLE);
                holder.imgRes2.setVisibility(View.VISIBLE);
                holder.imgRes3.setVisibility(View.VISIBLE);
                holder.imgRes4.setVisibility(View.VISIBLE);
                app.getImageLoader().get(commentEntity.getResourceList().get(0).getImgSmall(),
                        ImageLoader.getImageListener(holder.imgRes1, R.mipmap.default_img_middle, R.mipmap.default_img_middle));
                app.getImageLoader().get(commentEntity.getResourceList().get(1).getImgSmall(),
                        ImageLoader.getImageListener(holder.imgRes2, R.mipmap.default_img_middle, R.mipmap.default_img_middle));
                app.getImageLoader().get(commentEntity.getResourceList().get(2).getImgSmall(),
                        ImageLoader.getImageListener(holder.imgRes3, R.mipmap.default_img_middle, R.mipmap.default_img_middle));
                app.getImageLoader().get(commentEntity.getResourceList().get(3).getImgSmall(),
                        ImageLoader.getImageListener(holder.imgRes4, R.mipmap.default_img_middle, R.mipmap.default_img_middle));
                holder.imgRes5.setVisibility(View.GONE);
                break;
            case 5:
                holder.imgRes1.setVisibility(View.VISIBLE);
                holder.imgRes2.setVisibility(View.VISIBLE);
                holder.imgRes3.setVisibility(View.VISIBLE);
                holder.imgRes4.setVisibility(View.VISIBLE);
                holder.imgRes5.setVisibility(View.VISIBLE);
                app.getImageLoader().get(commentEntity.getResourceList().get(0).getImgSmall(),
                        ImageLoader.getImageListener(holder.imgRes1, R.mipmap.default_img_middle, R.mipmap.default_img_middle));
                app.getImageLoader().get(commentEntity.getResourceList().get(1).getImgSmall(),
                        ImageLoader.getImageListener(holder.imgRes2, R.mipmap.default_img_middle, R.mipmap.default_img_middle));
                app.getImageLoader().get(commentEntity.getResourceList().get(2).getImgSmall(),
                        ImageLoader.getImageListener(holder.imgRes3, R.mipmap.default_img_middle, R.mipmap.default_img_middle));
                app.getImageLoader().get(commentEntity.getResourceList().get(3).getImgSmall(),
                        ImageLoader.getImageListener(holder.imgRes4, R.mipmap.default_img_middle, R.mipmap.default_img_middle));
                app.getImageLoader().get(commentEntity.getResourceList().get(4).getImgSmall(),
                        ImageLoader.getImageListener(holder.imgRes5, R.mipmap.default_img_middle, R.mipmap.default_img_middle));
                break;
        }
        //分享
        holder.rlShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSharePopWindow(v);
            }
        });
        //评论
        holder.rlComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInfo=sp.getString("userInfo", "");
                if(!"".equals(userInfo)){
                    showCommentPopWindow(v,null,commentEntity);
                }else{
                    DialogTool dialogTool=new DialogTool(context);
                    dialogTool.getLoginDialogView(context, v);
                }
            }
        });
        //点赞
        holder.rg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                holder.zan.setText((commentEntity.getPraiseCount()+1)+"");
//                holder.zan.setText(String.valueOf(Integer.valueOf(holder.zan.getText().toString()) + 1));
                userInfo=sp.getString("userInfo", "");
                if(!"".equals(userInfo)){
                    String url=ybHttpURL.URL+"forum/insert.jsp?sid=25&obj.sid=25" +
                            "&obj.add_user_id=4961&add_user=%E7%9F%B3%E5%A4%B4&obj.topic_id="+commentEntity.getId()+"&obj.log_type=praise" +
                            "&table_name=wcm_cms_topic_logs";
                    MyStringRequest request=new MyStringRequest(url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Gson gson=new Gson();
                            PublishTopic topic=gson.fromJson(response, PublishTopic.class);
                            if(topic.getResultCode().equals("0001")&&topic.getResultMessage().equals("操作成功")){
                                Toast.makeText(context, "操作成功", Toast.LENGTH_SHORT).show();
                                notifyData();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    app.getRequestQueue().add(request);
                }else{
                    DialogTool dialogTool=new DialogTool(context);
                    dialogTool.getLoginDialogView(context, v);
                }
            }
        });
        holder.commentDetil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = ybHttpURL.MORE_DETIL+"sid=25&id="+commentEntity.getId();
                MyStringRequest request = new MyStringRequest(url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        H_CommentW h_commentW = gson.fromJson(response, H_CommentW.class);
                        String resultObject = h_commentW.getResultObject().replaceAll("\"memberId\":\" \"", "\"memberId\": 0").replaceAll("\"isShow\":\" \"", "\"isShow\":1").replaceAll("\"channelId\":\" \"", "\"channelId\":0").replaceAll("\"commentId\":\" \"", "\"commentId\":0").replaceAll("\"sid\":\" \"", "\"sid\":25");
                        bbs_resultObj = gson.fromJson(resultObject,BBS_resultObject.class);
                        Message message=new Message();
                        message.obj=bbs_resultObj;
                        message.what=1;
                        handler.sendMessage(message);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                app.getRequestQueue().add(request);
            }
        });
        return convertView;
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    BBS_resultObject bbs_resultObjSend= (BBS_resultObject) msg.obj;
                    Intent intent = new Intent(context, yb_SeeComment_Activity.class);
                    intent.putExtra("bbs_resultObj", bbs_resultObjSend);
                    context.startActivity(intent);
                    break;
            }
        }
    };

    /**
     * 社交分享
     */
    private PopupWindow mPopupWindow;
    private RelativeLayout WeixinFra;
    private RelativeLayout WeixniBest;
    private RelativeLayout QQFra;
    private RelativeLayout QQKong;
    private RelativeLayout SinaWeibo;
    private RelativeLayout Emil;
    private Button btCancel;
    private int id;
    private void initSharePopWindow(View views){
        View view= LayoutInflater.from(context).inflate(R.layout.yibaby_share_gradview_layout, null);
        WeixinFra= (RelativeLayout) view.findViewById(R.id.WeixinFra);
        WeixniBest= (RelativeLayout) view.findViewById(R.id.WeixniBest);
        QQFra= (RelativeLayout) view.findViewById(R.id.QQFra);
        QQKong= (RelativeLayout) view.findViewById(R.id.QQKong);
        SinaWeibo= (RelativeLayout) view.findViewById(R.id.SinaWeibo);
        Emil= (RelativeLayout) view.findViewById(R.id.Emil);
        btCancel= (Button) view.findViewById(R.id.btCancel);
        int width= DisplayUtil.getScreenWindth(context);
        int heigth= DisplayUtil.getScreenHeight(context)/3;
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
        mPopupWindow.showAtLocation(views, Gravity.BOTTOM, 0, 0);//相对于整个屏幕位置
    }

    /**
     * 评论
     */
    private ImageView smallHeadImg;
    private Button btUnchoose;
    private Button btSend;
    private EditText edit;
    private PopupWindow sPopupWindow;
    public void showCommentPopWindow(View viewComment,final BBS_oplogsList oplogs, final BBS_resultObject commentEntitys){
        View view=LayoutInflater.from(context).inflate(R.layout.yb_comment_dialog, null);
        smallHeadImg= (ImageView) view.findViewById(R.id.smallHeadImg);
        edit= (EditText) view.findViewById(R.id.edit);//输入框
        btUnchoose= (Button) view.findViewById(R.id.btUnchoose);//取消
        btSend= (Button) view.findViewById(R.id.btSend);//发送
        int windth=DisplayUtil.getScreenWindth(context);
        int heigth=DisplayUtil.getScreenHeight(context)/3;
        sPopupWindow=new PopupWindow(view,windth,heigth,true);
        sPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        WindowManager.LayoutParams lp=((Activity) context).getWindow().getAttributes();
        lp.alpha=0.5f;//0.0-1.0
        ((Activity) context).getWindow().setAttributes(lp);
        sPopupWindow.showAtLocation(viewComment, Gravity.BOTTOM, 0, 0);
        //2.popupwindow消失之后，背景恢复
        sPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
                lp.alpha = 1;
                ((Activity) context).getWindow().setAttributes(lp);
            }
        });
        smallHeadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSmallHeadPopWindow(); //显示表情pop
            }
        });
        btUnchoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sPopupWindow.dismiss();
            }
        });
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int comment_id = 0;
                int topic_id = 0;
                String url = null;
                if (oplogs != null) {
                    comment_id = oplogs.getId();
                    topic_id=oplogs.getTopicId();
                } else {
                    comment_id = 0;
                    topic_id=commentEntitys.getId();
                }
                url = ybHttpURL.URL + "forum/insert.jsp?" + "sid=25&obj.sid=25&obj.add_user_id=4961&add_user=%E7%9F%B3%E5%A4%B4&" +
                        "obj.member_id=0&obj.channel_id=0&obj.topic_id=" + topic_id + "&obj.comment_id=" + comment_id + "&" +
                        "obj.content=" + edit.getText() + "&obj.is_show=1&table_name=wcm_cms_topic_comment";
                MyStringRequest request = new MyStringRequest(url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        PublishTopic topic = gson.fromJson(response, PublishTopic.class);
                        if (topic.getResultCode().equals("0001") && topic.getResultMessage().equals("操作成功")) {
                            Toast.makeText(context, "操作成功", Toast.LENGTH_SHORT).show();
                            notifyData();
                            sPopupWindow.dismiss();
                        }
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
     * 通知fragment刷新数据
     */
    public void notifyData(){
        Method method;
        try {
            method = object.getClass().getMethod("notifyData",String.class);
            method.invoke(object,"");//抛出反射invoke方法
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
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
        View view=LayoutInflater.from(context).inflate(R.layout.pop_qq__head_, null);
        vip= (GridView) view.findViewById(R.id.vip);
        initGridView();
        btSend= (Button) view.findViewById(R.id.btSend);
        int windth=DisplayUtil.getScreenWindth(context);
        int heigth=DisplayUtil.getScreenHeight(context)/2;
        nPopupWindow=new PopupWindow(view,windth,heigth,true);
        nPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        WindowManager.LayoutParams lp=((Activity)context).getWindow().getAttributes();
        lp.alpha=0.5f;//0.0-1.0
        ((Activity)context).getWindow().setAttributes(lp);
        nPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }
    private void initGridView() {
        faceEntityList=new ArrayList<ybFace>();
        faceEntityList.addAll(ChatEmojiDbutil.getChatEmojiList());
        adapter=new yb_ImageBaseAdapter(context,faceEntityList);
        vip.setAdapter(adapter);
        vip.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ybFace=faceEntityList.get(position);
                int index = edit.getSelectionStart();
                Editable editable=edit.getText();
//                editable.delete(index - 1, index);
                editable.insert(index, ybFace.getImgRes());
//                sb.append(editable);
                edit.setText(FaceConversionUtil.getInstace().getExpressionString(context, editable));
                nPopupWindow.dismiss();
            }
        });
    }
}
