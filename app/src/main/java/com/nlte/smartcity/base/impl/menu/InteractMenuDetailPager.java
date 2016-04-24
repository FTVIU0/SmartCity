package com.nlte.smartcity.base.impl.menu;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.nlte.smartcity.base.BaseMenuDetailPager;

/**菜单详情页-互动
 * Created by Nlte
 * 2016/4/23 0023.
 */
public class InteractMenuDetailPager extends BaseMenuDetailPager{

    @Override
    public View initView() {
        //修改标题
        TextView view = new TextView(mActivity);
        view.setText("菜单详情页-互动");
        view.setTextColor(Color.RED);
        view.setGravity(Gravity.CENTER);
        return view;
    }

    public InteractMenuDetailPager(Activity activity) {
        super(activity);
    }
}
