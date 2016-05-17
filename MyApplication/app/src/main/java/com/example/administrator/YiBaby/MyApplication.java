package com.example.administrator.YiBaby;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.administrator.YiBaby.ybEntity.User;

/**
 * Created by Administrator on 2016/1/14. 自定义全局
 */
public class MyApplication extends Application {
    private RequestQueue queue;
    private ImageLoader imageLoader;
    private static MyApplication instance;
    private User user;

    /**
     * 系统构建这个的时候会调用这个方法
     */
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        queue= Volley.newRequestQueue(this);
        imageLoader=new ImageLoader(queue,new BitmapCache());//创建一块缓存区域
    }
    public RequestQueue getRequestQueue(){
        return queue;
    }
    public ImageLoader getImageLoader(){
        return imageLoader;
    }
    private class BitmapCache implements ImageLoader.ImageCache{//内部类    缓存
        private LruCache<String,Bitmap> cache; //类似于集合,管理缓存
        public BitmapCache(){
            int cacheSize= (int) (Runtime.getRuntime().maxMemory()/1024/8);//取设备运行时最大内存的1/8作为缓存的大小
            cache=new LruCache<String,Bitmap>(cacheSize);
        }
        @Override  //请求先取缓存数据，如果取得不再访问网络
        public Bitmap getBitmap(String url) {
            return cache.get(url);
        }
        @Override //请求网络图片，先存到缓存中
        public void putBitmap(String url, Bitmap bitmap) {
            cache.put(url,bitmap);
        }
    }
    public static MyApplication getInstance(){
        return instance;
    }
    public static boolean checkNetWorkConnectionState(Context context){
        ConnectivityManager connMgr=(ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connMgr.getActiveNetworkInfo();
        if(networkInfo!=null&&networkInfo.isConnected()){
            return true;
        }else {
            return false;
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
