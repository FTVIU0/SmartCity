package com.nlte.smartcity.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.nlte.smartcity.base.BasePager;

/**首页
 * Created by Nlte
 * 2016/4/22 0022.
 */
public class HomePager extends BasePager{
    public HomePager(Activity activity) {
        super(activity);
    }
    @Override
    public void initData() {
        //隐藏菜单按钮
        ibMenu.setVisibility(View.GONE);
        //修改标题
        tvTitle.setText("SmartCity");
        TextView view = new TextView(mActivity);
        view.setText("首页");
        view.setTextColor(Color.RED);
        view.setGravity(Gravity.CENTER);
        fraLayContent.addView(view);

    }

}
