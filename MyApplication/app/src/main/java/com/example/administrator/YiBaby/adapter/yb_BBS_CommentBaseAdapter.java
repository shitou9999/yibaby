package com.example.administrator.YiBaby.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.administrator.YiBaby.Entity.Comment;
import com.example.administrator.YiBaby.R;

import java.util.List;

/**
 * Created by Administrator on 2016/1/4.
 */
public class yb_BBS_CommentBaseAdapter extends BaseAdapter {
    private Context context;
    private List<Comment> commentList;
    private static int TYPE_COUNT=2;//有几种形式的布局
    public yb_BBS_CommentBaseAdapter() {
    }
    public yb_BBS_CommentBaseAdapter(Context context, List<Comment> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    @Override
    public int getCount() {
        return commentList.size();
    }

    @Override
    public Object getItem(int position) {
        return commentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder{
        public TextView tvTitle;
        public TextView tvDate;
        public TextView tvSbTitle;
        public ImageView imgRes1;
        public ImageView imgRes2;
        public TextView tvName;
        public RadioButton clickZan;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Comment comment=commentList.get(position);
        int type=commentList.get(position).getStyleType();
        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            switch (type){
                case 0:
                    convertView= LayoutInflater.from(context)
                            .inflate(R.layout.bbs_comment_layout,null);
                    holder.tvTitle=(TextView)convertView.findViewById(R.id.comment2);
                    holder.tvDate=(TextView)convertView.findViewById(R.id.data);
                    holder.tvName=(TextView)convertView.findViewById(R.id.name);
                    holder.imgRes1=(ImageView)convertView.findViewById(R.id.image08);
//                    holder.imgRes2=(ImageView)convertView.findViewById(R.id.imageBaby);
                    holder.clickZan=(RadioButton)convertView.findViewById(R.id.clickZan);
                    break;
                case 1:
                    convertView= LayoutInflater.from(context)
                            .inflate(R.layout.comment02_layout_noneed,null);
                    holder.tvTitle=(TextView)convertView.findViewById(R.id.seeComment);
                    holder.tvDate=(TextView)convertView.findViewById(R.id.data2);
                    holder.tvName=(TextView)convertView.findViewById(R.id.name);
                    holder.tvSbTitle=(TextView)convertView.findViewById(R.id.comment);
                    holder.imgRes1=(ImageView)convertView.findViewById(R.id.image09);
                    holder.clickZan=(RadioButton)convertView.findViewById(R.id.clickZan);
                    break;
            }
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        switch (type){
            case 0:
                holder.tvTitle.setText(comment.getTitle());
                holder.tvDate.setText(comment.getDate());
                holder.tvName.setText(comment.getName());
                holder.imgRes1.setImageResource(comment.getImgRes()[0]);
//                holder.imgRes2.setImageResource(comment.getImgRes()[1]);
                holder.clickZan.setText(comment.getClickZan());//setText只能放String 也可以用setValueOf()转
                break;
            case 1:
                holder.tvTitle.setText(comment.getTitle());
                holder.tvDate.setText(comment.getDate());
                holder.tvName.setText(comment.getName());
                holder.imgRes1.setImageResource(comment.getImgRes()[0]);
                holder.tvSbTitle.setText(comment.getSbTitle());
                holder.clickZan.setText(comment.getClickZan());
                break;
        }
        return convertView;
    }
    @Override
    public int getItemViewType(int position) {
        return commentList.get(position).getStyleType();
    }
    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

}
