package com.nlte.smartcity.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 功能描述：头条水平滑动
 *
 * @author NLTE
 * @time 2016/4/27 0027 1:12
 */
public class HorizonScrollViewPager extends ViewPager{

    private int mStartX;
    private int mStartY;

    public HorizonScrollViewPager(Context context) {
        super(context);
    }

    public HorizonScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    /*上下滑动，需要父控件拦截*/
    //阻止父控件拦截事件
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        getParent().requestDisallowInterceptTouchEvent(true);
        //根据手势判断
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mStartX = (int)ev.getX();
                mStartY = (int)ev.getY();
                //不要拦截
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                int endX = (int)ev.getX();
                int endY = (int)ev.getY();

                int dx = endX - mStartX;
                int dy = endY - mStartY;

                if (Math.abs(dx) < Math.abs(dy)){
                    //上下滑动，需要父控件拦截
                    getParent().requestDisallowInterceptTouchEvent(false);
                }else {
                    //左右滑动
                    if (dx > 0){//向右边滑动
                        if (getCurrentItem() == 0){
                            //需要拦截
                            getParent().requestDisallowInterceptTouchEvent(false);
                        }
                    }else {//左边滑动
                        int count = getAdapter().getCount();
                        if (getCurrentItem() == count-1){
                            //需要拦截
                            getParent().requestDisallowInterceptTouchEvent(false);
                        }
                    }
                }
        }
        return super.dispatchTouchEvent(ev);
    }
}
