package com.example.administrator.YiBaby.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.YiBaby.Entity.Product;
import com.example.administrator.YiBaby.R;

import java.util.List;

/**
 * Created by Administrator on 2016/1/3.
 */
public class YiBaby_ProductPagerAdapter22 extends PagerAdapter{
    private Context context;
    private List<Product> productList;

    public YiBaby_ProductPagerAdapter22() {
    }

    public YiBaby_ProductPagerAdapter22(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Product product=productList.get(position);
        View view= LayoutInflater.from(context).inflate(R.layout.activity_yi__baby_product, null);
        TextView i1=(TextView)view.findViewById(R.id.i1);
        TextView i2=(TextView)view.findViewById(R.id.i2);
        TextView i3=(TextView)view.findViewById(R.id.i3);
        ImageView image1=(ImageView)view.findViewById(R.id.image1);
        ImageView image2=(ImageView)view.findViewById(R.id.image2);
        i1.setText(product.getText1());
        i2.setText(product.getText2());
        i3.setText(product.getText3());
        image1.setImageResource(product.getImage1());
        image2.setImageResource(product.getImage2());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }


}
