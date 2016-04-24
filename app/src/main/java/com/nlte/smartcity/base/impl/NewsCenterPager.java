package com.nlte.smartcity.base.impl;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.nlte.smartcity.HomeActivity;
import com.nlte.smartcity.base.BaseMenuDetailPager;
import com.nlte.smartcity.base.BasePager;
import com.nlte.smartcity.base.impl.menu.InteractMenuDetailPager;
import com.nlte.smartcity.base.impl.menu.NewsMenuDetailPager;
import com.nlte.smartcity.base.impl.menu.PhotoMenuDetailPager;
import com.nlte.smartcity.base.impl.menu.TopicMenuDetailPager;
import com.nlte.smartcity.domain.NewsMenuBean;
import com.nlte.smartcity.fragment.LeftMenuFragment;
import com.nlte.smartcity.global.GlobalConstants;
import com.nlte.smartcity.utils.CacheUtils;
import com.nlte.smartcity.utils.ToastUtil;

import java.util.ArrayList;

/**首页
 * Created by Nlte
 * 2016/4/22 0022.
 */
public class NewsCenterPager extends BasePager{
    private ArrayList<BaseMenuDetailPager> mMenuDetailPagers;
    private NewsMenuBean mNewsMenuBean;//网络返回数据

    public NewsCenterPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        //请求服务器数据
        String cache = CacheUtils.getCache(mActivity, GlobalConstants.CATEGORY_URL);
        if (!TextUtils.isEmpty(cache)){
            processData(cache);
        }
        //请求服务器数据,读了缓存之后，仍然请求网络，保证信息为最新
        getDataFromServer();

    }
    //请求服务器数据
    private void getDataFromServer() {
        HttpUtils httpUtils = new HttpUtils();
        /*
        * 参数1：请求方式 GET或POST
        * 参数2：接口地址
        * 参数3：回调接口
        * */
        httpUtils.send(
                HttpMethod.GET,
                GlobalConstants.CATEGORY_URL,
                new RequestCallBack<String>() {
                    //访问成功
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String result = responseInfo.result;//服务器返回的数据
                        CacheUtils.setCache(mActivity, GlobalConstants.CATEGORY_URL, result);
                        //解析数据
                        processData(result);
                    }
                    //请求失败
                    @Override
                    public void onFailure(HttpException error, String msg) {
                        error.printStackTrace();
                        ToastUtil.show(mActivity, msg);
                    }
                });
    }
    //Gson解析数据
    private void processData(String result) {
        Gson gson = new Gson();
        mNewsMenuBean = gson.fromJson(result, NewsMenuBean.class);
        System.out.println("结果"+ mNewsMenuBean);

        /*将数据传递给侧边栏
        * 1. 通过NewsCenterPager获取HomeActivity
        * 2. 通过HomeActivity获取LeftMenuFragment
        * 3. 将NewsCenterPager的数据传递给LeftMenuFragment*/
        HomeActivity  mainUI = (HomeActivity)mActivity;
        LeftMenuFragment leftMenuFragment = mainUI.getLeftMenuFragment();
        leftMenuFragment.SetMenuData(mNewsMenuBean.data);

        //初始化菜单详情页
        mMenuDetailPagers = new ArrayList<BaseMenuDetailPager>();
        mMenuDetailPagers.add(new NewsMenuDetailPager(mActivity,
                mNewsMenuBean.data.get(0).children));
        mMenuDetailPagers.add(new TopicMenuDetailPager(mActivity));
        mMenuDetailPagers. add(new PhotoMenuDetailPager(mActivity));
        mMenuDetailPagers.add(new InteractMenuDetailPager(mActivity));
        //默认新闻菜单详情页为默认显示页
        setCurrentMenuDetailPager(0);
    }
    //设置当前菜单详情页
    public void setCurrentMenuDetailPager(int position){
        //获取当前选择的菜单详情页
        BaseMenuDetailPager pager = mMenuDetailPagers.get(position);
        View view = pager.rootView;
        fraLayContent.removeAllViews();//清除之前所有的布局
        fraLayContent.addView(view);//给新闻中心的帧布局添加菜单详情页布局

        //初始化菜单详情页布局
        pager.initData();

        //更新页面的标题
        String title  = mNewsMenuBean.data.get(position).title;
        tvTitle.setText(title);
    }

}
