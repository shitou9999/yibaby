package com.example.administrator.YiBaby.adapter;

import android.content.Context;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.YiBaby.R;
import com.example.administrator.YiBaby.ybEntity.ybMilkGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/6.
 */
public class MyBaseExpandableListAdapter_NoNeed extends BaseExpandableListAdapter {
    private List<ybMilkGroup> gData;
    private ArrayList<ArrayList<Spanned>> iData;
    private Context mContext;

    public MyBaseExpandableListAdapter_NoNeed(ArrayList<ybMilkGroup> gData, ArrayList<ArrayList<Spanned>> iData, Context mContext) {
        this.gData = gData;
        this.iData = iData;
        this.mContext = mContext;
    }


    @Override
    public int getGroupCount() {
        return gData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return iData.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return gData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return iData.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
    //取得用于显示给定分组的视图. 这个方法仅返回分组的视图对象
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderGroup groupHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.yibaby_item_exlist_group, parent, false);
            groupHolder = new ViewHolderGroup();
            groupHolder.tv_group_name = (TextView) convertView.findViewById(R.id.characteristic);
            groupHolder.iv_group_img= (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(groupHolder);
        }else{
            groupHolder = (ViewHolderGroup) convertView.getTag();
        }
        groupHolder.tv_group_name.setText(gData.get(groupPosition).getTitle());
        groupHolder.iv_group_img.setImageResource(gData.get(groupPosition).getImage());//代码中设置滑动
        return convertView;
    }
    //取得显示给定分组给定子位置的数据用的视图
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderItem itemHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.yibaby_item_exlist_item, parent, false);
            itemHolder = new ViewHolderItem();
            itemHolder.tv_name = (TextView) convertView.findViewById(R.id.content);
            convertView.setTag(itemHolder);
        }else{
            itemHolder = (ViewHolderItem) convertView.getTag();
        }
        itemHolder.tv_name.setText(iData.get(groupPosition).get(childPosition));////////
        itemHolder.tv_name.setMovementMethod(ScrollingMovementMethod.getInstance());
        itemHolder.tv_name.invalidate(); //最好重绘一下
        itemHolder.tv_name.setText(itemHolder.tv_name.getText()); //原来的重新加载一下
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    private static class ViewHolderGroup{
        private TextView tv_group_name;
        private ImageView iv_group_img;
    }

    private static class ViewHolderItem{
        private ImageView img_icon;
        private TextView tv_name;
    }
}
