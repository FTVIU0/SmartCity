package com.nlte.smartcity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.nlte.smartcity.utils.ConstantUtil;
import com.nlte.smartcity.utils.SharePreferenceUtil;

/**
 * 功能描述：第一次启动应用时的闪屏页面
 * @author NLTE
 * @time 2016/4/17 0017 22:55
 */
public class SplashActivity extends AppCompatActivity {

    private RelativeLayout relLaySplashRootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        relLaySplashRootView = (RelativeLayout) findViewById(R.id.relLay_splash_rootView);

        //旋转动画
        RotateAnimation rotateAnimation = new RotateAnimation(
                0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(1000);
        //缩放动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                0, 1,
                0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(1000);
        //渐变动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(2000);

        //动画集合
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);

        //启动动画
        relLaySplashRootView.startAnimation(animationSet);

        //动画监听
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //动画开始
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画结束，进行页面跳转和Splash页面的销毁
                Intent intent = new Intent();
                boolean isGuidShoe = SharePreferenceUtil.getBoolean(
                        getApplicationContext(), ConstantUtil.ISGUIDESHOW, false);
                if (!isGuidShoe){
                    //进入新手引导页面
                    intent.setClass(getApplicationContext(), GuideActivity.class);
                    startActivity(intent);
                }else {
                    //进入到主页
                    intent.setClass(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                }
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //动画重复
            }
        });
    }
}
