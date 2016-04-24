package com.nlte.smartcity.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.nlte.smartcity.base.impl.TabdetailPager;

import java.util.ArrayList;

/**
 * 描述：新闻详情页适配器
 * @author NLTE
 * @time 2016/4/24 0024 15:26
 */



public class NewsMenuDetailsAdapter extends PagerAdapter{
    private ArrayList<TabdetailPager> pagers;

    public NewsMenuDetailsAdapter(ArrayList<TabdetailPager> pagers) {
        this.pagers = pagers;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TabdetailPager pager = pagers.get(position);
        View view = pager.rootView;
        pager.initData();//初始化数据
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return pagers.size();
    }
}
