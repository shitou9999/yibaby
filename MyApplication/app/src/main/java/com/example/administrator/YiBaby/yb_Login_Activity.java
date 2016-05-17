package com.example.administrator.YiBaby;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class yb_Login_Activity extends AppCompatActivity {
    private ImageView returns;
    private WebView webView;
    private ProgressBar progressBar;
    private CookieManager cookieManager;
    private String loginCookie;
//    public static final String ENTRANCE_URL_LOGIN = "http://www.meiyibaby.cn/member/login.htm"; //登陆url
//    public static final String ENTRANCE_URL_HOME = "http://www.meiyibaby.cn/member/"; //登陆后调整的url
    private MyApplication app;
    private SharedPreferences sp;
    private String CookieStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yi_baby__login_);
        returns=(ImageView)super.findViewById(R.id.returns);
        webView= (WebView) super.findViewById(R.id.webView);
        progressBar= (ProgressBar) super.findViewById(R.id.progressBar);
        sp=getSharedPreferences("myData", Context.MODE_PRIVATE);
        app = MyApplication.getInstance();
        initLisener();
        initWebView();
    }
    private void initWebView() {
        final WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webView.addJavascriptInterface(new JavaScriptCall(this), "client");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
                CookieManager cookieManager = CookieManager.getInstance();
                CookieStr = cookieManager.getCookie(url);
                Log.e("jereh", "Cookies = " + CookieStr);
                sp.edit().putString("CookieStr",CookieStr).commit();
            }

            @Override //页面启动的时候
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
//        if(!"".equals(sp.getString("CookieStr",""))){
//            setCookie();
//        }
        webView.loadUrl("http://www.meiyibaby.cn/member");

    }
    private void setCookie(){
            CookieSyncManager.createInstance(this);
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            cookieManager.removeExpiredCookie();
            cookieManager.setCookie("http://www.meiyibaby.cn/member",CookieStr);
            CookieSyncManager.getInstance().sync();
    }
    class JavaScriptCall {
        private AppCompatActivity mActitvty;
        public JavaScriptCall(AppCompatActivity mActitvty) {
            this.mActitvty = mActitvty;
        }

        @JavascriptInterface
        public void clearCookie() {
            sp.edit().putString("userInfo","").commit();
        }

        @JavascriptInterface
        public void getUserInfo(String getUserInfo) {
            if (getUserInfo != null&&!getUserInfo.equals("undefined")) {
//                Gson gson = new Gson();
//                User user = gson.fromJson(getUserInfo, User.class);
                sp.edit().putString("userInfo",getUserInfo).commit();
//                Log.d("jereh", String.valueOf(user));
            }
        }
    }
        private void initLisener() {
            returns.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    yb_Login_Activity.this.finish();
                    overridePendingTransition(R.anim.change_activity_in, R.anim.change_activity_out);
                }
            });
        }

//        CookieManager cookieManager = CookieManager.getInstance();
//        String CookieStr = cookieManager.getCookie(ENTRANCE_URL_HOME); //获取cookie
//        Log.d("TAG", "onCreate cookie:" + CookieStr);
//        if (TextUtils.isEmpty(CookieStr)) {
//            webView.loadUrl(ENTRANCE_URL_LOGIN);
//        } else {
//            webView.loadUrl(ENTRANCE_URL_HOME);
//        }
}
