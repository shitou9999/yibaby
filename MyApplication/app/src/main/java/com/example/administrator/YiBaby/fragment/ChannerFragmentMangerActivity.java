package com.example.administrator.YiBaby.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.YiBaby.R;
import com.example.administrator.YiBaby.adapter.YiBaby_ProductFragmetnPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChannerFragmentMangerActivity extends AppCompatActivity {
    private ViewPager vip;
    private YiBaby_ProductFragmetnPagerAdapter adapter;
    private List<Fragment> fragmentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yi_baby__comment_bbs);
        vip=(ViewPager)super.findViewById(R.id.vip);
        initViewPager();
    }
    private void initViewPager(){
        fragmentList=new ArrayList<Fragment>();
        YiBaby_ProductFragment fragment=null;
        fragment=new YiBaby_ProductFragment();
        fragmentList.add(fragment);
        adapter=new YiBaby_ProductFragmetnPagerAdapter(super.getSupportFragmentManager(),fragmentList);
        vip.setAdapter(adapter);
    }
}
