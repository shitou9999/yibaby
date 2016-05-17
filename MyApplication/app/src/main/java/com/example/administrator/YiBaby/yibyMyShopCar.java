package com.example.administrator.YiBaby;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class yibyMyShopCar extends AppCompatActivity {
    private ImageView returns;
    private WebView webView;
    private ProgressBar progressBar;
    private MyApplication app;
    private SharedPreferences sp;
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
        webView.addJavascriptInterface(new JavaScriptCallTest(this), "client");
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
                String CookieStr = cookieManager.getCookie(url);
                Log.e("jereh", "Cookies = " + CookieStr);
//                sp.edit().putString("CookieStr",CookieStr).commit();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
        webView.loadUrl("http://www.meiyibaby.cn/member/cart.htm");
    }
    private void initLisener() {
        returns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yibyMyShopCar.this.finish();
                overridePendingTransition(R.anim.change_activity_in, R.anim.change_activity_out);
            }
        });
    }
}
