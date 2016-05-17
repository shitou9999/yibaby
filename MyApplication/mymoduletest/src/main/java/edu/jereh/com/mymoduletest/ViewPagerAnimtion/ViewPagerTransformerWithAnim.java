package edu.jereh.com.mymoduletest.ViewPagerAnimtion;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义的ViewPager(动画)
 */
public class ViewPagerTransformerWithAnim extends ViewPager {
    private View mLeft;
    private View mRight;
    private float mTrans;
    private float mScale;
    private static  final  float MIN_SCALE=0.5f;
//    position->view
//    0~1
//    position , position +1
//    1~0
//    position , position + 1
    private Map<Integer,View> mChildren=new HashMap<Integer,View>();
    public void setViewForPosition(View view,int postion){
        mChildren.put(postion,view);//在适配器中调这个方法
    }
    public void removeViewFromPostion(Integer positon){
        mChildren.remove(positon);
    }
    public ViewPagerTransformerWithAnim(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public ViewPagerTransformerWithAnim(Context context){
        super(context);
    }
    @Override  //offset 设置动画
    protected void onPageScrolled(int position, float offset, int offsetPixels) {
        Log.d("jereh","position="+position+"offset="+offset);
//        0-1  position=0 offset=0-1
//        1-0  position=0 offset=1-0
        mLeft=mChildren.get(position);
        mRight=mChildren.get(position+1);
        animStack(mLeft,mRight,offset,offsetPixels);
        super.onPageScrolled(position, offset, offsetPixels);
    }
    private void animStack(View left,View right,float offset,int offsetPixels){
        if(right!=null){
            //从0-1页 offset0-1
            mScale=(1-MIN_SCALE)*offset+MIN_SCALE;
            mTrans=-getWidth()-getPageMargin()+offsetPixels;
            ViewHelper.setScaleX(right,mScale);
            ViewHelper.setScaleY(right,mScale);
            ViewHelper.setTranslationX(right,mScale);
        }
        if(left!=null){
            left.bringToFront();//保证始终在它上面
        }

    }
}