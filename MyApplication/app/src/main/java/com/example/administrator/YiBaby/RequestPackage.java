package com.example.administrator.YiBaby;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.administrator.YiBaby.ybEntity.BBS_Comment;
import com.example.administrator.YiBaby.ybEntity.BBS_resultObject;
import com.example.administrator.YiBaby.ybEntity.H_CommentW;
import com.example.administrator.YiBaby.ybEntity.H_resultObjectZ;
import com.example.administrator.YiBaby.ybEntity.cResultObject;
import com.example.administrator.YiBaby.ybHttpURL.ybHttpURL;
import com.example.administrator.YiBaby.ybSQLite.CheckNet;
import com.example.administrator.YiBaby.ybSQLite.dao.DBManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Administrator on 2016/1/27.
 */
public class RequestPackage {
    private MyApplication app;
    private Context context;
    private DBManager dbManager;
    public RequestPackage(MyApplication app,Context context) {
        this.app = app;
        this.context=context;
        dbManager=new DBManager(context);
    }
    /**
     * 主页评论数据
     */
    private callBackMainCommentNetLisener callBackMainCommentNetLisener;
    public interface callBackMainCommentNetLisener{
        void callBackMainCommentNet(List list);
    }

    public void setCallBackMainCommentNet(callBackMainCommentNetLisener callBackMainCommentNetLisener) {
        this.callBackMainCommentNetLisener = callBackMainCommentNetLisener;
    }
    private String url="";
    public void getMainCommentByGet(){
        url=ybHttpURL.LIST_DT;
//        if(app.checkNetWorkConnectionState(context)){
            MyStringRequest request=new MyStringRequest(url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
//                    dbManager.insertData(url,response);
                    Gson gson=new Gson();
                    H_CommentW ybComments=gson.fromJson(response, H_CommentW.class);
                    List<H_resultObjectZ> ybCommentZList=gson.fromJson(ybComments.getResultObject(), new TypeToken<List<H_resultObjectZ>>(){}.getType());
                    callBackMainCommentNetLisener.callBackMainCommentNet(ybCommentZList);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
            app.getRequestQueue().add(request);
//        }else{
//            String data=dbManager.getSQLiteData(url);
//            if(data!=null&&!data.equals("")){
//                Gson gson=new Gson();
//                H_CommentW ybComments=gson.fromJson(data, H_CommentW.class);
//                List<H_resultObjectZ> ybCommentZList=gson.fromJson(ybComments.getResultObject(), new TypeToken<List<H_resultObjectZ>>() {}.getType());
//                callBackMainCommentNetLisener.callBackMainCommentNet(ybCommentZList);
//            }
//        }
    }
    /**
     * 点击Go获取数据
     */
    private List<cResultObject> cResultObjects;
    private DBManager milkListDao;
    private callBacksGetMilkNet callBacksGetMilkNet;
    public interface callBacksGetMilkNet{
        void callBacksGetMilk(List list);
    }
    public void setCallBacksGetMilkNet(callBacksGetMilkNet callBacksGetMilkNet) {
        this.callBacksGetMilkNet = callBacksGetMilkNet;
    }
    public void getCallGoByGet(final int flag){
         String url=ybHttpURL.getNetMilkJsonData(flag);
         milkListDao=new DBManager(context);
         if(CheckNet.checkNetWorkConnectionState(context)){
             MyStringRequest request=new MyStringRequest(url, new Response.Listener<String>() {
                 @Override
                 public void onResponse(String response) {
                     Gson gson=new Gson();
                     H_CommentW ybComment=gson.fromJson(response, H_CommentW.class);//第一层
                     cResultObjects=gson.fromJson(ybComment.getResultObject(), new TypeToken<List<cResultObject>>() {}.getType());
                     callBacksGetMilkNet.callBacksGetMilk(cResultObjects);
                     milkListDao.addBatchMilk(cResultObjects,flag);
                 }
             }, new Response.ErrorListener() {
                 @Override
                 public void onErrorResponse(VolleyError error) {

                 }
             });
             app.getRequestQueue().add(request);
         }else{
             List<cResultObject> cResultObjectList =milkListDao.searchMilkSQLite(flag);//取数据库
             callBacksGetMilkNet.callBacksGetMilk(cResultObjectList);
         }
     }

    /**
     *获取BBS有关的数据
     */
    private callBackBBS_CommentNeaDataLisener callBackBBS_CommentNeaDataLisener;
    public interface callBackBBS_CommentNeaDataLisener{
        void callBackBBS_CommentNeaData(List list,int count);
    }

    public void setCallBackBBS_CommentNeaDataLisener(RequestPackage.callBackBBS_CommentNeaDataLisener callBackBBS_CommentNeaDataLisener) {
        this.callBackBBS_CommentNeaDataLisener = callBackBBS_CommentNeaDataLisener;
    }

    public void getBBS_CommentNetData(int start_page){
        String url="http://www.meiyibaby.cn/appbackend/forum/list.jsp?is_page=1&page_size=10" +
                "&start_page="+start_page+"&sid=25&channel_id=7&user_id=-1&condition=&flag=0";
        MyStringRequest request=new MyStringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                BBS_Comment bbs_comment=gson.fromJson(response, BBS_Comment.class);
                String resultObject = bbs_comment.getResultObject().replaceAll("\"addUserId\":\" \"", "\"addUserId\": 0").replaceAll("\"memberId\":\" \"", "\"memberId\": 0").replaceAll("\"isShow\":\" \"", "\"isShow\":1").replaceAll("\"channelId\":\" \"", "\"channelId\":0").replaceAll("\"commentId\":\" \"", "\"commentId\":0").replaceAll("\"sid\":\" \"", "\"sid\":25");
                List<BBS_resultObject> bbs_resultObjectList=gson.fromJson(resultObject, new TypeToken<List<BBS_resultObject>>() {
                }.getType());
                bbs_resultObjectList.remove(0);
                bbs_resultObjectList.remove(0);
                int count=bbs_comment.getTotalCount();
                callBackBBS_CommentNeaDataLisener.callBackBBS_CommentNeaData(bbs_resultObjectList,count);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        app.getRequestQueue().add(request);
    }

    /**
     * 附近
     */
    private callBackBBS_CommentNeaDataLisener_near callBackBBS_CommentNeaDataLisener_near;
    public interface callBackBBS_CommentNeaDataLisener_near{
        void callBackBBS_CommentNeaData_near(List list);
    }

    public void setCallBackBBS_CommentNeaDataLisener_near(
            RequestPackage.callBackBBS_CommentNeaDataLisener_near callBackBBS_CommentNeaDataLisener_near) {
        this.callBackBBS_CommentNeaDataLisener_near = callBackBBS_CommentNeaDataLisener_near;
    }

    public void getBBS_CommentNetData_near(String url){
        MyStringRequest request=new MyStringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                BBS_Comment bbs_comment=gson.fromJson(response,BBS_Comment.class);
                String resultObject = bbs_comment.getResultObject().replaceAll("\"addUserId\":\" \"", "\"addUserId\": 0").replaceAll("\"memberId\":\" \"", "\"memberId\": 0").replaceAll("\"isShow\":\" \"", "\"isShow\":1").replaceAll("\"channelId\":\" \"", "\"channelId\":0").replaceAll("\"commentId\":\" \"", "\"commentId\":0").replaceAll("\"sid\":\" \"", "\"sid\":25");
                List<BBS_resultObject> bbs_resultObjectList=gson.fromJson(resultObject,
                        new TypeToken<List<BBS_resultObject>>(){}.getType());
                callBackBBS_CommentNeaDataLisener_near.callBackBBS_CommentNeaData_near(bbs_resultObjectList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        app.getRequestQueue().add(request);
    }
}