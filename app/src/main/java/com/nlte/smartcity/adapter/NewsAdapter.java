package com.nlte.smartcity.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.nlte.smartcity.R;
import com.nlte.smartcity.domain.NewsBean;
import com.nlte.smartcity.utils.SharePreferenceUtil;

import java.util.ArrayList;

/**
 * 功能描述：普通新闻适配器
 *
 * @author NLTE
 * @time 2016/4/27 0027 10:30
 */
public class NewsAdapter extends BaseAdapter{
    private ArrayList<NewsBean.News> mNewsList;//普通新闻数据集合
    private Activity mActivity;
    private final BitmapUtils mBitmapUtils;

    public NewsAdapter(Activity activity, ArrayList<NewsBean.News> newsList) {
        mActivity = activity;
        mNewsList = newsList;
        mBitmapUtils = new BitmapUtils(activity);
    }

    @Override
    public int getCount() {
        return mNewsList.size();
    }

    @Override
    public Object getItem(int position) {
        return mNewsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = View.inflate(mActivity, R.layout.list_item_news, null);
            holder = new ViewHolder();
            holder.ivIcon = (ImageView)convertView.findViewById(R.id.iv_icon);
            holder.tvTitle = (TextView)convertView.findViewById(R.id.tv_title);
            holder.tvTime = (TextView)convertView.findViewById(R.id.ttv_time);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }

        NewsBean.News news = (NewsBean.News) getItem(position);
        mBitmapUtils.display(holder.ivIcon, news.listimage);
        //设置默认加载图片
        mBitmapUtils.configDefaultLoadingImage(R.drawable.news_pic_default);
        holder.tvTitle.setText(news.title);
        holder.tvTime.setText(news.pubdate);

        //判断已读未读
        String readIds = SharePreferenceUtil.getString(mActivity, "read_ids", "");
        if (readIds.contains(news.id)){
            //标题置灰色
            holder.tvTitle.setTextColor(Color.GRAY);
        }else {
            //标题置黑
            holder.tvTitle.setTextColor(Color.BLACK);
        }
        return convertView;
    }
    static class ViewHolder{
        public ImageView ivIcon;
        public TextView tvTitle;
        public TextView tvTime;
    }
}
