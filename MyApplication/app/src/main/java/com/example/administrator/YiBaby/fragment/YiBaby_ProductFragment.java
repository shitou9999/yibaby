package com.example.administrator.YiBaby.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.YiBaby.DBUtil.DBUtil_YiBaby;
import com.example.administrator.YiBaby.Entity.Product;
import com.example.administrator.YiBaby.R;
import com.example.administrator.YiBaby.adapter.YiBaby_ProductBaseAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/3.
 */
public class YiBaby_ProductFragment extends Fragment
        implements PullToRefreshBase.OnRefreshListener2{
    private PullToRefreshListView vip;
    private YiBaby_ProductBaseAdapter adapter;
    private List<Product> productList;
    private int pageNo;
    private int pageSize;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.yibaby_product_fragment_layout,null);
        vip=(PullToRefreshListView)view.findViewById(R.id.vip);
        initPullListView();
        return view;
    }
    private void initPullListView(){
        productList=new ArrayList<Product>();
//        productList.addAll(DBUtil_YiBaby.getProductData());
        adapter=new YiBaby_ProductBaseAdapter(super.getActivity(),productList);
        vip.setMode(PullToRefreshBase.Mode.BOTH);
        vip.setOnRefreshListener(this);
        vip.setAdapter(adapter);
        vip.setRefreshing();
    }
    //********************************************************************************
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    adapter.notifyDataSetChanged();
                    vip.onRefreshComplete();
                    break;
            }
        }
    };
    private void load(){
        productList.clear();
        new Thread(new Runnable() {
            @Override
            public void run() {
                productList.addAll(DBUtil_YiBaby.getProductDataByPage(1,15));
                handler.sendEmptyMessage(0);
            }
        }).start();
    }
    private void loadMore(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                productList.addAll(DBUtil_YiBaby.getProductDataByPage(pageNo,15));
                handler.sendEmptyMessage(0);
            }
        }).start();
    }
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        pageNo=1;
        load();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        if(pageNo==3){
            Toast.makeText(getContext(),"更新完了",Toast.LENGTH_LONG).show();
            vip.onRefreshComplete();
        }else{
            pageNo++;
            loadMore();
        }
    }
}
