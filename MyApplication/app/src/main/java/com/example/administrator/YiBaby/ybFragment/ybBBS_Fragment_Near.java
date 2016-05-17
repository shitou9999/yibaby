package com.example.administrator.YiBaby.ybFragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
public class ybBBS_Fragment_Near extends Fragment
        implements PullToRefreshBase.OnRefreshListener,RequestPackage.callBackBBS_CommentNeaDataLisener_near{
    private PullToRefreshListView vip;
    private yb_BBS_CommentBaseAdapter_Net adapter;
    private List<BBS_resultObject> bbs_resultObjectList;
    public static final String BBS_URL ="url" ;
    private String  mParam;
    private RequestPackage pkg;
    private MyApplication app;
    public static ybBBS_Fragment_Near newInstance(String start_page){
        ybBBS_Fragment_Near ybBBS_Fragment=new ybBBS_Fragment_Near();
        Bundle bundle=new Bundle();
        bundle.putString(BBS_URL, start_page);
        ybBBS_Fragment.setArguments(bundle);
        return ybBBS_Fragment;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(getArguments()!=null){
            mParam=getArguments().getString(BBS_URL);
        }
        app=MyApplication.getInstance();
        pkg=new RequestPackage((MyApplication) getActivity().getApplication(),getActivity());
        pkg.setCallBackBBS_CommentNeaDataLisener_near(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.comment_layout,null);
        vip=(PullToRefreshListView)view.findViewById(R.id.vip);
        initPullListView();
        return view;
    }
    private void initPullListView(){
        bbs_resultObjectList=new ArrayList<BBS_resultObject>();
        adapter=new yb_BBS_CommentBaseAdapter_Net(getActivity(),this,bbs_resultObjectList);
        vip.setAdapter(adapter);
        vip.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        vip.setOnRefreshListener(this);
        vip.setRefreshing();
    }
    /**
     * 刷新数据
     */
    public void notifyData(String obj){
        vip.setRefreshing();
    }
    @Override
    public void callBackBBS_CommentNeaData_near(List list) {
        bbs_resultObjectList.clear();
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
    public void onRefresh(PullToRefreshBase refreshView) {
        pkg.getBBS_CommentNetData_near(mParam);
    }
}
