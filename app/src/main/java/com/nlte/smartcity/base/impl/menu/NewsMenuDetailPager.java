package com.nlte.smartcity.base.impl.menu;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nlte.smartcity.R;
import com.nlte.smartcity.adapter.NewsMenuDetailsAdapter;
import com.nlte.smartcity.base.BaseMenuDetailPager;
import com.nlte.smartcity.base.impl.TabdetailPager;
import com.nlte.smartcity.domain.NewsMenuBean;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;

/**菜单详情页-新闻
 *
 * ViewPagerIndicator使用流程：
 *  1. 引入ViewPagerIndicator包
 *  2. 在布局文件中声明自定义控件
 *  3. 仿照sample代码初始化自定义控件,和ViewPager绑定
 *  4. 重写pagerAdapter的getPagerTitle（）方法，返回页面的标题
 *  5. 给HomeActivity加主题样式
 *  6. 基于样式进行修改
 *
 * Created by Nlte
 * 2016/4/23 0023.
 */
public class NewsMenuDetailPager extends BaseMenuDetailPager{
    @ViewInject(R.id.vp_news_details)
    private ViewPager mVPNewsMEnuDetails;
    @ViewInject(R.id.indicator)
    private TabPageIndicator mIndicator;//Tab指示器

    private ArrayList<NewsMenuBean.NewsTabData> mTabList;//页签网络数据
    private ArrayList<TabdetailPager> mPagers;//页签详情页集合

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_news_menu_details, null);
        ViewUtils.inject(this, view);
        return view;
    }

    public NewsMenuDetailPager(Activity activity,
                               ArrayList<NewsMenuBean.NewsTabData> children) {
        super(activity);
        mTabList = children;
        initDate();
    }

    public void initDate(){
        mPagers = new ArrayList<TabdetailPager>();
        //初始化12个页签对象数据
        for (int i = 0; i < mTabList.size(); i++){
            TabdetailPager pager = new TabdetailPager(mActivity, mTabList.get(i));
            mPagers.add(pager);
        }
        NewsMenuDetailsAdapter adapter = new NewsMenuDetailsAdapter(mTabList, mPagers);
        mVPNewsMEnuDetails.setAdapter(adapter);
        //将ViewPager和指针绑定在一起，绑定之前必须保证ViewPager已经设置完成
        mIndicator.setViewPager(mVPNewsMEnuDetails);
    }
}
