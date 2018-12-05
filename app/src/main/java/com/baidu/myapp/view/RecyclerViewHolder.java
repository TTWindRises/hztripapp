package com.baidu.myapp.view;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by Administrator on 2018/11/29.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> arrayView;
    public RecyclerViewHolder(View itemView) {
        super(itemView);
    }

    /**
     *通过填写的itemId来获取具体的view的对象
     * @param itemId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int itemId) {
        View mView = arrayView.get(itemId);
        return (T)mView;
    }
}
