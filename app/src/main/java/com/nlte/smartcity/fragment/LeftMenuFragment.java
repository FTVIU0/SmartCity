package com.nlte.smartcity.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nlte.smartcity.R;
import com.nlte.smartcity.adapter.LeftMenuAdapter;
import com.nlte.smartcity.domain.NewsMenuBean;

import java.util.ArrayList;

/**左侧边栏页面
 * Created by Nlte
 * 2016/4/19 0019.
 */
public class LeftMenuFragment extends BaseFragment {
    private ListView lvMenu;
    private LeftMenuAdapter mLeftMenuAdapter;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_left_menu, null);
        lvMenu = (ListView) view.findViewById(R.id.lv_menu);

        return view;
    }
    //设置侧边栏数据, 供NewsCenterPager调用
    public void SetMenuData(ArrayList<NewsMenuBean.NewsMenuData> data){
        mLeftMenuAdapter = new LeftMenuAdapter(mActivity, data);
        lvMenu.setAdapter(mLeftMenuAdapter);
        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mLeftMenuAdapter.mCurrentItem = position;
                mLeftMenuAdapter.notifyDataSetChanged();
            }
        });
    }
}
