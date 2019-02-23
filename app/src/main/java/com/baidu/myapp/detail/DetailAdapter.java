package com.baidu.myapp.detail;


import android.support.annotation.Nullable;

import com.baidu.myapp.R;
import com.baidu.myapp.bean.food.FoodBean;
import com.baidu.myapp.view.foodview.AddWidget;
import com.baidu.myapp.view.foodview.ZAddWidget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


class DetailAdapter extends BaseQuickAdapter<FoodBean, BaseViewHolder> {
	private ZAddWidget.OnAddClick onAddClick;

	DetailAdapter(@Nullable List<FoodBean> data, ZAddWidget.OnAddClick onAddClick) {
		super(R.layout.item_detail, data);
		this.onAddClick = onAddClick;
	}

	@Override
	protected void convert(BaseViewHolder helper, FoodBean item) {
		helper.setText(R.id.textView6, item.getFoodName())
				.setText(R.id.textView7, "月售"+item.getFoodSales())
				.setText(R.id.textView8, item.getStrPrice(mContext))
				.setImageResource(R.id.imageView2, Integer.parseInt(item.getFoodImg()))
		;
		ZAddWidget addWidget = helper.getView(R.id.detail_addwidget);
		addWidget.setData(onAddClick,item);
	}
}
