package com.example.administrator.YiBaby.yb_Util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

/**
 * Created by Administrator on 2016/3/3.
 */
public class BitmapUtil_NoNeed {
    //    int screenWidth=context.getResources().getDisplayMetrics().widthPixels; //屏幕的宽度
    public static Bitmap scaleBitmap(Bitmap bitmap,int newWidth){
        int scaleWidth=newWidth;
        float rate=(float)scaleWidth/bitmap.getWidth();
        int scaleHeight= (int) (bitmap.getHeight()*rate);
        Bitmap newBitmap=Bitmap.createScaledBitmap(bitmap, scaleWidth, scaleHeight, true); //对图片进行缩放
        if(bitmap!=null){
            bitmap.recycle(); //销毁原来的图片，
        }
        return newBitmap;
    }
    public static  Bitmap  scaleImage(Bitmap bm, int newWidth){
        if (bm == null){
            return null;
        }
        int width = bm.getWidth();
        int height = bm.getHeight();
        float rate=(float)newWidth/width;
        int scaleWidth = newWidth;
        int scaleHeight =(int) (height*rate);
        Log.d("jereh", scaleHeight + "" + " " + rate);

        Bitmap newbm=Bitmap.createScaledBitmap(bm,scaleWidth,scaleHeight,true);
        if (bm != null & !bm.isRecycled()){
            bm.recycle();//销毁原图片
            bm = null;
        }
        return newbm;
    }
    public static Bitmap toRoundBitmap(Bitmap bitmap){
        if (bitmap == null){
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float roundPx;
        float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
        if (width <= height){
            roundPx = width / 2;
            top = 0;
            bottom = width;
            left = 0;
            right = width;
            height = width;
            dst_left = 0;
            dst_top = 0;
            dst_right = width;
            dst_bottom = width;
        } else{
            roundPx = height / 2;
            float clip = (width - height) / 2;
            left = clip;
            right = width - clip;
            top = 0;
            bottom = height;
            width = height;
            dst_left = 0;
            dst_top = 0;
            dst_right = height;
            dst_bottom = height;
        }

        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect src = new Rect((int) left, (int) top, (int) right,
                (int) bottom);
        final Rect dst = new Rect((int) dst_left, (int) dst_top,
                (int) dst_right, (int) dst_bottom);
        final RectF rectF = new RectF(dst);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, dst, paint);
        if (bitmap != null && !bitmap.isRecycled()){
            bitmap.recycle();
            bitmap = null;
        }
        return output;
    }
}
