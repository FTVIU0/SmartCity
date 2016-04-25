package com.nlte.smartcity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.nlte.smartcity.fragment.ContentFragment;
import com.nlte.smartcity.fragment.LeftMenuFragment;
/**
 * 功能描述：主页面
 * Fragment：
 *  1.写基类BaseFragment
 *  2.实现子类：LeftMenuFragment和ContentFragment,并实现initView()
 *  3.主页面和侧边栏的布局掏空，并替换为FragmentLayout
 *  4.用LeftMenuFragment和ContentFragment替换相对应的页面和侧边栏的布局
 * @author NLTE
 * @time 2016/4/19 0019 17:14
 */
public class HomeActivity extends SlidingFragmentActivity {
    private static final String TAG_LEFT_MENU= "TAG_LEFT_MENU";
    private static final String TAG_CONTENT = "TAG_CONTENT";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setBehindContentView(R.layout.menu_left);//设置左侧滑栏

        SlidingMenu slidingMenu = getSlidingMenu();
        //设置屏幕预留宽度
        slidingMenu.setBehindOffset(400);

        //初始化Fragment
        initFragment();
    }

    /**
     * 初始化Fragment
     */
    private void initFragment(){
        FragmentManager fragmentManager = getFragmentManager();//fragment管理器
        FragmentTransaction transaction = fragmentManager.beginTransaction();//开启事务
        //用fragment替换帧布局，参数1：被替换帧布局的id 参数二：要替换的fragment
        transaction.replace(R.id.fraLay_home_menu, new LeftMenuFragment(), TAG_LEFT_MENU);
        transaction.replace(R.id.fraLay_content, new ContentFragment(), TAG_CONTENT);
        transaction.commit();
    }

    //获取LeftMenuFragment对象
    public LeftMenuFragment getLeftMenuFragment(){
        FragmentManager fm = getFragmentManager();//fragment管理器
        return (LeftMenuFragment)fm.findFragmentByTag(TAG_LEFT_MENU);
    }
    //获取ContentFragment对象
    public ContentFragment getContentFragment(){
        FragmentManager fm = getFragmentManager();//fragment管理器
        return (ContentFragment)fm.findFragmentByTag(TAG_CONTENT);
    }
}
