package com.example.administrator.YiBaby.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.administrator.YiBaby.MyApplication;
import com.example.administrator.YiBaby.R;
import com.example.administrator.YiBaby.RequestPackage;
import com.example.administrator.YiBaby.ybEntity.cResultObject;
import com.example.administrator.YiBaby.yb_ItemMilkDteil;
import com.example.administrator.YiBaby.ybadapter.yb_ProductBaseAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/3.
 */
public class YiBaby_ProductFragment2s extends Fragment implements PullToRefreshBase.OnRefreshListener,
        RequestPackage.callBacksGetMilkNet ,AdapterView.OnItemClickListener{
    private PullToRefreshListView vip;
    private yb_ProductBaseAdapter adapter;
    private List<cResultObject> cResultObjectList;
    private RequestPackage pac;
    private int flag;
    private MyApplication app;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        pac=new RequestPackage((MyApplication) getActivity().getApplication(),context);///////////////////
        app=MyApplication.getInstance();
        pac.setCallBacksGetMilkNet(this);
    }

    public static YiBaby_ProductFragment2s newInstance(int flag){
        YiBaby_ProductFragment2s yiBaby_productFragment2s=new YiBaby_ProductFragment2s();
        Bundle bundle=new Bundle();
        bundle.putInt("flag", flag);
        yiBaby_productFragment2s.setArguments(bundle);
        return yiBaby_productFragment2s;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.yibaby_product_fragment_layout,null);
        vip=(PullToRefreshListView)view.findViewById(R.id.vip);
        initPullListView();
        return view;
    }
    private void initPullListView(){
        cResultObjectList=new ArrayList<cResultObject>();
        adapter=new yb_ProductBaseAdapter(super.getActivity(),cResultObjectList);
        vip.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        vip.setOnRefreshListener(this);
        vip.setOnItemClickListener(this);
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
    @Override
    public void callBacksGetMilk(List list) {
        cResultObjectList.clear();////////////////////////////
        cResultObjectList.addAll(list);
        paixu();
        handler.sendEmptyMessage(0);
    }
    public void paixu(){
        for (int j=0;j<cResultObjectList.size()-1;j++){
            for (int i=0;i<cResultObjectList.size()-1;i++){
                if (cResultObjectList.get(i).getBrandId()>=cResultObjectList.get(i+1).getBrandId()){
                    cResultObject milk1=cResultObjectList.get(i);
                    cResultObject milk2=cResultObjectList.get(i+1);
                    cResultObjectList.set(i,milk1);
                    cResultObjectList.set(i+1,milk2);
                }else {
                    cResultObject milk1=cResultObjectList.get(i);
                    cResultObject milk2=cResultObjectList.get(i+1);
                    cResultObjectList.set(i,milk2);
                    cResultObjectList.set(i+1,milk1);
                }
            }
        }
    }
    @Override
    public void onRefresh(PullToRefreshBase refreshView) {
        Bundle bundle=getArguments();
        flag=bundle.getInt("flag");
        String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
        refreshView.getLoadingLayoutProxy().setRefreshingLabel("正在刷新");
        refreshView.getLoadingLayoutProxy().setPullLabel("下拉刷新");
        refreshView.getLoadingLayoutProxy().setReleaseLabel("释放开始刷新");
//        refreshView.getLoadingLayoutProxy().setLastUpdatedLabel("最后更新:" + label);
        pac.getCallGoByGet(flag);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        cResultObject cResultObjectEntity=cResultObjectList.get(position-1);
        Intent intent=new Intent(getActivity(), yb_ItemMilkDteil.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("cResultObject", cResultObjectEntity);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
