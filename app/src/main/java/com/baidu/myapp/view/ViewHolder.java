package com.baidu.myapp.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.myapp.R;

/**
 * Created by Administrator on 2018/11/29.
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView title;
    TextView sales;
    TextView present_price;
    TextView original_price;
    ImageView add;

    public ViewHolder(View itemView) {
        super(itemView);
        imageView =  itemView.findViewById(R.id.food_horizontal_item_img);
        title = itemView.findViewById(R.id.food_horizontal_item_title);
        sales =  itemView.findViewById(R.id.food_horizontal_item_sales);
        present_price =  itemView.findViewById(R.id.food_present_price);
        original_price = itemView.findViewById(R.id.food_original_price);
        add =itemView.findViewById(R.id.food_horizontal_item_add);//

    }
}
