package com.nlte.smartcity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nlte.smartcity.adapter.GuideAdapter;

/**
 * 功能描述：新手引导页面
 * @author NLTE
 * @time 2016/4/18 0018 2:17
 */
public class GuideActivity extends AppCompatActivity {

    private ViewPager mGuideVp;
    private LinearLayout mLinLayGuidepointContainer;
    private ImageView mIvGuideRedPoint;//小红点
    private GuideAdapter mGuideAdapter;
    private int mPointWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        //ViewPager
        mGuideVp = (ViewPager)findViewById(R.id.vp_guide_guide);
        //小红点根容器
        mLinLayGuidepointContainer =
                (LinearLayout) findViewById(R.id.linLay_guide_pointContainer);
        //小红点
        mIvGuideRedPoint = (ImageView)findViewById(R.id.iv_guide_redPoint);
        //获取适配器
        mGuideAdapter = new GuideAdapter(getApplicationContext());
        //设置适配器
        mGuideVp.setAdapter(mGuideAdapter);
        //添加小圆点指示器
        initPoint();

    }
    //初始化小圆点指示器
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
