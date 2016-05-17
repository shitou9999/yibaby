package com.example.administrator.YiBaby.ybadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.administrator.YiBaby.CircleImageView;
import com.example.administrator.YiBaby.R;
import com.example.administrator.YiBaby.ybEntity.EnergySigin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/3/31.
 */
public class yb_SigInBaseAdapter extends BaseAdapter{
    private Context context;
    private List<EnergySigin> energySiginList;
    private Date date;
    private SimpleDateFormat sdf;
    public yb_SigInBaseAdapter(Context context, List<EnergySigin> energySiginList) {
        this.context = context;
        this.energySiginList = energySiginList;
        date=new Date();
        sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
    }

    @Override
    public int getCount() {
        return energySiginList.size();
    }

    @Override
    public Object getItem(int position) {
        return energySiginList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder{
        public CircleImageView ivHead;
        public TextView tvName;
        public TextView tvData;
        public TextView tvEnergyExchange;
        public TextView tvCommet;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EnergySigin energySigin=energySiginList.get(position);
        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.yb__sign_in_item,null);
            holder.tvName= (TextView) convertView.findViewById(R.id.tvName);
            holder.tvData= (TextView) convertView.findViewById(R.id.tvData);
            holder.tvEnergyExchange= (TextView) convertView.findViewById(R.id.tvEnergyExchange);
            holder.tvCommet= (TextView) convertView.findViewById(R.id.tvCommet);
            holder.ivHead= (CircleImageView) convertView.findViewById(R.id.ivHead);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        holder.tvName.setText(energySigin.getAddUser());
        holder.tvCommet.setText(energySigin.getContent());
        date.setTime(energySigin.getAddDate());
        holder.tvData.setText(sdf.format(date));
        if(energySigin.getSignInType()==1){
            holder.ivHead.setImageResource(R.mipmap.reg_ch);
        }else if(energySigin.getSignInType()==2){
            holder.ivHead.setImageResource(R.mipmap.reg_fd);
        }else if(energySigin.getSignInType()==3){
            holder.ivHead.setImageResource(R.mipmap.reg_kx);
        }else if(energySigin.getSignInType()==4){
            holder.ivHead.setImageResource(R.mipmap.reg_ng);
        }else if(energySigin.getSignInType()==5){
            holder.ivHead.setImageResource(R.mipmap.reg_nu);
        }else if(energySigin.getSignInType()==6){
            holder.ivHead.setImageResource(R.mipmap.reg_shuai);
        }else if(energySigin.getSignInType()==7){
            holder.ivHead.setImageResource(R.mipmap.reg_wl);
        }else if(energySigin.getSignInType()==8){
            holder.ivHead.setImageResource(R.mipmap.reg_yl);
        }else if(energySigin.getSignInType()==9){
            holder.ivHead.setImageResource(R.mipmap.reg_ym);
        }
        return convertView;
    }
}
