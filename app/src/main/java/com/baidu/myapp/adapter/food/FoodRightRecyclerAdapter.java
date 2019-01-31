package com.baidu.myapp.adapter.food;

import android.content.Context;
import android.graphics.Paint;
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

import java.util.List;



/**
 * Created by Administrator on 2018/11/29.
 */

public class FoodRightRecyclerAdapter extends RecyclerView.Adapter<FoodRightRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<FoodBean> data;
    private ZAddWidget.OnAddClick onAddClick;
    public FoodRightRecyclerAdapter(Context context, List<FoodBean> data, ZAddWidget.OnAddClick onAddClick) {
        this.context = context;
        this.data = data;
        this.onAddClick = onAddClick;
        setHasStableIds(true);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_vertical_right_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        FoodGlideUtil.getInstance().setSquareGlide(context, Integer.valueOf(data.get(position).getFoodImg()), holder.foodimg);
        holder.title.setText(data.get(position).getFoodName());
        holder.sale.setText("月销"+data.get(position).getFoodSales());
        holder.praise.setText(data.get(position).getFoodPraise());
        double discount = Computational.getInstance().getDiscount(data.get(position).getFoodPresentPrice(), data.get(position).getFoodOriginalPrice());
        holder.discount.setText(discount + "折");
        holder.origin_price.setText("￥"+data.get(position).getFoodOriginalPrice());
        holder.origin_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.present_price.setText("￥"+data.get(position).getFoodPresentPrice());
//        holder.number.setText(data.get(position).getFoodNum() + "");
//        holder.sub.setVisibility(View.INVISIBLE);
//        holder.number.setVisibility(View.INVISIBLE);
        ZAddWidget zAddWidget = holder.zadd;
        zAddWidget.setData(onAddClick,data.get(position));
      /*  holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Debbuger.LogE("点击了" + data.get(position).getFoodName() + "添加按钮");
                int i = data.get(position).getFoodNum() + 1;
                Debbuger.LogE("对应的数据为:" + i);
                data.get(position).setFoodNum(i);
                onAddClick.onAddClick(data.get(position));
                holder.number.setText("" + data.get(position).getFoodNum());
                if (data.get(position).getFoodNum() == 1) {
                    Debbuger.LogE("显示减按钮");
                    holder.sub.setAnimation(getShowAnimation());
                    holder.number.setVisibility(View.VISIBLE);
                    holder.origin_price.setVisibility(View.GONE);
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
                    onAddClick.onSubClick(data.get(position));
                    holder.number.setText("" + data.get(position).getFoodNum());
                }
                if (data.get(position).getFoodNum() == 0) {
                    Debbuger.LogE("去除减按钮");
                    holder.origin_price.setVisibility(View.VISIBLE);
                    holder.sub.setVisibility(View.INVISIBLE);
                    holder.sub.setAnimation(getHiddenAnimation());
                    holder.number.setVisibility(View.INVISIBLE);
                }
                //控制美食视图界面底部的背景色变化
                if (number == 0) return;
                if (number > 0) number--;
                if (number == 0) {
                    Debbuger.LogE("默认状态");
                }

            }

        });
*/
    }
    /**
     * 显示减号的动画
     * @return
     */


    //必须重写  不然item会错乱
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView foodimg;
        TextView title;
        TextView sale;
        TextView praise;
        TextView discount;
        TextView present_price;
        TextView origin_price;
        ZAddWidget zadd;
        public ViewHolder(View itemView) {
            super(itemView);
            foodimg = itemView.findViewById(R.id.food_store_vertical_right_img);
            title = itemView.findViewById(R.id.food_store_vertical_right_title);
            sale = itemView.findViewById(R.id.food_store_vertical_right_sale);
            praise = itemView.findViewById(R.id.food_store_vertical_right_praise);
            discount = itemView.findViewById(R.id.food_store_vertical_right_discount);
            present_price = itemView.findViewById(R.id.food_vertical_right_present_price);
            origin_price = itemView.findViewById(R.id.food_vertical_right_original_price);
           /* number = itemView.findViewById(R.id.food_vertical_right_item_number);
            add = itemView.findViewById(R.id.food_vertical_right_item_add);
            sub = itemView.findViewById(R.id.food_vertical_righ_item_sub);*/
            zadd = itemView.findViewById(R.id.zaddwidget);
        }
    }


}
