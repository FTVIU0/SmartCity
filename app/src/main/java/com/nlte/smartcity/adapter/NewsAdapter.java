package com.nlte.smartcity.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.nlte.smartcity.R;
import com.nlte.smartcity.domain.NewsBean;

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
        holder.tvTitle.setText(news.title);
        holder.tvTime.setText(news.pubdate);
        return convertView;
    }
    static class ViewHolder{
        public ImageView ivIcon;
        public TextView tvTitle;
        public TextView tvTime;
    }
}
