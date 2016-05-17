package com.example.administrator.YiBaby.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.YiBaby.Entity.Mood;
import com.example.administrator.YiBaby.R;

import java.util.List;

/**
 * Created by Administrator on 2016/1/6.
 */
public class YiBaby_MoodBaseAdapter extends BaseAdapter{
    private Context context;
    private List<Mood> moodList;

    public YiBaby_MoodBaseAdapter() {
    }

    public YiBaby_MoodBaseAdapter(Context context, List<Mood> moodList) {
        this.context = context;
        this.moodList = moodList;
    }

    @Override
    public int getCount() {
        return moodList.size();
    }

    @Override
    public Object getItem(int position) {
        return moodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder{
        public TextView tvTitle;
        public ImageView ivImg;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Mood mood=moodList.get(position);
        ViewHolder holder=null;
        if(convertView==null){
           holder=new ViewHolder();
            convertView= LayoutInflater.from(context)
                    .inflate(R.layout.sing_in_wall_mood,null);
            holder.ivImg=(ImageView)convertView.findViewById(R.id.image1);
            holder.tvTitle=(TextView)convertView.findViewById(R.id.text1);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        holder.tvTitle.setText(mood.getTitle());
        holder.ivImg.setImageResource(mood.getImgRes());
        return convertView;
    }
}
