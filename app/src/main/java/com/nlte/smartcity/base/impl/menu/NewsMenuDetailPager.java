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

import java.util.ArrayList;

/**菜单详情页-新闻
 * Created by Nlte
 * 2016/4/23 0023.
 */
public class NewsMenuDetailPager extends BaseMenuDetailPager{
    @ViewInject(R.id.vp_news_details)
    private ViewPager mVPNewsMEnuDetails;
    private ArrayList<NewsMenuBean.NewsTabData> mTabList;//页签网络数据
    private ArrayList<TabdetailPager> mPagers;//页签详情页集合

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_news_menu_details, null);
        ViewUtils.inject(this, view);
        /*//修改标题
        TextView view = new TextView(mActivity);
        view.setText("菜单详情页-新闻");
        view.setTextColor(Color.RED);
        view.setGravity(Gravity.CENTER);
        System.out.println("initView");*/
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
            TabdetailPager pager = new TabdetailPager(mActivity);
            mPagers.add(pager);
        }
        NewsMenuDetailsAdapter adapter = new NewsMenuDetailsAdapter(mPagers);
        mVPNewsMEnuDetails.setAdapter(adapter);
    }

    /*class NewsMenuDetailsAdapter extends PagerAdapter {
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TabdetailPager pager = mPagers.get(position);
            View view = pager.rootView;
            //pager.initData();//初始化数据
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return mPagers.size();
        }
    }*/
}
