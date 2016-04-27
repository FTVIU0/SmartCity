package com.nlte.smartcity.base.impl;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nlte.smartcity.R;
import com.nlte.smartcity.adapter.NewsAdapter;
import com.nlte.smartcity.adapter.TopNewsAdapter;
import com.nlte.smartcity.base.BaseMenuDetailPager;
import com.nlte.smartcity.domain.NewsBean;
import com.nlte.smartcity.domain.NewsMenuBean;
import com.nlte.smartcity.global.GlobalConstants;
import com.nlte.smartcity.utils.CacheUtils;
import com.nlte.smartcity.utils.ToastUtil;
import com.nlte.smartcity.view.HorizonScrollViewPager;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

/**
 * 描述：页签详情页
 *
 * @author NLTE
 * @time 2016/4/24 0024 15:31
 */
public class TabdetailPager extends BaseMenuDetailPager {
    private NewsMenuBean.NewsTabData mNewsTabData;//页签网络数据对象
    private TextView mView;
    @ViewInject(R.id.vp_top_news)
    private HorizonScrollViewPager mVpTopNews;
    @ViewInject(R.id.lv_news)
    private ListView mLvNews;
    @ViewInject(R.id.tv_title)
    private TextView tvTitle;//头条新闻标题
    @ViewInject(R.id.indicator)
    private CirclePageIndicator mCirclePageIndicator;
    private final String mUrl;//页签网络数据URL
    private ArrayList<NewsBean.TopNews> mTopnewsList;//头条新闻数据集合
    private ArrayList<NewsBean.News> mNewsList;//普通新闻数据集合
    private NewsAdapter mNewsAdapter;

    public TabdetailPager(Activity activity, NewsMenuBean.NewsTabData newsTabData) {
        super(activity);
        this.mNewsTabData = newsTabData;
        mUrl = GlobalConstants.SERVER_URL + mNewsTabData.url;
        //initData();
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_tab_detail, null);
        ViewUtils.inject(this, view);

        //加载头条新闻布局
        View headView = View.inflate(mActivity, R.layout.list_header, null);
        //必须将头条布局也注入到xutils中，才能初始化Viewpager对象
        ViewUtils.inject(this, headView);
        //将头条新闻以布局的方式添加到listview中，作为listview的一份子, 以实现上下滑动
        mLvNews.addHeaderView(headView);
        return view;
    }

    @Override
    public void initData() {
        //从服务器获取数据
        //读缓存
        String cache = CacheUtils.getCache(mActivity, mUrl);
        if (!TextUtils.isEmpty(cache)){
            processData(cache);
        }
        getDataFromService();
    }

    //从服务器获取数据
    private void getDataFromService() {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpMethod.GET, mUrl, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;//服务器返回的数据
                //写缓存
                CacheUtils.setCache(mActivity, mUrl, result);
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
    //解析数据
    private void processData(String result) {
        Gson gson = new Gson();
        NewsBean newsBean = gson.fromJson(result, NewsBean.class);
        mTopnewsList = newsBean.data.topnews;

        if (mTopnewsList!=null){
            mVpTopNews.setAdapter(new TopNewsAdapter(mActivity, mTopnewsList));

            //添加小圆点指示器
            mCirclePageIndicator.setViewPager(mVpTopNews);
            mCirclePageIndicator.setSnap(true);

            //初始化第一页头条新闻标题
            tvTitle.setText(mTopnewsList.get(0).title);

            //让圆点设置到第一个页面的位置（indicator在页面销毁后会默认保存上一次选择的状态）
            mCirclePageIndicator.onPageSelected(0);

            //ViewPager监听事件
            mCirclePageIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    //手动更新头条新闻标题
                    NewsBean.TopNews topNews = mTopnewsList.get(position);
                    tvTitle.setText(topNews.title);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            mNewsList = newsBean.data.news;
            if (mNewsList != null){
                mNewsAdapter = new NewsAdapter(mActivity, mNewsList);
                mLvNews.setAdapter(mNewsAdapter);
            }
        }
    }
}
