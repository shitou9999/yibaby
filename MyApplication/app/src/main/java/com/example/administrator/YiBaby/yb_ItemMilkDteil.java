package com.example.administrator.YiBaby;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.YiBaby.adapter.MyBaseExpandableListAdapter;
import com.example.administrator.YiBaby.ybEntity.cResultObject;
import com.example.administrator.YiBaby.ybEntity.ybMilkGroup;
import com.example.administrator.YiBaby.ybHttpURL.ybHttpURL;
import com.example.administrator.YiBaby.yb_Util.DialogTool;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 详显
 */
public class yb_ItemMilkDteil extends AppCompatActivity {
    private MyBaseExpandableListAdapter adapter;
    private ExpandableListView vip;
    private ArrayList<ybMilkGroup> gData = null;
    private ArrayList<ArrayList<String>> iData ;
    private ArrayList<String> lData;
    private ImageView returns;
    private MyApplication app;
    private cResultObject cResultObjectEntity;
    private TextView title;
    private TextView uer;
    private TextView price;
    private TextView headline;
    private ImageView photo;
    private ImageView shopCar;
    private Button addShop;
    private Button buyItNow;
    private int goodId;
    private SharedPreferences sp;
    private String CookieStr;
    private String userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yi_baby__item_milk_dteil);
        vip= (ExpandableListView) super.findViewById(R.id.vip);
        returns= (ImageView) super.findViewById(R.id.returns);
        shopCar= (ImageView) super.findViewById(R.id.shopCar);
        headline= (TextView) super.findViewById(R.id.headline);
        addShop= (Button) super.findViewById(R.id.addShop);
        buyItNow= (Button) super.findViewById(R.id.buyItNow);
        app= (MyApplication) super.getApplication();
        gData=new ArrayList<ybMilkGroup>();
        iData = new ArrayList<ArrayList<String>>();
        sp=getSharedPreferences("myData", Context.MODE_PRIVATE);
        initAcceptData();
        initBaseViewData();
        initViewLisener();
    }

    private void initAcceptData() {
        Intent intent=super.getIntent();
//        cResultObjectEntity= (cResultObject) intent.getSerializableExtra("cResultObject");
        Bundle bundle=intent.getExtras();
        cResultObjectEntity= (cResultObject) bundle.getSerializable("cResultObject");
        goodId=cResultObjectEntity.getId();
    }

    private void initBaseViewData() {
        headline.setText(cResultObjectEntity.getName());
        gData.add(new ybMilkGroup(R.mipmap.prodcut_detail, "产品特点"));
        gData.add(new ybMilkGroup(R.mipmap.product_method, "冲调方法"));
        gData.add(new ybMilkGroup(R.mipmap.product_attention, "注意事项"));
        lData = new ArrayList<String>();
        lData.add(cResultObjectEntity.getContent());
        iData.add(lData);
        lData = new ArrayList<String>();
        lData.add(cResultObjectEntity.getApplyTo());
        iData.add(lData);
        lData = new ArrayList<String>();
        lData.add(cResultObjectEntity.getService());
        iData.add(lData);
        adapter=new MyBaseExpandableListAdapter(gData,iData,this);
        addHeadtData();
        vip.setAdapter(adapter);
        vip.expandGroup(0); //默认展开
//        vip.expandGroup(1);
    }

    private void addHeadtData(){
        View view= LayoutInflater.from(this).inflate(R.layout.yibaby__item_milk_head,null);
        title= (TextView) view.findViewById(R.id.title);
        uer= (TextView) view.findViewById(R.id.uer);
        price= (TextView) view.findViewById(R.id.price);
        photo= (ImageView) view.findViewById(R.id.photo);
        getNetImg();
        title.setText(cResultObjectEntity.getName());
        uer.setText(cResultObjectEntity.getScope());
        DecimalFormat decimalFormat =new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String distanceString = decimalFormat.format(cResultObjectEntity.getPrice());
        price.setText(distanceString);
        vip.addHeaderView(view);
    }

    private void initViewLisener() {
        returns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yb_ItemMilkDteil.this.finish();
                overridePendingTransition(R.anim.change_activity_in, R.anim.change_activity_out);
            }
        });
    }
    /**
     * 根据Url获取网络图片
     */
    private void getNetImg(){
        String url="http://www.meiyibaby.cn"+cResultObjectEntity.getImg();
        ImageRequest request=new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                if(response!=null){
                    photo.setImageBitmap(response); //可以发个消息
                }
            }
        },0,0,ImageView.ScaleType.CENTER,Bitmap.Config.RGB_565,new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MyApplication.getInstance().getRequestQueue().add(request);
    }

    @Override
    protected void onStart() {
        super.onStart();
        CookieStr=sp.getString("CookieStr", "");
        userInfo=sp.getString("userInfo","");
    }

    /**
     *立即购买
     */
    public void buyItNow(View view){
        if(!"".equals(userInfo)){
            Intent intent=new Intent(yb_ItemMilkDteil.this,ybBuyNow.class);
            startActivity(intent);
        }else{
            DialogTool dialogTool=new DialogTool(yb_ItemMilkDteil.this);
            dialogTool.getLoginDialogView(yb_ItemMilkDteil.this, view);
        }
    }

    /**
     *加入购物车请求
     */
    public void getAddShopingCar(View view){
        if(!"".equals(userInfo)){
            sendShoppingCartByGET(goodId);
        }else{
            DialogTool dialogTool=new DialogTool(yb_ItemMilkDteil.this);
            dialogTool.getLoginDialogView(yb_ItemMilkDteil.this, view);
        }
    }
    private void sendShoppingCartByGET(final int goodsId){
        String url= ybHttpURL.HOST+ "core/control/wcm_eshop_order_cart/control.jsp";
        StringRequest request=new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.contains("ok")){
                    Toast.makeText(yb_ItemMilkDteil.this, "已成功添加至购物车", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(yb_ItemMilkDteil.this, "请求错误", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers=new HashMap<String, String>();
                headers.put("cookie",CookieStr);
                return headers;
            }
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                params.put("goodsid",String.valueOf(goodsId));
                params.put("amount",String.valueOf(1));
                params.put("method","addToCart");
                params.put("areaid=", String.valueOf(100430));
                return params;
            }
        };
        app.getRequestQueue().add(request);
    }
    public void toSeeShopCar(View view){
        if(!"".equals(userInfo)){
            Intent intent=new Intent(yb_ItemMilkDteil.this,yibyMyShopCar.class);
            startActivity(intent);
        }else{
            DialogTool dialogTool=new DialogTool(yb_ItemMilkDteil.this);
            dialogTool.getLoginDialogView(yb_ItemMilkDteil.this, view);
        }

    }
}
