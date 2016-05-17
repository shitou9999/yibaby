package com.example.administrator.YiBaby.ybadapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.administrator.YiBaby.MyApplication;
import com.example.administrator.YiBaby.MyStringRequest;
import com.example.administrator.YiBaby.ybEntity.cResultObject;
import com.example.administrator.YiBaby.ybEntity.ybHead;
import com.example.administrator.YiBaby.ybHttpURL.ybHttpURL;
import com.example.administrator.YiBaby.yb_ItemMilkDteil;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Administrator on 2016/1/2.
 */
public class yb_ImgPagerAdapter extends PagerAdapter{
    private Context context;
//    private List<ImageView> imgResList;
    private List<Bitmap> bitmapList;
    private MyApplication app;
    private ybHead ybHead;
    private cResultObject cResultObjectNet;

    public yb_ImgPagerAdapter() {
    }

    public yb_ImgPagerAdapter(Context context, List<Bitmap> bitmapList) {
        this.context = context;
        this.bitmapList = bitmapList;
        app=MyApplication.getInstance();
    }

    @Override
    public int getCount() {
        return bitmapList.size()*100;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        Bitmap bitmap=bitmapList.get(position%(bitmapList.size()));
        ImageView img=new ImageView(context);
        img.setImageBitmap(bitmap);
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHeadNetData(position%(bitmapList.size()));
            }
        });
        container.addView(img);
        return img;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
    private void getHeadNetData(int position){
        String url="";
        switch (position){
            case 0:
                url= ybHttpURL.HEAD_DTETIL01;
                break;
            case 1:
                url= ybHttpURL.HEAD_DTETIL02;
                break;
        }
        getData(url);
    }
    private void getData(String url){
        MyStringRequest request=new MyStringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                ybHead=gson.fromJson(response,ybHead.class);
                cResultObjectNet=gson.fromJson(ybHead.getResultObject(),cResultObject.class);
                handler.sendEmptyMessage(1);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("jereh",error.getMessage());
            }
        });
        app.getRequestQueue().add(request);
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    Intent intent=new Intent(context, yb_ItemMilkDteil.class);
//                    intent.putExtra("cResultObject",cResultObjectNet);
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("cResultObject",cResultObjectNet);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                  break;
            }
        }
    };
}
