package com.nlte.smartcity.fragment;

import android.view.View;

import com.nlte.smartcity.R;

/**左侧边栏页面
 * Created by Nlte
 * 2016/4/19 0019.
 */
public class LeftMenuFragment extends BaseFragment {
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_left_menu, null);
        return view;
    }
}
