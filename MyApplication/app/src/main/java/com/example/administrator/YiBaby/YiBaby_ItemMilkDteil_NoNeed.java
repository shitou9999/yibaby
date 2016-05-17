package com.example.administrator.YiBaby;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.administrator.YiBaby.adapter.MyBaseExpandableListAdapter_NoNeed;
import com.example.administrator.YiBaby.ybEntity.cResultObject;
import com.example.administrator.YiBaby.ybEntity.ybMilkGroup;
import com.example.administrator.YiBaby.yb_Util.BitmapUtil_NoNeed;

import java.util.ArrayList;

/**
 * 详显
 */
public class YiBaby_ItemMilkDteil_NoNeed extends AppCompatActivity {
    private MyBaseExpandableListAdapter_NoNeed adapter;
    private ExpandableListView vip;
    private ArrayList<ybMilkGroup> gData = null;
    private ArrayList<ArrayList<Spanned>> iData ;
    private ArrayList<Spanned> lData;
    private ImageView returns;
    private MyApplication app;
    private cResultObject cResultObjectEntity;
    private TextView title;
    private TextView uer;
    private TextView price;
    private TextView headline;
    private Spanned spanned;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yi_baby__item_milk_dteil_noneed);
        vip= (ExpandableListView) super.findViewById(R.id.vip);
        returns= (ImageView) super.findViewById(R.id.returns);
        title= (TextView) super.findViewById(R.id.title);
        uer= (TextView) super.findViewById(R.id.uer);
        price= (TextView) super.findViewById(R.id.price);
        headline= (TextView) super.findViewById(R.id.headline);
        app= (MyApplication) super.getApplication();////////////////
        gData=new ArrayList<ybMilkGroup>();
        iData = new ArrayList<ArrayList<Spanned>>();
        initAcceptData();
        initBaseViewData();
    }

    private void initAcceptData() {
        Intent intent=super.getIntent();
        Bundle bundle=intent.getExtras();
        cResultObjectEntity= (cResultObject) bundle.getSerializable("cResultObject");

    }

    private void initBaseViewData() {
        headline.setText(cResultObjectEntity.getName());
        title.setText(cResultObjectEntity.getName());
        uer.setText(cResultObjectEntity.getScope());
//        price.setText((int) cResultObjectEntity.getPrice());///////////////
        gData.add(new ybMilkGroup(R.mipmap.prodcut_detail, "产品特点"));
        gData.add(new ybMilkGroup(R.mipmap.product_method,"冲调方法"));
        gData.add(new ybMilkGroup(R.mipmap.product_attention,"注意事项"));
        lData = new ArrayList<Spanned>();
        spanned= Html.fromHtml(cResultObjectEntity.getContent(), new MyImageGetter(), null);
        lData.add(spanned);
        iData.add(lData);
        lData = new ArrayList<Spanned>();
//        spanned= Html.fromHtml(cResultObjectEntity, new MyImageGetter(), null);
//        tvBroeser.setText(spanned);
//        tvBroeser.setMovementMethod(ScrollingMovementMethod.getInstance()); //代码中设置滑动
        lData.add(spanned);
        iData.add(lData);
        lData = new ArrayList<Spanned>();
        spanned= Html.fromHtml(cResultObjectEntity.getService(), new MyImageGetter(), null);
        lData.add(spanned);
        iData.add(lData);
        adapter=new MyBaseExpandableListAdapter_NoNeed(gData,iData,this);
        vip.setAdapter(adapter);
    }
    public class MyImageGetter implements Html.ImageGetter{
        private  URLDrawable urlDrawable=new URLDrawable();
        @Override
        public Drawable getDrawable(String source) {  //source就是返回的src内容
            String url="http://www.meiyibaby.cn"+source;
            ImageRequest request=new ImageRequest(url, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {  //要把BitMap转化成Drawable
//                    AyncHtmlTextActivity.this.getResources(); //可以掉外部类的方法
                    Bitmap newBitmap= BitmapUtil_NoNeed.scaleBitmap(response, getResources().getDisplayMetrics().widthPixels);
                    urlDrawable.bitmap=newBitmap;
                    urlDrawable.setBounds(0,0,urlDrawable.bitmap.getWidth(),urlDrawable.bitmap.getHeight());
//                    tvBroeser.invalidate(); //最好重绘一下
//                    tvBroeser.setText(tvBroeser.getText()); //原来的重新加载一下
                }
            }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            app.getRequestQueue().add(request);
            return urlDrawable;
        }
    }
    private class URLDrawable extends BitmapDrawable { //集成转换
        private Bitmap bitmap;
        @Override
        public void draw(Canvas canvas) {
            if(bitmap!=null) {
                canvas.drawBitmap(bitmap, 0, 0, getPaint());//画笔
            }
        }
    }
}
