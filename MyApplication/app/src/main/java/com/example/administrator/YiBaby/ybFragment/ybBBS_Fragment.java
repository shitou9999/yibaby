package com.example.administrator.YiBaby.ybFragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.YiBaby.MyApplication;
import com.example.administrator.YiBaby.R;
import com.example.administrator.YiBaby.RequestPackage;
import com.example.administrator.YiBaby.adapter.yb_BBS_CommentBaseAdapter_Net;
import com.example.administrator.YiBaby.ybEntity.BBS_resultObject;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 社区
 * Created by Administrator on 2016/1/5.
 */
public class ybBBS_Fragment extends Fragment
        implements PullToRefreshBase.OnRefreshListener2,RequestPackage.callBackBBS_CommentNeaDataLisener{
    private PullToRefreshListView vip;
    private yb_BBS_CommentBaseAdapter_Net adapter;
    private List<BBS_resultObject> bbs_resultObjectList;
    public static final String BBS_URL ="url" ;
    private int  mParam;
    private RequestPackage pkg;
    private int flag=2;
    private int num;
    private LocalBroadcastManager broadcastManager;
    private MyWorkflowReceiver receiver;
    public static ybBBS_Fragment newInstance(int start_page){
        ybBBS_Fragment ybBBS_Fragment=new ybBBS_Fragment();
        Bundle bundle=new Bundle();
        bundle.putInt(BBS_URL, start_page);
        ybBBS_Fragment.setArguments(bundle);
        return ybBBS_Fragment;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(getArguments()!=null){
            mParam=getArguments().getInt(BBS_URL);
        }
        pkg=new RequestPackage((MyApplication) getActivity().getApplication(),getActivity());
        pkg.setCallBackBBS_CommentNeaDataLisener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.comment_layout,null);
        vip=(PullToRefreshListView)view.findViewById(R.id.vip);
        initPullListView();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        broadcastManager = LocalBroadcastManager.getInstance(getActivity());
        receiver=new MyWorkflowReceiver();
        IntentFilter filter=new IntentFilter();
        filter.addAction("new_msg");
        broadcastManager.registerReceiver(receiver, filter);
    }
    //动态注册
    private class MyWorkflowReceiver  extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
             vip.setRefreshing();
//              adapter.notifyDataSetChanged();
        }
    }

    /**
     * 刷新数据
     */
    public void notifyData(String obj){
        vip.setRefreshing();
    }
    private void initPullListView(){
        bbs_resultObjectList=new ArrayList<BBS_resultObject>();
        adapter=new yb_BBS_CommentBaseAdapter_Net(getActivity(),this,bbs_resultObjectList);
        vip.setAdapter(adapter);
        vip.setMode(PullToRefreshBase.Mode.BOTH);
        vip.setOnRefreshListener(this);
        vip.setRefreshing();
    }
    @Override
    public void callBackBBS_CommentNeaData(List list,int count) {
        if(mParam==1){
            bbs_resultObjectList.clear();
        }
        num=count;
        bbs_resultObjectList.addAll(list);
        handler.sendEmptyMessage(0);
    }
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
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        mParam=1;
        String str = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
        refreshView.getLoadingLayoutProxy().setLastUpdatedLabel("上次更新:" + str);
        pkg.getBBS_CommentNetData(mParam);

    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        if(num%10==0){
            if(flag<=(num/10)){
                mParam++;
                pkg.getBBS_CommentNetData(mParam);
                flag++;
            }else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        vip.onRefreshComplete();
                    }
                },500);
            }
        }else {
            if(flag<=(num/10)+1){
                mParam++;
                pkg.getBBS_CommentNetData(mParam);
                flag++;
            }else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        vip.onRefreshComplete();
                    }
                },500);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        vip.setRefreshing();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        broadcastManager.unregisterReceiver(receiver);
    }
}
