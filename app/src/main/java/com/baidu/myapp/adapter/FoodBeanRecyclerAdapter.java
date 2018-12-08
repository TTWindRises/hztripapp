package com.baidu.myapp.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.myapp.R;
import com.baidu.myapp.activity.FoodStoreActivity;
import com.baidu.myapp.bean.food.FoodBean;
import com.baidu.myapp.enums.FoodEnum;
import com.baidu.myapp.impl.foodimpl.FoodUtil;
import com.baidu.myapp.interfaces.ifood.IFoodBuy;
import com.baidu.myapp.util.Debbuger;

import java.util.List;

import static android.media.CamcorderProfile.get;
import static com.baidu.location.g.j.D;

/**
 * Created by Administrator on 2018/11/29.
 */

public class FoodBeanRecyclerAdapter extends RecyclerView.Adapter<FoodBeanRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<FoodBean> data;
    public IFoodBuy iFoodBuy;
    private int number = 0;

    public FoodBeanRecyclerAdapter(Context context, List<FoodBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_horizontal_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.imageView.setImageResource(Integer.parseInt(data.get(position).getFoodImg()));
        holder.title.setText(data.get(position).getFoodName());
        holder.sales.setText("月销" + data.get(position).getFoodSales());
        holder.original_price.setText("￥" + (CharSequence) data.get(position).getFoodOriginalPrice());
        holder.present_price.setText("￥" + (CharSequence) data.get(position).getFoodPresentPrice());
        holder.original_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

       /*         Debbuger.LogE("点击了" + data.get(position).getFoodName() + "图片");
                //控制单个食品的增减的变化
                if (data.get(position).getFoodNum() == 0) return;
                if (data.get(position).getFoodNum() > 0) {

                    int i = data.get(position).getFoodNum() - 1;
                    Debbuger.LogE("对应的数据为:" + i);
                    data.get(position).setFoodNum(i);
                }
                if (data.get(position).getFoodNum() == 0) {
                    Debbuger.LogE("去除减按钮");
                    holder.sub.setVisibility(View.INVISIBLE);
                    holder.num.setVisibility(View.INVISIBLE);
                }
                //控制美食视图界面底部的背景色变化
                if (number == 0) return;
                if (number > 0) number--;
                if (number == 0) {
                    Debbuger.LogE("默认状态");
                }*/

            }
        });
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Debbuger.LogE("点击了" + data.get(position).getFoodName() + "添加按钮");
                int i = data.get(position).getFoodNum() + 1;
                Debbuger.LogE("对应的数据为:" + i);
                data.get(position).setFoodNum(i);
                holder.num.setText(""+data.get(position).getFoodNum());
                if (data.get(position).getFoodNum() == 1) {
                    Debbuger.LogE("显示减按钮");
                    holder.num.setVisibility(View.VISIBLE);
                    holder.original_price.setVisibility(View.GONE);
                    holder.sub.setVisibility(View.VISIBLE);
                }
                number++;
                if (number == 1) {
                    Debbuger.LogE("选中状态");
                }
            }
        });
        holder.sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Debbuger.LogE("点击了" + data.get(position).getFoodName() + "图片");
                //控制单个食品的增减的变化
                if (data.get(position).getFoodNum() == 0) return;
                if (data.get(position).getFoodNum() > 0) {

                    int i = data.get(position).getFoodNum() - 1;
                    Debbuger.LogE("对应的数据为:" + i);
                    data.get(position).setFoodNum(i);
                    holder.num.setText(""+data.get(position).getFoodNum());
                }
                if (data.get(position).getFoodNum() == 0) {
                    Debbuger.LogE("去除减按钮");
                    holder.original_price.setVisibility(View.VISIBLE);
                    holder.sub.setVisibility(View.INVISIBLE);
                    holder.num.setVisibility(View.INVISIBLE);
                }
                //控制美食视图界面底部的背景色变化
                if (number == 0) return;
                if (number > 0) number--;
                if (number == 0) {
                    Debbuger.LogE("默认状态");
                }

            }

        });

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
        TextView num;
        ImageView add;
        ImageView sub;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.food_horizontal_item_img);
            title = itemView.findViewById(R.id.food_horizontal_item_title);
            sales = itemView.findViewById(R.id.food_horizontal_item_sales);
            present_price = itemView.findViewById(R.id.food_present_price);
            original_price = itemView.findViewById(R.id.food_original_price);
            add = itemView.findViewById(R.id.food_horizontal_item_add);//
            sub = itemView.findViewById(R.id.food_horizontal_item_sub);
            num = itemView.findViewById(R.id.food_horizontal_item_number);
        }
    }

}
