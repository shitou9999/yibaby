package com.example.administrator.YiBaby;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2016/2/17.
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    //设置item之间的间隔 ----------------------使用   （不用也行）
//        SpacesItemDecoration decoration=new SpacesItemDecoration(16);
//        mRecyclerView.addItemDecoration(decoration);
    private int space;

    public SpacesItemDecoration(int space) {
        this.space=space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left=space;
        outRect.right=space;
        outRect.bottom=space;
        if(parent.getChildAdapterPosition(view)==0){
            outRect.top=space;
        }
    }
}
