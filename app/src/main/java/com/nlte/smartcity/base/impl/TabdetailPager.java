package com.nlte.smartcity.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.nlte.smartcity.base.BaseMenuDetailPager;

/**
 * 描述：页签详情页
 *
 * @author NLTE
 * @time 2016/4/24 0024 15:31
 */
public class TabdetailPager extends BaseMenuDetailPager{
    public TabdetailPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        TextView view = new TextView(mActivity);
        view.setText("页签详情页");
        view.setTextColor(Color.RED);
        view.setGravity(Gravity.CENTER);
        return view;
    }
}
