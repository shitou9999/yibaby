package edu.jereh.com.mymoduletest.ViewPagerAnimtion;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import edu.jereh.com.mymoduletest.R;

public class ViewPagerAnim2 extends AppCompatActivity {
    private ViewPagerTransformerWithAnim vip;
    private int[] imgIds=new int[]{R.mipmap.guide_image1,R.mipmap.guide_image2,R.mipmap.guide_image3};
    private List<ImageView> imgRes=new ArrayList<ImageView>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.ch1_view_pager_anim2);
        vip= (ViewPagerTransformerWithAnim) findViewById(R.id.vip);
        initViewPager();
    }
    private void initViewPager() {
        vip.setAdapter(new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView=new ImageView(ViewPagerAnim2.this);
                imageView.setImageResource(imgIds[position]);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                container.addView(imageView);
                imgRes.add(imageView);
                vip.setViewForPosition(imageView,position);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(imgRes.get(position));
                vip.removeViewFromPostion(position);
            }

            @Override
            public int getCount() {
                return imgIds.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }
        });
    }
}
