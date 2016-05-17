package com.example.administrator.YiBaby.ybadapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.administrator.YiBaby.MyApplication;
import com.example.administrator.YiBaby.R;
import com.example.administrator.YiBaby.ybEntity.cResultObject;
import com.example.administrator.YiBaby.ybHttpURL.ybHttpURL;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2016/1/3.
 */
public class yb_ProductBaseAdapter extends BaseAdapter{
    private Context context;
    private List<cResultObject> cResultObjectList;
    private MyApplication myApplication;

    public yb_ProductBaseAdapter() {
    }

    public yb_ProductBaseAdapter(Context context, List<cResultObject> cResultObjectList) {
        this.context = context;
        this.cResultObjectList = cResultObjectList;
        myApplication= (MyApplication) ((Activity)context).getApplication();
    }

    @Override
    public int getCount() {
        return cResultObjectList.size();
    }

    @Override
    public Object getItem(int position) {
        return cResultObjectList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder{
        public TextView title;
        public TextView sbTitle;
        public TextView price;
        public ImageView imgRes1;
        public ImageView imgRes2;
        public TextView taypeTitle;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        cResultObject product=cResultObjectList.get(position);
        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
                    convertView= LayoutInflater.from(context).
                            inflate(R.layout.activity_yi__baby_product,null);
                    holder.taypeTitle=(TextView)convertView.findViewById(R.id.testStype);
                    holder.title=(TextView)convertView.findViewById(R.id.i1);
                    holder.price=(TextView)convertView.findViewById(R.id.i2);
                    holder.sbTitle=(TextView)convertView.findViewById(R.id.i3);
                    holder.imgRes1=(ImageView)convertView.findViewById(R.id.image1);
                    holder.imgRes2=(ImageView)convertView.findViewById(R.id.image2);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        holder.title.setText(product.getName());
        DecimalFormat decimalFormat =new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String distanceString = decimalFormat.format(product.getPrice());
        holder.price.setText("￥"+distanceString+"");///////////////////////////////////////
        holder.sbTitle.setText("试用于"+product.getScope());
        myApplication.getImageLoader().get(ybHttpURL.HOST + product.getImg(),
                ImageLoader.getImageListener(holder.imgRes1, 0, 0));
        String zhege=cResultObjectList.get(position).getTypeName();
        String qianyige=(position-1)>=0?cResultObjectList.get(position-1).getTypeName():"";
        if (!zhege.equals(qianyige)){
            holder.taypeTitle.setVisibility(View.VISIBLE);
            holder.taypeTitle.setText(zhege);
        }else {
            holder.taypeTitle.setVisibility(View.GONE);
        }
        return convertView;
    }
}
