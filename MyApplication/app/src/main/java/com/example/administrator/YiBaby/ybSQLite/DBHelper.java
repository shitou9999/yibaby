package com.example.administrator.YiBaby.ybSQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/2/28.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static int DATABASE_VERSION = 6;
    public static final String DB_NAME = "MilkList.db";
    public static final String MILK_TABLE_NAME = "milkList";
    public static final String MILK_COLUMN_ID="_id";
    public static final String MILK_COLUMN_NAME="_name";
    public static final String MILK_COLUMN_PRICE="_price";
    public static final String MILK_COLUMN_SCROP="_scrop";
    public static final String MILK_COLUMN_TYPENAME="_typename";
    public static final String MILK_COLUMN_FLAG="_flag";

    public static final String CACHE = "cache";
    public static final String URL = "url";
    public static final String DATA = "data";
    public DBHelper(Context context,String name,int version){
        super(context,name,null,version);
        DATABASE_VERSION=version;
    }
    public DBHelper(Context context){  //不指定调超类的
        super(context,DB_NAME,null,DATABASE_VERSION);
    }
    //一次创建其他表
    //插入一些初始数据
    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuffer sb=new StringBuffer();
        sb.append("create table if not exists ");
        sb.append(MILK_TABLE_NAME);
        sb.append("(").append(MILK_COLUMN_ID + " integer not null primary key autoincrement, ");
        sb.append(MILK_COLUMN_NAME + " verchar(50) not null, ");
        sb.append(MILK_COLUMN_TYPENAME + " verchar(50), ");
        sb.append(MILK_COLUMN_PRICE + " float, ");
        sb.append(MILK_COLUMN_SCROP + " verchar(100), ");
        sb.append(MILK_COLUMN_FLAG+" integer ");
        sb.append(")");
        db.execSQL(sb.toString());
        //一次创建其他表
        //插入一些初始数据
        String sql = "CREATE TABLE IF NOT EXISTS "
                + CACHE + " ("
                + URL + " TEXT PRIMARY KEY, "
                + DATA + " TEXT)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql="drop table if exists "+MILK_TABLE_NAME;
        String sql2="drop table if exists "+CACHE;
        db.execSQL(sql);
        db.execSQL(sql2);
        onCreate(db);
    }
}
