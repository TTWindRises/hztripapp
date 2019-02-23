package com.baidu.myapp.adapter.food;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.myapp.bean.food.FoodBean;
import com.baidu.myapp.util.Debbuger;
import com.baidu.myapp.util.foodutil.Computational;
import com.baidu.myapp.util.foodutil.FoodGlideUtil;
import com.baidu.myapp.R;
import com.baidu.myapp.view.foodview.AddWidget;
import com.baidu.myapp.view.foodview.ZAddWidget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


/**
 * Created by Administrator on 2018/11/29.
 */

public class FoodRightRecyclerAdapter extends BaseQuickAdapter<FoodBean, BaseViewHolder> {
    private Context context;
    private List<FoodBean> data;
    private ZAddWidget.OnAddClick onAddClick;

    public FoodRightRecyclerAdapter(List<FoodBean> data, ZAddWidget.OnAddClick onAddClick) {
        super(R.layout.food_vertical_right_item, data);
        this.data = data;
        this.onAddClick = onAddClick;
    }


    @Override
    protected void convert(BaseViewHolder helper, FoodBean item) {
        double discount = Computational.getInstance().getDiscount(item.getFoodPresentPrice(), item.getFoodOriginalPrice());
        helper.setText(R.id.food_store_vertical_right_title, item.getFoodName())
                .setText(R.id.food_store_vertical_right_praise, item.getFoodPraise())
                .setText(R.id.food_store_vertical_right_discount, discount + "折")
                .setText(R.id.food_vertical_right_original_price, "￥"+item.getFoodOriginalPrice())

                .setText(R.id.food_vertical_right_present_price, "￥"+item.getFoodPresentPrice())
                .setText(R.id.food_store_vertical_right_sale,"月销 "+ item.getFoodSales())
                .setImageResource(R.id.food_store_vertical_right_img, Integer.parseInt(item.getFoodImg())).addOnClickListener(R.id.zaddwidget)
                .addOnClickListener(R.id.food_main2);
        TextView textView = helper.getView(R.id.food_vertical_right_original_price);
        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        ZAddWidget addWidget = helper.getView(R.id.zaddwidget);
//		addWidget.setData(this, helper.getAdapterPosition(), onAddClick);
        addWidget.setData(onAddClick, item);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


}
