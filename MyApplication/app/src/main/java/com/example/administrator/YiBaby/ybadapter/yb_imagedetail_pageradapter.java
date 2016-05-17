package com.example.administrator.YiBaby.ybadapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.example.administrator.YiBaby.MyApplication;
import com.example.administrator.YiBaby.R;
import com.example.administrator.YiBaby.UIDragImageView;

import java.util.List;

/**
 * Created by Administrator on 2016/3/22.
 */
public class yb_imagedetail_pageradapter extends PagerAdapter{
    private Context context;
    private List<String> imageViewList;
    private MyApplication app;
    public yb_imagedetail_pageradapter(Context context, List<String> imageViewList) {
        this.context = context;
        this.imageViewList = imageViewList;
        app=MyApplication.getInstance();
    }

    @Override
    public int getCount() {
        return imageViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        ImageView imageView=new ImageView(context);//可以直接new
        UIDragImageView imageView=new UIDragImageView(context);//可以直接new
        app.getImageLoader().get(imageViewList.get(position),
                ImageLoader.getImageListener(imageView, R.mipmap.home_baby_bg, R.mipmap.home_baby_bg));
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
