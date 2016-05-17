package com.example.administrator.YiBaby;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.YiBaby.fragment.YiBaby_ProductFragment2s;
import com.example.administrator.YiBaby.ybadapter.yb_ProductFragmetnPagerAdapter;

public class yb_Go_Milk_Fg_Ms extends AppCompatActivity {
    private ImageView returns;
    private CheckBox img;
    private RadioGroup rGoup01;
    private LinearLayout layout;
    private NoSlideViewPager vip;
    private yb_ProductFragmetnPagerAdapter adapter;
    private RadioButton bt1;
    private RadioButton bt2;
    private int[] flag ={0,1,2,3,4,5};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go__milk);
        vip=(NoSlideViewPager)super.findViewById(R.id.vip);
        returns=(ImageView)super.findViewById(R.id.returns);
        img=(CheckBox)super.findViewById(R.id.img);
        layout=(LinearLayout)super.findViewById(R.id.layout);
        rGoup01=(RadioGroup)super.findViewById(R.id.rGoup01);
        bt1=(RadioButton)super.findViewById(R.id.bt1);
        bt2=(RadioButton)super.findViewById(R.id.bt2);
        initViewGroup();
        initBarsListener();
        initView();
    }
    private void initView(){
        adapter=new yb_ProductFragmetnPagerAdapter(getSupportFragmentManager());
        for(int i=0;i<flag.length;i++){
             adapter.addFragment(YiBaby_ProductFragment2s.newInstance(flag[i]));
        }
        vip.setAdapter(adapter);
    }
    private void initViewGroup(){
        rGoup01.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.bt1:
                        vip.setCurrentItem(0);
                        break;
                    case R.id.bt2:
                        vip.setCurrentItem(1);
                        break;
                    case R.id.bt3:
                        vip.setCurrentItem(2);
                        break;
                    case R.id.bt4:
                        vip.setCurrentItem(3);
                        break;
                    case R.id.bt5:
                        vip.setCurrentItem(4);
                        break;
                    case R.id.bt6:
                        vip.setCurrentItem(5);
                        break;
                }
            }
        });
    }

    private void initBarsListener(){
        returns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yb_Go_Milk_Fg_Ms.this.finish();
                overridePendingTransition(R.anim.change_activity_in, R.anim.change_activity_out);
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (img.isChecked()) {
                    layout.setVisibility(View.GONE);
                } else {
                    layout.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
