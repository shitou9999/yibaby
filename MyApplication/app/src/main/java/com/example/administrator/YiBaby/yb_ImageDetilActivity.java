package com.example.administrator.YiBaby;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.administrator.YiBaby.ybadapter.yb_imagedetail_pageradapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class yb_ImageDetilActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ViewPager vip;
    private List<String> stringList;
    private yb_imagedetail_pageradapter pageradapter;
    private TextView page;
    private TextView totalCount;
    private File filePath;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yb__image_detil);
        vip= (ViewPager) super.findViewById(R.id.vip);
        page= (TextView) super.findViewById(R.id.page);
        totalCount= (TextView) super.findViewById(R.id.totalCount);
        vip.addOnPageChangeListener(this);
        stringList=new ArrayList<String>();
        receiveIntentData();
        initViewPagerView();
        vip.setCurrentItem(id);
    }

    private void receiveIntentData() {
        Intent intent=super.getIntent();
        stringList=intent.getStringArrayListExtra("imgUrl");
        id=intent.getIntExtra("id", 0);
        totalCount.setText(stringList.size()+"");
        page.setText(id + 1 + "");
    }

    private void initViewPagerView() {
        pageradapter=new yb_imagedetail_pageradapter(this,stringList);
        vip.setAdapter(pageradapter);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        vip.setCurrentItem(position);
        page.setText(vip.getCurrentItem() + 1 + "");
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    /**
     * 下载图片请求
     */
    public void saveImg(View view){
        downloadPhotoRequest();
    }

    private void downloadPhotoRequest(){
        String url=stringList.get(vip.getCurrentItem());
        ImageRequest request = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                saveImgSD(response);
                Toast.makeText(yb_ImageDetilActivity.this,
                        "图片已保存到"+Environment.getExternalStorageDirectory()+"/"+"yibaobei", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);//系统广播
                Uri contentUri = Uri.fromFile(filePath);
                intent.setData(contentUri);
                sendBroadcast(intent);
            }
        }, 0, 0, ImageView.ScaleType.CENTER_CROP, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MyApplication.getInstance().getRequestQueue().add(request);
    }
    private void saveImgSD(Bitmap bitmap){
        FileOutputStream fos=null;
        filePath=new File(Environment.getExternalStorageDirectory()+"/"+"yibaobei"+"/"+System.currentTimeMillis()+".jpg");
        if(!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdir();
        }
        try {
            fos=new FileOutputStream(filePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }finally {
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
//        getFilesDir() 内存储
//        getCacheDir() 内存储
//        Environment.getExternalStorageDirectory()外存储
//        saveFile=getFilesDir().getPath()+"/"+System.currentTimeMillis()+".jpg";
//        FileOutputStream fos=null;
//        try {
//            fos=new FileOutputStream(saveFile);
//            img.compress(Bitmap.CompressFormat.JPEG,100,fos); //100高质量  无损压缩

}
