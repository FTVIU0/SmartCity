package com.nlte.smartcity.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.nlte.smartcity.base.BaseMenuDetailPager;
import com.nlte.smartcity.domain.NewsMenuBean;

/**
 * 描述：页签详情页
 *
 * @author NLTE
 * @time 2016/4/24 0024 15:31
 */
public class TabdetailPager extends BaseMenuDetailPager{
    private NewsMenuBean.NewsTabData mNewsTabData;//页签网络数据对象
    private TextView mView;

    public TabdetailPager(Activity activity, NewsMenuBean.NewsTabData newsTabData) {
        super(activity);
        this.mNewsTabData = newsTabData;
    }


    @Override
    public View initView() {
        mView = new TextView(mActivity);
        mView.setText("页签详情页");
        mView.setTextColor(Color.RED);
        mView.setGravity(Gravity.CENTER);
        return mView;
    }

    @Override
    public void initData() {
        mView.setText(mNewsTabData.title);
    }
}
