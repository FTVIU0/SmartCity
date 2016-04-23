package com.nlte.smartcity.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.nlte.smartcity.base.BasePager;
import com.nlte.smartcity.domain.NewsMenuBean;
import com.nlte.smartcity.global.GlobalConstants;
import com.nlte.smartcity.utils.ToastUtil;

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

        //请求服务器数据
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
                        System.out.println("请求结果 "+ result);
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
        NewsMenuBean newsMenuBean = gson.fromJson(result, NewsMenuBean.class);
        System.out.println("结果");
    }

}
