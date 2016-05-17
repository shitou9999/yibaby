package com.example.administrator.YiBaby.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.YiBaby.DBUtil.DBUtil_YiBaby;
import com.example.administrator.YiBaby.Entity.Product;
import com.example.administrator.YiBaby.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/3.
 */
public class YiBaby_ProductPagerAdapter extends PagerAdapter{
    private Context context;
    private List<Product> productList;

    public YiBaby_ProductPagerAdapter() {
    }

    public YiBaby_ProductPagerAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Product product=productList.get(position);
        PullToRefreshListViewConfig fig=new PullToRefreshListViewConfig();
        container.addView(fig.getView());
        return fig.getView();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
    private   class PullToRefreshListViewConfig{  //Config配置，布局；显示配置信息命令
        private YiBaby_ProductBaseAdapter adapter;
        private PullToRefreshListView lvNews;
        private List<Product> newsList;
        public PullToRefreshListViewConfig(){
            init();
        }
        private void init(){  //刷新模式
            newsList=new ArrayList<Product>();
            lvNews=new PullToRefreshListView(context);
            lvNews.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT));
            lvNews.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
            adapter=new YiBaby_ProductBaseAdapter(context,newsList);
            lvNews.setAdapter(adapter);
            lvNews.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                @Override
                public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            load();
                        }
                    },1000);
                }
            });
            lvNews.setRefreshing();

        }
        private Handler handler=new Handler(){

            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 1:
                        adapter.notifyDataSetChanged();
                        lvNews.onRefreshComplete();
                        break;
                }
            }
        };

        private void load(){
            newsList.clear();
            new Thread(new Runnable() {
                @Override
                public void run() {

                            newsList.addAll(DBUtil_YiBaby.getProductData());
                            handler.sendEmptyMessage(1);


                }
            }).start();
        }
        public PullToRefreshListView getView(){
            return lvNews;
        }

    }

}
