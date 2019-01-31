package com.baidu.myapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.myapp.MainActivity;
import com.baidu.myapp.R;
import com.baidu.myapp.animate.ShopCartAnimate;
import com.baidu.myapp.bean.food.FoodBean;
import com.baidu.myapp.interfaces.ifood.IFoodBuy;
import com.baidu.myapp.util.Debbuger;
import com.baidu.myapp.util.foodutil.FoodGlideUtil;
import com.baidu.myapp.view.foodview.AddWidget;
import com.baidu.myapp.view.foodview.ZAddWidget;
import com.bumptech.glide.Glide;

import java.math.BigDecimal;
import java.util.List;

import static com.baidu.location.g.j.G;
import static com.baidu.location.g.j.p;

/**
 * Created by Administrator on 2018/11/29.
 */

public class FoodBeanRecyclerAdapter extends RecyclerView.Adapter<FoodBeanRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<FoodBean> data;
    private ZAddWidget.OnAddClick onAddClick;
    public FoodBeanRecyclerAdapter(Context context, List<FoodBean> data, ZAddWidget.OnAddClick onAddClick) {
        this.context = context;
        this.data = data;
        this.onAddClick = onAddClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_horizontal_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        FoodGlideUtil.getInstance().setSquareGlide(context, Integer.valueOf(data.get(position).getFoodImg()), holder.imageView);
        holder.title.setText(data.get(position).getFoodName());
        holder.sales.setText("月销" + data.get(position).getFoodSales());
        holder.original_price.setText("￥" + (CharSequence) data.get(position).getFoodOriginalPrice());
        holder.present_price.setText("￥" + (CharSequence) data.get(position).getFoodPresentPrice());
        holder.original_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        ZAddWidget zAddWidget = holder.zadd;
        zAddWidget.setData(onAddClick,data.get(position));
        if (data.get(position).getFoodNum() > 0) {
            holder.original_price.setVisibility(View.GONE);
        } else {
            holder.original_price.setVisibility(View.VISIBLE);
        }
        Debbuger.LogE("dataNum:"+data.get(position).getFoodNum());
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title;
        TextView sales;
        TextView present_price;
        TextView original_price;
        ZAddWidget zadd;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.food_horizontal_item_img);
            title = itemView.findViewById(R.id.food_horizontal_item_title);
            sales = itemView.findViewById(R.id.food_horizontal_item_sales);
            present_price = itemView.findViewById(R.id.food_present_price);
            original_price = itemView.findViewById(R.id.food_original_price);
            zadd = itemView.findViewById(R.id.horizontal_addwidget);
        }
    }

}
