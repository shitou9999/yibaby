package com.example.administrator.YiBaby.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.YiBaby.DBUtil.DBUtil_Comment;
import com.example.administrator.YiBaby.Entity.Comment;
import com.example.administrator.YiBaby.R;
import com.example.administrator.YiBaby.adapter.yb_BBS_CommentBaseAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/5.
 */
public class Near_Comment_Fragment extends Fragment
        implements PullToRefreshBase.OnRefreshListener{
    private PullToRefreshListView vip;
    private yb_BBS_CommentBaseAdapter adapter;
    private List<Comment> commentList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.comment_layout,null);
        vip=(PullToRefreshListView)view.findViewById(R.id.vip);
        initPullListView();
        return view;
    }
    private void initPullListView(){
        commentList=new ArrayList<Comment>();
        adapter=new yb_BBS_CommentBaseAdapter(getActivity(),commentList);
        vip.setAdapter(adapter);
        vip.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        vip.setOnRefreshListener(this);
        vip.setRefreshing();
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
    private void load(){
        commentList.clear();
        new Thread(new Runnable() {
            @Override
            public void run() {
                commentList.addAll(DBUtil_Comment.getCommentData());
                handler.sendEmptyMessage(0);
            }
        }).start();//不要忘了开启子线程
    }
    @Override
    public void onRefresh(PullToRefreshBase refreshView) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                load();
            }
        },1000);
    }
}
