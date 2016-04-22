package com.nlte.smartcity.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.nlte.smartcity.base.BasePager;

import java.util.ArrayList;

/**
 * Created by Nlte
 * 2016/4/22 0022.
 */
public class ContentAdapter extends PagerAdapter {
    // 5个标签页集合
    private ArrayList<BasePager> mPagers;

    public ContentAdapter(ArrayList<BasePager> pagers) {
        mPagers = pagers;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BasePager pager = mPagers.get(position);
        //获取当前页面的根布局
        View view = pager.mRrootView;
        container.addView(view);
        pager.initData();//初始化数据
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return mPagers.size();
    }
}
