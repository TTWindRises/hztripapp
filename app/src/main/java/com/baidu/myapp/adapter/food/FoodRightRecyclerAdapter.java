package com.baidu.myapp.adapter.food;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
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

import com.baidu.myapp.R;
import com.baidu.myapp.activity.FoodStoreActivity;
import com.baidu.myapp.bean.food.FoodBean;
import com.baidu.myapp.bean.food.FoodCategory;
import com.baidu.myapp.interfaces.ifood.IFoodBuy;
import com.baidu.myapp.util.Debbuger;
import com.baidu.myapp.util.foodutil.Computational;
import com.baidu.myapp.util.foodutil.FoodGlideUtil;
import com.bumptech.glide.Glide;

import java.util.List;

import static com.baidu.location.g.j.D;
import static com.baidu.location.g.j.F;
import static com.baidu.location.g.j.t;

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
        setHasStableIds(true);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_vertical_right_item, parent, false);
        return new ViewHolder(view);

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
        holder.number.setText(data.get(position).getFoodNum() + "");
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Debbuger.LogE("点击了" + data.get(position).getFoodName() + "添加按钮");
                int i = data.get(position).getFoodNum() + 1;
                Debbuger.LogE("对应的数据为:" + i);
                data.get(position).setFoodNum(i);
                holder.number.setText("" + data.get(position).getFoodNum());
                if (data.get(position).getFoodNum() == 1) {
                    Debbuger.LogE("显示减按钮");
                    holder.number.setVisibility(View.VISIBLE);
                    holder.origin_price.setVisibility(View.GONE);
                    holder.sub.setVisibility(View.VISIBLE);
                    holder.sub.setAnimation(getShowAnimation());
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

    }
    /**
     * 显示减号的动画
     * @return
     */
    private Animation getShowAnimation(){
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotate = new RotateAnimation(0,720,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        set.addAnimation(rotate);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF,2f
                ,TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,0);
        set.addAnimation(translate);
        AlphaAnimation alpha = new AlphaAnimation(0,1);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }


    /**
     * 隐藏减号的动画
     * @return
     */
    private Animation getHiddenAnimation(){
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotate = new RotateAnimation(0,720,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        set.addAnimation(rotate);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,2f
                ,TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,0);
        set.addAnimation(translate);
        AlphaAnimation alpha = new AlphaAnimation(1,0);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }

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
        TextView number;
        ImageView add;
        ImageView sub;

        public ViewHolder(View itemView) {
            super(itemView);
            foodimg = itemView.findViewById(R.id.food_store_vertical_right_img);
            title = itemView.findViewById(R.id.food_store_vertical_right_title);
            sale = itemView.findViewById(R.id.food_store_vertical_right_sale);
            praise = itemView.findViewById(R.id.food_store_vertical_right_praise);
            discount = itemView.findViewById(R.id.food_store_vertical_right_discount);
            present_price = itemView.findViewById(R.id.food_vertical_right_present_price);
            origin_price = itemView.findViewById(R.id.food_vertical_right_original_price);
            number = itemView.findViewById(R.id.food_vertical_right_item_number);
            add = itemView.findViewById(R.id.food_vertical_right_item_add);
            sub = itemView.findViewById(R.id.food_vertical_righ_item_sub);
        }
    }

}
