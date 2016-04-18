package com.nlte.smartcity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nlte.smartcity.adapter.GuideAdapter;

/**
 * 功能描述：新手引导页面
 * @author NLTE
 * @time 2016/4/18 0018 2:17
 */
public class GuideActivity extends AppCompatActivity {

    private ViewPager mGuideVp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        mGuideVp = (ViewPager)findViewById(R.id.vp_guide_guide);
        //获取适配器
        GuideAdapter guideAdapter = new GuideAdapter(getApplicationContext());
        //设置适配器
        mGuideVp.setAdapter(guideAdapter);
        System.out.println("Test");
    }

}
