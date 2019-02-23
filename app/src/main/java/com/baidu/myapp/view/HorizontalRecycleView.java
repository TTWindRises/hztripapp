package com.baidu.myapp.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.BaseAdapter;

import com.baidu.myapp.util.Debbuger;
import com.baidu.myapp.view.foodview.NestRecyclerView;

/**
 * Created by Administrator on 2018/11/29.
 */

public class HorizontalRecycleView extends RecyclerView {
    private int lastVisibleItemPosition;
    private int firstVisibleItemPosition;
    private float mLastY = 0;// 记录上次Y位置
    private boolean isTopToBottom = false;
    private boolean isBottomToTop = false;
    public HorizontalRecycleView(Context context) {
        super(context);
    }

    public HorizontalRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizontalRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    private BaseAdapter mSelfAdapter;

    /**
     * 删除ListView中上一次渲染的View，并添加新View。
     */
    private void buildList() {
        if (mSelfAdapter == null) {

        }

        if (getChildCount() > 0) {
            removeAllViews();
        }

        int count = mSelfAdapter.getCount();

        for(int i = 0 ; i < count ; i++) {
            View view = mSelfAdapter.getView(i, null, null);
            if (view != null) {
                addView(view, i);
            }
        }
    }
  /*  @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastY = event.getY();
                //不允许父View拦截事件
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                float nowY = event.getY();
                isIntercept(nowY);
                if (isBottomToTop||isTopToBottom){
                    getParent().requestDisallowInterceptTouchEvent(false);
                    return false;
                }else{
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                mLastY = nowY;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return super.onTouchEvent(event);
    }

    private void isIntercept(float nowY){

        isTopToBottom = false;
        isBottomToTop = false;

        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            //得到当前界面，最后一个子视图对应的position
            lastVisibleItemPosition = ((GridLayoutManager) layoutManager)
                    .findLastVisibleItemPosition();
            //得到当前界面，第一个子视图的position
            firstVisibleItemPosition = ((GridLayoutManager) layoutManager)
                    .findFirstVisibleItemPosition();
        }

        //得到当前界面可见数据的大小
        int visibleItemCount = layoutManager.getChildCount();
        //得到RecyclerView对应所有数据的大小
        int totalItemCount = layoutManager.getItemCount();
        Debbuger.LogE("nestScrolling"+"onScrollStateChanged");
        if (visibleItemCount>0) {
            if (lastVisibleItemPosition == totalItemCount - 1) {
                //最后视图对应的position等于总数-1时，说明上一次滑动结束时，触底了
                Debbuger.LogE("nestScrolling"+"触底了");
                if (HorizontalRecycleView.this.canScrollVertically(-1) && nowY < mLastY) {
                    // 不能向上滑动
                    Debbuger.LogE("nestScrolling"+"不能向上滑动");
                    isBottomToTop = true;
                } else {
                    Debbuger.LogE("nestScrolling"+"向下滑动");
                }
            } else if (firstVisibleItemPosition == 0) {
                //第一个视图的position等于0，说明上一次滑动结束时，触顶了
                Debbuger.LogE("nestScrolling"+"触顶了");
                if (HorizontalRecycleView.this.canScrollVertically(1) && nowY > mLastY) {
                    // 不能向下滑动
                    Debbuger.LogE("nestScrolling"+"不能向下滑动");
                    isTopToBottom = true;
                } else {
                    Debbuger.LogE("nestScrolling"+"向上滑动");
                }
            }
        }
    }*/

    public BaseAdapter getSelfAdapter() {
        return mSelfAdapter;
    }

    /**
     * 设置Adapter。
     *
     * @param selfAdapter
     */
    public void setSelfAdapter(BaseAdapter selfAdapter) {
        this.mSelfAdapter = selfAdapter;
        buildList();
    }
   /* @Override
    *//**
     * 重写该方法，达到使ListView适应ScrollView的效果
     *//*
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
*/

}
