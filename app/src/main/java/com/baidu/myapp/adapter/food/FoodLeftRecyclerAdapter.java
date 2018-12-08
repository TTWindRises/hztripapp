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
import com.baidu.myapp.bean.food.FoodCategory;
import com.baidu.myapp.interfaces.ifood.IFoodBuy;
import com.baidu.myapp.util.Debbuger;

import java.util.List;

import static com.baidu.location.g.j.D;

/**
 * Created by Administrator on 2018/11/29.
 */

public class FoodLeftRecyclerAdapter extends RecyclerView.Adapter<FoodLeftRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<FoodCategory> data;
    private int number = 0;

    public FoodLeftRecyclerAdapter(Context context, List<FoodCategory> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_vertical_left_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Debbuger.LogE("vertical load");

        if (position == 1 ) {
            holder.category.setBackgroundColor(Color.WHITE);
        }
        holder.category.setText(data.get(position).getCategoryName());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

      TextView category;

        public ViewHolder(View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.food_store_vertical_left_category_name);
        }
    }

}
