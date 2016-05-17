package com.example.administrator.YiBaby;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.administrator.YiBaby.ybadapter.yb_ImageBaseAdapter_NoNeed;

public class yb_QQ_Head_Activity extends AppCompatActivity {
    private yb_ImageBaseAdapter_NoNeed adapter;
    private Integer[] integers;
    private GridView vip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qq__head_);
        vip= (GridView) super.findViewById(R.id.vip);
        initView();
        initGridViewLisener();
    }

    private void initView() {
        integers= new Integer[]{
                R.mipmap.h001, R.mipmap.h002,R.mipmap.h003, R.mipmap.h004,
                R.mipmap.h005, R.mipmap.h006,R.mipmap.h007, R.mipmap.h008,
                R.mipmap.h009, R.mipmap.h010,R.mipmap.h011, R.mipmap.h012,
                R.mipmap.h013, R.mipmap.h014,R.mipmap.h015, R.mipmap.h016,
                R.mipmap.h017, R.mipmap.h018,R.mipmap.h019, R.mipmap.h020,
                R.mipmap.h021, R.mipmap.h022,R.mipmap.h023, R.mipmap.h024,
                R.mipmap.h025, R.mipmap.h026,R.mipmap.h027, R.mipmap.h028,
                R.mipmap.h029, R.mipmap.h030,R.mipmap.h031, R.mipmap.h032,
                R.mipmap.h031, R.mipmap.h034,R.mipmap.h035, R.mipmap.h036,
                R.mipmap.h037, R.mipmap.h038,R.mipmap.h039, R.mipmap.h040,
                R.mipmap.h041, R.mipmap.h042,R.mipmap.h043, R.mipmap.h044,
                R.mipmap.h045, R.mipmap.h046,R.mipmap.h047, R.mipmap.h048,
                R.mipmap.h049, R.mipmap.h050,R.mipmap.h051, R.mipmap.h052,
                R.mipmap.h053, R.mipmap.h054,R.mipmap.h055, R.mipmap.h056,
                R.mipmap.h057, R.mipmap.h058,R.mipmap.h059, R.mipmap.h060,
                R.mipmap.h061, R.mipmap.h062,R.mipmap.h063, R.mipmap.h064,
                R.mipmap.h065, R.mipmap.h066,R.mipmap.h067, R.mipmap.h068,
                R.mipmap.h069, R.mipmap.h070,R.mipmap.h071, R.mipmap.h072,
                R.mipmap.h073, R.mipmap.h074,R.mipmap.h075, R.mipmap.h076,
                R.mipmap.h077, R.mipmap.h078,R.mipmap.h079, R.mipmap.h080,
                R.mipmap.h081, R.mipmap.h082,R.mipmap.h083, R.mipmap.h084,
                R.mipmap.h085, R.mipmap.h086,R.mipmap.h087, R.mipmap.h088,
                R.mipmap.h089, R.mipmap.h090
        };
        adapter=new yb_ImageBaseAdapter_NoNeed(this,integers);
        vip.setAdapter(adapter);
    }
    private void initGridViewLisener() {
        vip.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.putExtra("head",integers[position]);
                setResult(1,intent);
                finish();
            }
        });
    }
}
