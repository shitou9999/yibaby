package com.example.administrator.YiBaby;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.administrator.YiBaby.ybEntity.PublishTopic;
import com.example.administrator.YiBaby.ybEntity.ybFace;
import com.example.administrator.YiBaby.yb_Util.BitmapUtil;
import com.example.administrator.YiBaby.yb_Util.ChatEmojiDbutil;
import com.example.administrator.YiBaby.yb_Util.DisplayUtil;
import com.example.administrator.YiBaby.yb_Util.FaceConversionUtil;
import com.example.administrator.YiBaby.yb_Util.FileUitlity;
import com.example.administrator.YiBaby.ybadapter.yb_ImageBaseAdapter;
import com.example.administrator.YiBaby.ybadapter.yb_addPotoBaseAdapter;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class yb_NewTopic extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private TextView number;// 用来显示剩余字数
    private EditText editContent;//定义一个文本输入框
    int num = 0;//限制的最大字数
    private ImageView returns;
    private LinearLayout getCamera;
    private LinearLayout addStore;
    private ImageView addPtoto;
    private PopupWindow window;
    private GridView gridView;
    private yb_addPotoBaseAdapter adapter;
    private List<Bitmap> bitmapList;
    private EditText etTitle;
    private MyApplication app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yi_baby__new_topic);
        number= (TextView) super.findViewById(R.id.number);
        etTitle= (EditText) super.findViewById(R.id.etTitle);
        editContent= (EditText) super.findViewById(R.id.editContent);
        returns= (ImageView) super.findViewById(R.id.returns);
        addStore= (LinearLayout) super.findViewById(R.id.addStore);
        getCamera= (LinearLayout) super.findViewById(R.id.getCamera);
        addPtoto= (ImageView) super.findViewById(R.id.addPtoto);
        gridView= (GridView) super.findViewById(R.id.gridView);
        app=MyApplication.getInstance();
        editContent.addTextChangedListener(mTextWatcher);
        bitmapList=new ArrayList<Bitmap>();
        gridView.setOnItemClickListener(this);
        initReturnsListener();

    }

    /**
     * 回退键监听
     */
    private void initReturnsListener(){
        returns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yb_NewTopic.this.finish();
                overridePendingTransition(R.anim.change_activity_in, R.anim.change_activity_out);
            }
        });
    }
    /**
     * 加号监听
     */
    public void appPotoLisener(View view){
            publishTopic(view);
    }

    /**
     * 初始化gridview
     */
    private void initGridView() {
        adapter=new yb_addPotoBaseAdapter(this,bitmapList);
        gridView.setAdapter(adapter);
    }

    /**
     * gridview监听
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            dialogShow(position);
    }
    /*
     * Dialog对话框提示用户删除操作
     * position为删除图片位置
     */
    protected void dialogShow(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确认移除已添加图片吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                bitmapList.remove(position);
                if(bitmapList.size()!=0){
                    addPtoto.setVisibility(View.VISIBLE);
                }else if(bitmapList.size()==0){
                    getCamera.setVisibility(View.VISIBLE);
                    addStore.setVisibility(View.GONE);
                }
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
    /**
     * 点击发表主题
     */
    public void publishTopic(View view){
        View viewPop= LayoutInflater.from(this).inflate(R.layout.yt_set_activity_camera_menu, null);
        window=new PopupWindow(viewPop, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setAnimationStyle(R.style.popWindow_Share_Show);
        window.setFocusable(true);
        window.setOutsideTouchable(true);
        backgroundAlpha(0.3f);
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
    }
    /**
     * 设置添加屏幕的背景透明度
     */
    public void backgroundAlpha(float bgAlpha){
        WindowManager.LayoutParams lp=getWindow().getAttributes();
        lp.alpha=bgAlpha;
        getWindow().setAttributes(lp);
    }

    /**
     * 取消上传
     */
    public void canel(View view){
        window.dismiss();
    }
    private String headFile;
    private final int CAMERA_PHOTO=1;//照的
    private final int STORE_PHOTO=2;//本地图库
    private String headName;
    private Uri uri;
    public void camera(View view){
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            //调用相机
            Intent camera=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            File parent= FileUitlity.getInstance(this).makeDir("head_img");
            headName=System.currentTimeMillis()+".jpg";
            headFile=parent.getPath()+File.separator+headName;
            camera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(headFile)));
            camera.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            window.dismiss();
            startActivityForResult(camera, CAMERA_PHOTO);
        }
    }

    public void photo(View view){
        //调用图库
        Intent photo=new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        window.dismiss();
        startActivityForResult(photo, STORE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!= Activity.RESULT_OK){
            return;
        }
        switch(requestCode){
            case STORE_PHOTO: //本地图库
                if(data!=null){
                    uri=data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                        Bitmap btmap3= BitmapUtil.extractThumbnail(bitmap, 230, 230);
                        bitmapList.add(btmap3);
                        if(bitmapList.size()==3) {
                            Toast.makeText(yb_NewTopic.this, "上传图片数量达到上限！", Toast.LENGTH_SHORT).show();
                            addPtoto.setVisibility(View.GONE);
                        }
                        getCamera.setVisibility(View.GONE);
                        addStore.setVisibility(View.VISIBLE);
                        initGridView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CAMERA_PHOTO: //相机
                uri=Uri.fromFile(new File(headFile));
//                    Bitmap btmap2=BitmapFactory.decodeFile(headFile)
                Bitmap btmap2= BitmapUtil.fitSizeImg(headFile);
                Bitmap btmap3= BitmapUtil.extractThumbnail(btmap2, 230, 230);
//                bitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.icon_seletor));
                bitmapList.add(btmap3);
                if(bitmapList.size()==3) {
                    Toast.makeText(yb_NewTopic.this, "上传图片数量达到上限！", Toast.LENGTH_SHORT).show();
                    addPtoto.setVisibility(View.GONE);
                }
                getCamera.setVisibility(View.GONE);
                addStore.setVisibility(View.VISIBLE);
                initGridView();
                break;
        }
    }

    /**
     * 将Bitmap转换为Base64编码的字符串
     * @param b
     * @return
     */
    public String convertBitmap(Bitmap b){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, baos);
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String  headPicString = new String(Base64.encode(baos.toByteArray(), 0));
        try {
            if(baos!=null) baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return headPicString;
    }

    /**
     * 发送主题
     */
    public void tvSend_servicer(View view) {
        if("".equals(etTitle.getText().toString())){
            Toast.makeText(yb_NewTopic.this, "内容不能为空！", Toast.LENGTH_SHORT).show();
        }else{
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("确定发送这条主题吗？");
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    sendMyTopic();
                    dialog.dismiss();//关闭对话框
                    yb_NewTopic.this.finish();
                }
            });
            AlertDialog dialog=builder.create();
            dialog.show();
        }
    }

    /**
     * 小头像的pop
     */
    public void ivClickHead(View view){
        showSmallHeadPopWindow();
    }
    private GridView vip;
    private yb_ImageBaseAdapter adapterImg;
    private List<ybFace> faceEntityList;
    private ybFace ybFace;
    private PopupWindow mPopupWindow;
