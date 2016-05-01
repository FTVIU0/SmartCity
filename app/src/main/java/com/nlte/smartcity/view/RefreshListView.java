package com.nlte.smartcity.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nlte.smartcity.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 功能描述：自定义下拉刷新listview
 *
 * @author NLTE
 * @time 2016/4/27 0027 15:00
 */
public class RefreshListView extends ListView implements AdapterView.OnItemClickListener{

    private View mHeaderView;
    private int mStartY = -1;
    private int mHeadViewHeidht;
    private static final int STATE_PULL_TO_REFRESH = 1;//下拉刷新
    private static final int STATE_RELEASE_TO_REFRESH = 2;//松开刷新
    private static final int STATE_REFRESHING = 3;//正在刷新

    private OnRefreshListener mListener;

    private int mCurrentState = STATE_PULL_TO_REFRESH;//当前默认状态
    private TextView mTvTile;
    private TextView mTvTime;
    private ImageView mIvIcon;
    private ProgressBar mPbLoading;
    //箭头动画
    private RotateAnimation mRotateAnimationUp;
    private RotateAnimation mRotateAnimationDown;
    private View mFooterView;
    private int mFooterViewHeight;

    public RefreshListView(Context context) {
        super(context);
        initHeaderView();
        initFooterView();
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initHeaderView();
        initFooterView();
    }

    public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initHeaderView();
        initFooterView();
    }

    //初始化脚布局
    private void initFooterView(){
        mFooterView = View.inflate(getContext(), R.layout.pull_to_refresh_footer, null);
        this.addFooterView(mFooterView);
        //隐藏布局
        mFooterView.measure(0, 0);
        mFooterViewHeight = mFooterView.getMeasuredHeight();
        mFooterView.setPadding(0, - mFooterViewHeight, 0, 0);
    }
    //初始化头布局
    private void initHeaderView() {
        mHeaderView = View.inflate(getContext(), R.layout.pull_to_refresh_header, null);
        this.addHeaderView(mHeaderView);//给listview添加头布局

        mTvTile = (TextView) mHeaderView.findViewById(R.id.tv_title);
        mTvTime = (TextView) mHeaderView.findViewById(R.id.tv_time);
        mIvIcon = (ImageView) mHeaderView.findViewById(R.id.iv_icon);
        mPbLoading = (ProgressBar) mHeaderView.findViewById(R.id.pb_loading);


        //隐藏listview头布局
        //通过设置负的内边距，从而布局往上走
        mHeaderView.measure(0, 0);//手动测量布局宽高，具体宽高由系统决定
        //获取测量后的高度
        mHeadViewHeidht = mHeaderView.getMeasuredHeight();
        mHeaderView.setPadding(0, -mHeadViewHeidht, 0, 0);

        //刷新时间
        setRefreshTime();
        //初始化箭头动画
        initArrowAnim();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (mStartY == -1) {//防止事件被父控件消费掉
                    mStartY = (int) ev.getY();
                }
                int endY = (int) ev.getY();
                int dy = endY - mStartY;

                //如果当前是正在刷新状态，什么都不做
                if (mCurrentState == STATE_REFRESHING){
                    break;
                }

                int firstVisiblePosition = getFirstVisiblePosition();//当前显示的第一个item的位置
                if (dy > 0 && firstVisiblePosition == 0) {//下拉动作在listview的最顶部
                    int paddingTop = dy - mHeadViewHeidht;//计算当前控件的padding值
                    if (paddingTop < 0 && mCurrentState != STATE_PULL_TO_REFRESH){
                        //下拉刷新
                        mCurrentState = STATE_PULL_TO_REFRESH;
                        refreshState();//刷新控件
                    }else if (paddingTop >= 0 && mCurrentState != STATE_RELEASE_TO_REFRESH){
                        //松开刷新
                        mCurrentState = STATE_RELEASE_TO_REFRESH;
                        refreshState();//刷新控件
                    }

                    mHeaderView.setPadding(0, paddingTop, 0, 0);
                    return true;//消费此次事件
                }
                break;
            case MotionEvent.ACTION_UP:
                mStartY = -1;//重新初始化起始位置
                if (mCurrentState == STATE_RELEASE_TO_REFRESH){//如果是松开刷新状态，要切换为正在涮新
                    mCurrentState = STATE_REFRESHING;
                    //完整展示
                    mHeaderView.setPadding(0, 0, 0, 0);
                    refreshState();
                    //回调下拉刷新接口
                    if (mListener != null){
                        mListener.onRefresh();
                    }
                }else if (mCurrentState == STATE_PULL_TO_REFRESH){
                    //隐藏控件
                    mHeaderView.setPadding(0, -mHeadViewHeidht, 0, 0);
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    //根据当前状态刷新控件
    private void refreshState(){
        switch (mCurrentState){
            case STATE_PULL_TO_REFRESH://下拉刷新
                mTvTile.setText("下拉刷新");
                mPbLoading.setVisibility(INVISIBLE);
                mIvIcon.startAnimation(mRotateAnimationDown);
                break;
            case STATE_RELEASE_TO_REFRESH://松开刷新
                mTvTile.setText("松开刷新");
                mPbLoading.setVisibility(INVISIBLE);
                mIvIcon.startAnimation(mRotateAnimationUp);
                break;
            case STATE_REFRESHING://正在刷新
                mTvTile.setText("正在刷新···");
                mPbLoading.setVisibility(VISIBLE);
                //必须先清除动画才能隐藏控件
                mIvIcon.clearAnimation();
                mIvIcon.setVisibility(INVISIBLE);
                break;
            default:
                break;
        }
    }
    //初始化箭头动画
    private void initArrowAnim(){
        //向上旋转
        mRotateAnimationUp = new RotateAnimation(
                0, -180,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateAnimationUp.setDuration(200);
        mRotateAnimationUp.setFillAfter(true);//保持动画结束后的状态
        //向下旋转
        mRotateAnimationDown = new RotateAnimation(
                -180, 0,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateAnimationDown.setDuration(200);
        mRotateAnimationDown.setFillAfter(true);//保持动画结束后的状态


    }

    /*下拉刷新回调接口*/
    public interface OnRefreshListener {
        //下拉刷新回调
        public void onRefresh();
    }

    public void setOnRefreshListener(OnRefreshListener listener){
        mListener = listener;
    }

    //刷新结束
    public void onRefreshComplete(boolean success){
        //隐藏控件
        mHeaderView.setPadding(0, -mHeadViewHeidht, 0, 0);

        //设置为默认的下拉刷新
        mCurrentState = STATE_PULL_TO_REFRESH;
        mTvTile.setText("下拉刷新");
        mPbLoading.setVisibility(INVISIBLE);
        mIvIcon.startAnimation(mRotateAnimationDown);

        if (success){
            //刷新时间
            setRefreshTime();
        }
    }

    private void setRefreshTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//H大写表示24小时制。小写为12小时制
        String time = format.format(new Date());
        mTvTime.setText(time);
    }

    /*偷梁换柱*/
    private OnItemClickListener mItemClickListener;
    @Override
    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
        super.setOnItemClickListener(this);//给当前控件设置点击事件（自己给自己设置点击事件）
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //通知前端界面item被点击事件，回调
        if (mItemClickListener != null){
            //将头布局的个数减掉后回调给前端界面
            mItemClickListener.onItemClick(parent, view, position-getHeaderViewsCount(), id);
        }
    }
}
