package com.nlte.smartcity.base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.nlte.smartcity.HomeActivity;
import com.nlte.smartcity.R;

/**五个标签页的基类
 * Created by Nlte
 * 2016/4/20 0020.
 */
public abstract class BasePager {
    public Activity mActivity;
    public TextView tvTitle;
    public FrameLayout fraLayContent;
    public ImageButton ibMenu;
    public View mRrootView;//页面的根布局
    public BasePager(Activity activity) {
        mActivity = activity;
        mRrootView = initView();
    }
    //初始化布局
    public View initView() {
        View view = View.inflate(mActivity, R.layout.base_pager, null);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);//标题栏
        fraLayContent = (FrameLayout) view.findViewById(R.id.fraLay_home_content);//内容区域
        ibMenu = (ImageButton) view.findViewById(R.id.ib_menu);//menu菜单

        ibMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
            }
        });
        return view;
    }
    /*点击后收起侧边栏*/
    private void toggle() {
        HomeActivity activity = (HomeActivity) mActivity;
        SlidingMenu slidingMenu = activity.getSlidingMenu();
        slidingMenu.toggle();//如果侧边栏为打开状态则关闭侧边栏；否则打开
    }
    //初始化数据，必须由子类实现
    public abstract void initData();
}
