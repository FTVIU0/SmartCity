package com.nlte.smartcity.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.nlte.smartcity.base.BasePager;

/**首页
 * Created by Nlte
 * 2016/4/22 0022.
 */
public class SmartServicePager extends BasePager{
    public SmartServicePager(Activity activity) {
        super(activity);
    }
    @Override
    public void initData() {
        //修改标题
        tvTitle.setText("SmartService");
        TextView view = new TextView(mActivity);
        view.setText("智慧服务");
        view.setTextColor(Color.RED);
        view.setGravity(Gravity.CENTER);
        fraLayContent.addView(view);

    }

}
