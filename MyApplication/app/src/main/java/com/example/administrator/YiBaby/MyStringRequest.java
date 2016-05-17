package com.example.administrator.YiBaby;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/1/13.
 */
public class MyStringRequest extends StringRequest { //封装起来
    private Map<String, String> params;
    private Map<String, String> headers;
    public MyStringRequest(String url,Map<String, String> params,
                           Response.Listener<String> listener,
                           Response.ErrorListener errorListener) {
        super(Method.POST,url, listener, errorListener);
        this.params=params;
    }
    public MyStringRequest(String url, Map<String, String> params,String apikey,
                       Response.Listener<String> listener,
                       Response.ErrorListener errorListener) {
        super(Method.POST,url, listener, errorListener);
        this.params=params;
        headers=new HashMap<String,String>();
        headers.put("apikey",apikey);
    }
    public MyStringRequest(String url,
                           Response.Listener<String> listener,
                           Response.ErrorListener errorListener) {
        super(Method.GET,url, listener, errorListener);
        this.params=params;
    }
    public MyStringRequest(String url,String apikey,
                           Response.Listener<String> listener,
                           Response.ErrorListener errorListener) {
        super(Method.GET,url, listener, errorListener);
        headers=new HashMap<String,String>();
        headers.put("apikey",apikey);
    }
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers!=null?headers:super.getHeaders();
    }
    @Override
    protected Map<String, String> getParams() throws AuthFailureError { //Post传参，，头部附加信息重写这个方法
        return params!=null?params:super.getParams();
    }
//    protected Map<String, String> getParams() throws AuthFailureError {
//        return params;
//    }
}
