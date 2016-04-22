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
public class NewsCenterPager extends BasePager{
    public NewsCenterPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        //修改标题
        tvTitle.setText("News");
        TextView view = new TextView(mActivity);
        view.setText("新闻中心");
        view.setTextColor(Color.RED);
        view.setGravity(Gravity.CENTER);
        fraLayContent.addView(view);

    }

}
