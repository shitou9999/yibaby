package com.example.administrator.YiBaby.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/1/2.
 */
public class YiBaby_ImgPagerAdapter extends PagerAdapter{
    private Context context;
    private List<ImageView> imgResList;

    public YiBaby_ImgPagerAdapter() {
    }

    public YiBaby_ImgPagerAdapter(Context context, List<ImageView> imgResList) {
        this.context = context;
        this.imgResList = imgResList;
    }

    @Override
    public int getCount() {
        return imgResList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView img=imgResList.get(position);
        container.addView(img);//向容器内添加一个view
        return img;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }


}