//    private Button btSend;
    public void showSmallHeadPopWindow(){
        View view=LayoutInflater.from(this).inflate(R.layout.pop_qq__head_, null);
        vip= (GridView) view.findViewById(R.id.vip);
        initGridViewImg();
//        btSend= (Button) view.findViewById(R.id.btSend);
        int windth= DisplayUtil.getScreenWindth(this);
        int heigth=DisplayUtil.getScreenHeight(this)/2;
        mPopupWindow=new PopupWindow(view,windth,heigth,true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        WindowManager.LayoutParams lp=getWindow().getAttributes();
        lp.alpha=0.5f;
        getWindow().setAttributes(lp);
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
    private StringBuffer sb;
    private void initGridViewImg() {
        faceEntityList=new ArrayList<ybFace>();
        faceEntityList.addAll(ChatEmojiDbutil.getChatEmojiList());
        adapterImg=new yb_ImageBaseAdapter(this,faceEntityList);
        vip.setAdapter(adapterImg);
        vip.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ybFace=faceEntityList.get(position);
                SpannableString spannableString = FaceConversionUtil.getInstace().getExpressionString(yb_NewTopic.this, ybFace.getImgRes());
                sb=new StringBuffer();
                int index = editContent.getSelectionStart();
                Editable editable = editContent.getText();
                editable.insert(index, spannableString);
                sb.append(editable);
                mPopupWindow.dismiss();
            }
        });
    }
    /**
     * 字数监听
     */
    TextWatcher mTextWatcher = new TextWatcher() {
        private CharSequence temp;
        private int editStart ;
        private int editEnd ;
        @Override
        public void beforeTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
            temp = s;
        }

        @Override
        public void onTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
            number.setText(s);
        }

        @Override
        public void afterTextChanged(Editable s) {
            int numb = num + s.length();
            number.setText("" + numb);
            editStart = editContent.getSelectionStart();
            editEnd = editContent.getSelectionEnd();
            if (temp.length() > 200) {
                Toast.makeText(yb_NewTopic.this, "你输入的字数已经超过了限制！", Toast.LENGTH_SHORT).show();
                s.delete(editStart-1, editEnd);
                int tempSelection = editStart;
                editContent.setText(s);
                editContent.setSelection(tempSelection);
            }
        }
    };
    private void sendMyTopic(){
        String url ="http://www.meiyibaby.cn/appbackend/forum/insert.jsp?obj.sid=25&obj.add_user_id=4961" +
                "&add_user=%E7%9F%B3%E5%A4%B4&obj.title="+etTitle.getText()+"&obj.channel_id=7" +
                "&obj.content="+sb.toString()+"&obj.flag=0&obj.is_show=1&obj.is_show_others=1" +
                "&resource_json=%5B%7B%22fileName%22%3A%2262f84c2c89664203a7a5e59c07e1b09f.png" +
                "%22%2C%22fileSize%22%3A40806%2C%22height%22%3A220%2C%22id%22%3A0%2C%22" +
                "imgSmall%22%3A%22" +
                "http%3A%2F%2Fxxlimg.21-sun.com%2Fupload%2F25%2Fimage%2F2016%2F03%2F26%2F20160326155737_309.png" +
                "%22%2C%22parentUuid%22%3A%228972020e5b68416da37cdf9358d7e4bc%22%2C%22sid%22%3A0%2C%22topicId%22%3A0%2C%22width%22%3A372%7D%2C%7B%22" +
                "fileName%22%3A%22712d30fa98e04c95820d6fc5d42d484c.png%22%2C%22fileSize%22%3A63736%2C%22height%22%3A224%2C%22id%22%3A0%2C%22" +
                "imgSmall%22%3A%22" +
                "http%3A%2F%2Fxxlimg.21-sun.com%2Fupload%2F25%2Fimage%2F2016%2F03%2F26%2F20160326155752_402.png" +
                "%22%2C%22parentUuid%22%3A%2282ded1a052b048bba17687d897c73682%22%2C%22sid%22%3A0%2C%22topicId%22%3A0%2C%22width%22%3A367%7D%5D" +
                "&table_name=wcm_cms_topic";
        MyStringRequest request=new MyStringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                PublishTopic topic=gson.fromJson(response, PublishTopic.class);
                if(topic.getResultCode().equals("0001")&&topic.getResultMessage().equals("操作成功")){
                    Toast.makeText(yb_NewTopic.this, "上传成功", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        app.getRequestQueue().add(request);
    }

}
