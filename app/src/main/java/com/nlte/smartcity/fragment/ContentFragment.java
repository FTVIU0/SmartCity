package com.nlte.smartcity.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nlte.smartcity.R;
import com.nlte.smartcity.adapter.ContentAdapter;
import com.nlte.smartcity.base.BasePager;
import com.nlte.smartcity.base.impl.GovAffairsPager;
import com.nlte.smartcity.base.impl.HomePager;
import com.nlte.smartcity.base.impl.NewsCenterPager;
import com.nlte.smartcity.base.impl.SettingPager;
import com.nlte.smartcity.base.impl.SmartServicePager;

import java.util.ArrayList;

/**主页面布局
 * Created by Nlte
 * 2016/4/19 0019.
 */
public class ContentFragment extends BaseFragment {
    @ViewInject(R.id.vp_home_content)
    private ViewPager mViewPager;
    @ViewInject(R.id.rg_group)
    private RadioGroup rgGroup;

    // 5个标签页集合
    private ArrayList<BasePager> mPagers;
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_content, null);
        ViewUtils.inject(this, view);
        //通过rediobutton的点击切换viewpager
        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_home:
                        //禁止切换时的动画
                        mViewPager.setCurrentItem(0, false);
                        break;
                    case R.id.rb_news:
                        mViewPager.setCurrentItem(1, false);
                        break;
                    case R.id.rb_smart:
                        mViewPager.setCurrentItem(2, false);
                        break;
                    case R.id.rb_gov:
                        mViewPager.setCurrentItem(3, false);
                        break;
                    case R.id.rb_setting:
                        mViewPager.setCurrentItem(4, false);
                        break;
                    default:
                        break;
                }
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPagers.get(position).initData();//只有当前页面被选中后才初始化数据
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return view;
    }
    //初始化数据
    @Override
    public void initData() {
        //初始化5个标签页
        mPagers = new ArrayList<BasePager>();
        mPagers.add(new HomePager(mActivity));//首页
        mPagers.add(new NewsCenterPager(mActivity));//新闻中心
        mPagers.add(new SmartServicePager(mActivity));//智慧服务
        mPagers.add(new GovAffairsPager(mActivity));//政务
        mPagers.add(new SettingPager(mActivity));//设置中心

        //初始化首页数据
        mPagers.get(0).initData();
        //设置适配器
        ContentAdapter adapter = new ContentAdapter(mPagers);
        mViewPager.setAdapter(adapter);
    }

}
