package com.example.administrator.YiBaby.ybadapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.android.volley.toolbox.ImageLoader;
import com.example.administrator.YiBaby.MyApplication;
import com.example.administrator.YiBaby.R;
import com.example.administrator.YiBaby.ybEntity.resourceList;
import com.example.administrator.YiBaby.yb_ImageDetilActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/15.
 */
public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder> {

    private Context context;
    private ArrayList<resourceList> mDatas;
    private MyApplication app;
    public MyAdapter2(Context context, List<resourceList> mDatas) {
        this.context = context;
        this.mDatas =(ArrayList<resourceList>) mDatas;
        app=MyApplication.getInstance();
    }
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
//        void onItemLongClick(View view, int position);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.
                from(viewGroup.getContext()).inflate(R.layout.item_recycler_layout,viewGroup,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        app.getImageLoader().get(mDatas.get(position).getImgSmall(),
                ImageLoader.getImageListener(viewHolder.mIamgView,R.mipmap.home_baby_bg, R.mipmap.home_baby_bg));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, yb_ImageDetilActivity.class);
                intent.putExtra("imgUrl", getStringURL(mDatas));
                intent.putExtra("id",position);
                context.startActivity(intent);
            }
        });
//        if (mOnItemClickListener != null) {//如果设置了监听那么它就不为空，然后回调相应的方法
//            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int pos = viewHolder.getLayoutPosition();
////                    mOnItemClickListener.onItemClick(viewHolder.itemView, pos);
//                    Intent intent = new Intent(context, yb_ImageDetilActivity.class);
//                    intent.putExtra("imgUrl", getStringURL(mDatas));
//                    context.startActivity(intent);
//
//                }
//            });
//        }
    }
    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     *解析URL成为List,直接传过去
     */
    private ArrayList<String> getStringURL(ArrayList<resourceList> mDatas){
        ArrayList<String> mStringURL=new ArrayList<String>();
        for(int i=0;i<mDatas.size();i++){
            mStringURL.add(mDatas.get(i).getImgSmall());
        }
        return mStringURL;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mIamgView;
        public ViewHolder(View view){
            super(view);
            mIamgView = (ImageView) view.findViewById(R.id.item);
        }
    }

}
