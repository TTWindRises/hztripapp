package com.baidu.myapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;

/**
 * Created by Administrator on 2018/11/28.
 */

public class myListView extends ListView {
    private BaseAdapter mSelfAdapter;

    public myListView(Context context) {
        super(context);
    }

    public myListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

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
    @Override
    /**
     * 重写该方法，达到使ListView适应ScrollView的效果
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
