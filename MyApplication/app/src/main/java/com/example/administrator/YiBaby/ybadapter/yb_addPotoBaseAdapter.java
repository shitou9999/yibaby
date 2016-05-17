package com.example.administrator.YiBaby.ybadapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.administrator.YiBaby.MyApplication;

import java.util.List;

/**
 * Created by Administrator on 2016/1/30.
 */
public class yb_addPotoBaseAdapter extends BaseAdapter {
    private List<Bitmap> bitmapList;
    private Context mContext;
    private MyApplication app;
    public yb_addPotoBaseAdapter(Context mContext, List<Bitmap> bitmapList) {
        this.mContext = mContext;
        this.bitmapList  = bitmapList ;
        app=MyApplication.getInstance();
    }

    @Override
    public int getCount() {
        return bitmapList .size();
//        return
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView=new ImageView(mContext);
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Bitmap bm=bitmapList.get(position);
        imageView.setImageBitmap(bm);
//        if(position==bitmapList.size()){
//            imageView.setImageBitmap(R.drawable.);
//            if(bitmapList.size()==5){
//
//            }else{
//
//            }
//        }
        return imageView;
    }

}

