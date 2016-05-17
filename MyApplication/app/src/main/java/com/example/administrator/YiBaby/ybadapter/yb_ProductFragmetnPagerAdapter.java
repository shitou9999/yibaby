package com.example.administrator.YiBaby.ybadapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/3.
 */
 public class yb_ProductFragmetnPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    public yb_ProductFragmetnPagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentList=new ArrayList<Fragment>();
    }
    public void addFragment(Fragment fragment){
        fragmentList.add(fragment);
    }
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
