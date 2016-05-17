package com.example.administrator.YiBaby.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
public class MyBaseExpandableListAdapter extends BaseExpandableListAdapter {
    private List<ybMilkGroup> gData;
    private ArrayList<ArrayList<String>> iData;
    private Context mContext;

    public MyBaseExpandableListAdapter(ArrayList<ybMilkGroup> gData, ArrayList<ArrayList<String>> iData, Context mContext) {
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
        return iData.get(childPosition).get(childPosition);
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
                    R.layout.yibaby_item_exlist_group,null);
            groupHolder = new ViewHolderGroup();
            groupHolder.tv_group_name = (TextView) convertView.findViewById(R.id.characteristic);
            groupHolder.iv_group_img= (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(groupHolder);
        }else{
            groupHolder = (ViewHolderGroup) convertView.getTag();
        }
        groupHolder.tv_group_name.setText(gData.get(groupPosition).getTitle());
        return convertView;
    }
    //取得显示给定分组给定子位置的数据用的视图
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderItem itemHolder;
        if(convertView == null){
//            convertView = LayoutInflater.from(mContext).inflate(
//                    R.layout.yibaby_item_exlist_item,null);
            convertView=new WebView(mContext);
            itemHolder = new ViewHolderItem();
            itemHolder.tv_name = (WebView)convertView;
            itemHolder.tv_name.getSettings().setSupportZoom(true);
            itemHolder.tv_name.getSettings().setJavaScriptEnabled(true);
            itemHolder.tv_name.setWebViewClient(new WebViewClient());
            convertView.setTag(itemHolder);
        }else{
            itemHolder = (ViewHolderItem) convertView.getTag();
        }

        itemHolder.tv_name.loadDataWithBaseURL("http://www.meiyibaby.cn",
                getHtmlData(iData.get(groupPosition).get(childPosition)), "text/html", "utf-8", null);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    private static class ViewHolderGroup{
        private TextView tv_group_name;
        private ImageView iv_group_img;
    }

    private static class ViewHolderItem{
        private WebView tv_name;
    }
    private String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%; width:auto; height:auto;}</style>" +
                "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></HTMl>";
    }
}
