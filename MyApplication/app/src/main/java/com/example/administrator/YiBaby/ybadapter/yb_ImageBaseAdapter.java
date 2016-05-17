package com.example.administrator.YiBaby.ybadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.administrator.YiBaby.ybEntity.ybFace;

import java.util.List;

/**
 * Created by Administrator on 2016/1/30.
 */
public class yb_ImageBaseAdapter extends BaseAdapter {
    private Context mContext;
    private List<ybFace> chatEmojiList;
    public yb_ImageBaseAdapter(Context mContext, List<ybFace> chatEmojiList ) {
        this.mContext = mContext;
        this.chatEmojiList  = chatEmojiList ;
    }

    @Override
    public int getCount() {
        return chatEmojiList.size();
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
        ImageView imageView;
        if (convertView == null ) {
            //给ImageView设置资源
            imageView = new ImageView(mContext);
            //设置布局
            imageView.setLayoutParams(new GridView.LayoutParams(85,85));
            //设置显示比例类型
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
        else {
            imageView = (ImageView) convertView;
        }
        ybFace ybFace=chatEmojiList.get(position);
        imageView.setImageResource(ybFace.getIcon());
        return imageView;
    }
}

