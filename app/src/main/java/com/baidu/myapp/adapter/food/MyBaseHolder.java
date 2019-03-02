package com.baidu.myapp.adapter.food;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.ImageView;

import com.baidu.myapp.util.foodutil.FoodGlideUtil;
import com.chad.library.adapter.base.BaseViewHolder;



/**
 * Created by Administrator on 2019/2/26.
 */

public class MyBaseHolder extends BaseViewHolder {
    Context context;
    public MyBaseHolder(View view, Context context) {
        super(view);
        this.context = context;
    }

    @Override
    public BaseViewHolder setImageResource(@IdRes int viewId, @DrawableRes int imageResId) {

        ImageView view =getView(viewId);
        FoodGlideUtil.getInstance().setSquareGlide(context,imageResId,view);
        return super.setImageResource(viewId, imageResId);
    }
}
