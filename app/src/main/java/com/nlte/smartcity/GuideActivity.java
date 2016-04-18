package com.nlte.smartcity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nlte.smartcity.adapter.GuideAdapter;
import com.nlte.smartcity.utils.ConstantUtil;
import com.nlte.smartcity.utils.SharePreferenceUtil;

/**
 * 功能描述：新手引导页面
 * @author NLTE
 * @time 2016/4/18 0018 2:17
 */
public class GuideActivity extends AppCompatActivity {

    private ViewPager mGuideVp;
    private LinearLayout mLinLayGuidepointContainer;//圆点容器
    private ImageView mIvGuideRedPoint;//小红点
    private GuideAdapter mGuideAdapter;//适配器
    private int mPointWidth;//两个小灰点的距离
    private Button mBtnStart;//开始体验按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        /*获取控件*/
        //ViewPager
        mGuideVp = (ViewPager)findViewById(R.id.vp_guide_guide);
        //小红点根容器
        mLinLayGuidepointContainer =
                (LinearLayout) findViewById(R.id.linLay_guide_pointContainer);
        //小红点
        mIvGuideRedPoint = (ImageView)findViewById(R.id.iv_guide_redPoint);
        //开始体验按钮
        mBtnStart = (Button)findViewById(R.id.btn_guide_start);

        //获取适配器
        mGuideAdapter = new GuideAdapter(getApplicationContext());
        //设置适配器
        mGuideVp.setAdapter(mGuideAdapter);
        //添加小圆点指示器
        initPoint();

    }
    /*开始体验按钮点击事件*/
    public void btnStartClick(View v){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        //点击开始体验后，设置ISGUIDESHOW为true，表示已经展示过新手引导页面
        SharePreferenceUtil.putBoolean(
                getApplicationContext(), ConstantUtil.ISGUIDESHOW, true);
        finish();
    }
    /*初始化小圆点指示器*/
    private void initPoint(){
        //初始化灰色小圆点
        for (int i=0; i<mGuideAdapter.getImageId().length; i++){
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.shape_point_gray);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            if (i > 0){
                params.leftMargin = 10;//设置左边距
            }
            point.setLayoutParams(params);
            mLinLayGuidepointContainer.addView(point);
        }
        //页面滑动事件
        mGuideVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            // 页面滑动过程中回调
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                int leftMargin = (int) (mPointWidth * positionOffset + mPointWidth * position);
                //重新修改布局参数
                RelativeLayout.LayoutParams params =
                        (RelativeLayout.LayoutParams) mIvGuideRedPoint.getLayoutParams();
                params.leftMargin = leftMargin;
                mIvGuideRedPoint.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                if (position == mGuideAdapter.getImageId().length - 1){
                    mBtnStart.setVisibility(View.VISIBLE);
                }else {
                    //gone:表示不占位置 invisiable：占位置
                    mBtnStart.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        // meaure->layout->draw(必须在onCreate执行结束之后才开始绘制),
        // 所以不能直接在onCreate中获取位置相关信息
        // 监听layout执行结束事件, 结束之后再去获取位置信息,计算圆点间距
        // 获取视图树,hierarchyviewer.bat
        mIvGuideRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    // layout方法执行结束之后,回调此方法
                    @Override
                    public void onGlobalLayout() {
                        mPointWidth = mLinLayGuidepointContainer.getChildAt(1).getLeft() -
                                mLinLayGuidepointContainer.getChildAt(0).getLeft();
                    }
                });
    }
}
