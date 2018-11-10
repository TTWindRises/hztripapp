package com.baidu.myapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.baidu.myapp.bean.food.FoodBean;
import com.baidu.myapp.bean.food.FoodStore;

import java.util.List;

import static android.R.attr.data;

/**
 * Created by Administrator on 2018/11/8.
 */

public class FoodBeanAdapter extends BaseAdapter {
    private List<FoodBean> foodBeanList;//数据源
    private LayoutInflater mInflater;//布局装载器对象


    public FoodBeanAdapter(Context context, List<FoodBean> data) {
        this.foodBeanList = data;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return foodBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return foodBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
