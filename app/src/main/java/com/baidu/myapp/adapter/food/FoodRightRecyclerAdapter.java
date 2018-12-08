package com.baidu.myapp.adapter.food;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.myapp.R;
import com.baidu.myapp.bean.food.FoodBean;
import com.baidu.myapp.bean.food.FoodCategory;
import com.baidu.myapp.interfaces.ifood.IFoodBuy;
import com.baidu.myapp.util.Debbuger;

import java.util.List;

import static com.baidu.location.g.j.D;

/**
 * Created by Administrator on 2018/11/29.
 */

public class FoodRightRecyclerAdapter extends RecyclerView.Adapter<FoodRightRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<FoodBean> data;
    private int number = 0;

    public FoodRightRecyclerAdapter(Context context, List<FoodBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_vertical_right_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {




    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {



        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

}
