package com.nlte.smartcity.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nlte.smartcity.R;
import com.nlte.smartcity.domain.NewsMenuBean;

import java.util.ArrayList;

/**侧边栏适配器
 * Created by Nlte
 * 2016/4/23 0023.
 */
public class LeftMenuAdapter extends BaseAdapter{
    private ArrayList<NewsMenuBean.NewsMenuData> data;
    private Activity activity;
    public int mCurrentItem = 0;//当前被选中的菜单位置

    public LeftMenuAdapter(Activity activity, ArrayList<NewsMenuBean.NewsMenuData> data) {
        this.data = data;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public NewsMenuBean.NewsMenuData getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(activity, R.layout.list_item_left_menu, null);
        TextView tvMenu = (TextView) view.findViewById(R.id.tv_menu);
        NewsMenuBean.NewsMenuData info = getItem(position);
        tvMenu.setText(info.title);
        if (mCurrentItem == position){
            tvMenu.setEnabled(true);
        }else {
            tvMenu.setEnabled(false);
        }
        return view;
    }
}
