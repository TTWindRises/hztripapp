package com.baidu.myapp.adapter;

import android.support.annotation.Nullable;

import com.baidu.myapp.R;
import com.baidu.myapp.bean.food.FoodBean;
import com.baidu.myapp.view.foodview.AddWidget;
import com.baidu.myapp.view.foodview.ZAddWidget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2019/1/16.
 */


public class CarAdapter extends BaseQuickAdapter<FoodBean, BaseViewHolder> {
    private ZAddWidget.OnAddClick onAddClick;

    public CarAdapter(@Nullable List<FoodBean> data, ZAddWidget.OnAddClick onAddClick) {
        super(R.layout.item_car, data);
        this.onAddClick = onAddClick;
    }

    @Override
    protected void convert(BaseViewHolder helper, FoodBean item) {
        helper.setText(R.id.car_name, item.getFoodName())
                .setText(R.id.car_price, item.getStrPrice(mContext, item.getPrice().multiply(BigDecimal.valueOf(item.getFoodNum()))));
        ZAddWidget addWidget = helper.getView(R.id.car_addwidget);
//		addWidget.setData(this, helper.getAdapterPosition(), onAddClick);
        addWidget.setData(onAddClick,item);
    }
}