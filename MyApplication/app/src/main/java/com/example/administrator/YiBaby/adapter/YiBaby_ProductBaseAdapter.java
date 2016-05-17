package com.example.administrator.YiBaby.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.YiBaby.Entity.Product;
import com.example.administrator.YiBaby.R;

import java.util.List;

/**
 * Created by Administrator on 2016/1/3.
 */
public class YiBaby_ProductBaseAdapter extends BaseAdapter{
    private Context context;
    private List<Product> productList;
    private static int TYPE=2;

    public YiBaby_ProductBaseAdapter() {
    }

    public YiBaby_ProductBaseAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
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
        Product product=productList.get(position);
        int type=getItemViewType(position);
        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            switch (type){
                case 1:
                    convertView= LayoutInflater.from(context).
                            inflate(R.layout.activity_yi__baby_product,null);
                    holder.title=(TextView)convertView.findViewById(R.id.i1);
                    holder.price=(TextView)convertView.findViewById(R.id.i2);
                    holder.sbTitle=(TextView)convertView.findViewById(R.id.i3);
                    holder.imgRes1=(ImageView)convertView.findViewById(R.id.image1);
                    holder.imgRes2=(ImageView)convertView.findViewById(R.id.image2);
                    break;
                case 0:
                    convertView= LayoutInflater.from(context).
                            inflate(R.layout.yibaby_product_type,null);
                    holder.taypeTitle=(TextView)convertView.findViewById(R.id.testStype);
                    break;
            }
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        switch (type){
            case 0:
                holder.taypeTitle.setText(product.getClassify());

                break;
            case 1:
                holder.title.setText(product.getText1());
                holder.price.setText(product.getText2());
                holder.sbTitle.setText(product.getText3());
                holder.imgRes1.setImageResource(product.getImage1());
                holder.imgRes2.setImageResource(product.getImage2());
                break;
        }
        return convertView;
    }
    @Override
    public int getItemViewType(int position) {
        return productList.get(position).getStype();
    }

    @Override
    public int getViewTypeCount() {
        return TYPE;
    }
}
