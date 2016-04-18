package com.nlte.smartcity.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nlte.smartcity.R;

/**
 * GuideActivity 新手引导页面适配器
 * Created by Nlte
 * 2016/4/18 0018.
 */
public class GuideAdapter extends PagerAdapter {
    //图片资源合集
    int[] mImageId = new int[]{R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};
    private Context mContext;

    public GuideAdapter(Context context) {
        super();
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mImageId.length;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        //设置背景
        imageView.setBackgroundResource(mImageId[position]);
        container.addView(imageView);
        return imageView;
    }
}
