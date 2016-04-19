package com.nlte.smartcity.fragment;

import android.view.View;

import com.nlte.smartcity.R;

/**主页面布局
 * Created by Nlte
 * 2016/4/19 0019.
 */
public class ContentFragment extends BaseFragment {
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_content, null);
        return view;
    }
}
