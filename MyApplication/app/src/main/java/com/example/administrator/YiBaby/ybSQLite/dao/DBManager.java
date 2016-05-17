package com.example.administrator.YiBaby.ybSQLite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.YiBaby.ybEntity.cResultObject;
import com.example.administrator.YiBaby.ybSQLite.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/1.
 */
public class DBManager {
    private DBHelper dbHelper;
    public DBManager(Context context){
        dbHelper=new DBHelper(context);
    }
    /**
     * 插入缓存(Url)
     */
    public void insertData(String url,String data){
        SQLiteDatabase database=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(DBHelper.URL,url);
        values.put(DBHelper.DATA,data);
        database.replace(DBHelper.CACHE,null,values);
        database.close();
    }
    /**
     * 根据url获取缓存数据
     */
    public String getSQLiteData(String url){
        String result="";
        SQLiteDatabase database=dbHelper.getReadableDatabase();
        Cursor cursor=database.rawQuery(" select * from "+DBHelper.CACHE+" where url = ? ",new String[]{url});
        if(cursor.moveToNext()){
            result=cursor.getString(cursor.getColumnIndex(DBHelper.DATA));
        }
        cursor.close();
        database.close();
        return result;
    }
    /**
     * 向表批量插入Milk列表数据
     * @param
     */
    public void addBatchMilk(List<cResultObject> addBatchMilkList,int flag){////////////////////
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.beginTransaction();
        db.execSQL("delete from milkList where _flag=? ",new Integer[]{flag});
        for(cResultObject cResultObject:addBatchMilkList){
            ContentValues values=new ContentValues();
            values.put("_typename",cResultObject.getTypeName());
            values.put("_name",cResultObject.getName());
            values.put("_price",cResultObject.getPrice());
            values.put("_scrop",cResultObject.getScope());
            values.put("_flag",flag);
            db.insert("milkList",null,values);
        }
        db.setTransactionSuccessful();//设置成功状态，没有成功的状态回到初始
        db.endTransaction();
        db.close();
    }
    /**
     * 查询数据
     * @return
     */
     public List<cResultObject> searchMilkSQLite(int flag){
         List<cResultObject> cResultObjectList=new ArrayList<cResultObject>();
         SQLiteDatabase db=dbHelper.getWritableDatabase();
         String sql="select * from milkList where _flag= "+flag;///////////////////////////
         Cursor c=db.rawQuery(sql,null);
         while (c.moveToNext()){
             String name=c.getString(c.getColumnIndex("_name"));
             String typeName=c.getString(c.getColumnIndex("_typename"));
             int price=c.getInt(c.getColumnIndex("_price"));
             String scrop=c.getString(c.getColumnIndex("_scrop"));
//             flag=c.getInt(c.getColumnIndex("_flag"));
             cResultObjectList.add(new cResultObject(typeName,name,price,scrop));
         }
         c.close();
         db.close();
         return cResultObjectList;
     }
}
