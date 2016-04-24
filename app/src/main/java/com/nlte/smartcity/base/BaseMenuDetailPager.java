package com.nlte.smartcity.base;

import android.app.Activity;
import android.view.View;

/**菜单详情页基类
 * Created by Nlte
 * 2016/4/23 0023.
 */
public abstract class BaseMenuDetailPager {
    public Activity mActivity;
    public View rootView;//当前页面的根部局

    public BaseMenuDetailPager(Activity activity) {
        this.mActivity = activity;
        rootView= initView();
    }
    //初始化页面， 由子类实行
    public abstract View initView();
    //初始化数据
    public void initData(){
        System.out.println("BaseMenuDetailPager");
    }
}
