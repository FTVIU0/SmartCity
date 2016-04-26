package com.nlte.smartcity.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lidroid.xutils.BitmapUtils;
import com.nlte.smartcity.domain.NewsBean;

import java.util.ArrayList;

/**
 * 功能描述：头条新闻适配器
 *
 * @author NLTE
 * @time 2016/4/26 0026 20:47
 */
public class TopNewsAdapter extends PagerAdapter{
    private ArrayList<NewsBean.TopNews> mTopnewsList;
    private Activity mActivity;
    private final BitmapUtils mBitmapUtils;//Xutils提供的专门加载网络图片的工具类

    public TopNewsAdapter(Activity activity, ArrayList<NewsBean.TopNews> topnewsList) {
        mTopnewsList = topnewsList;
        mActivity = activity;
        mBitmapUtils = new BitmapUtils(activity);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView view = new ImageView(mActivity);
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        /*1. 根据图片url下载图片。用Xutils提供的BitMapUtils工具类
        * 2. 将图片设置给ImageView
        * 3. 图片缓存：以图片Url为文件名，图片为内容进行缓存（一般图片最好设置一个缓存）XUtils已经做好了
        * 4. 解决内存溢出问题*/
        /*参数一：要设置图片的容器
        * 参数二：图片的下载链接*/
        mBitmapUtils.display(view, mTopnewsList.get(position).topimage);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return mTopnewsList.size();
    }
}
