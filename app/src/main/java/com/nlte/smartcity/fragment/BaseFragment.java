package com.nlte.smartcity.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Nlte
 * 2016/4/19 0019.
 */
public abstract class BaseFragment extends Fragment {

    public Activity mActivity;

    //Fragment创建
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();//获取Fragment所依赖的Activity对象
    }

    //Fragment布局初始化
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = initView();
        return view;
    }


    //Fragment所在的Activity创建完成后被调用
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化数据
        initData();
    }
    //初始化数据
    public void initData() {
    }

    //初始化布局，子类必须实现
    public abstract View initView();
}
