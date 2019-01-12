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
    public IFoodBuy iFoodBuy;
    private int number = 0;
    private double present_sum =0;
    private double origin_sum=0;
    private ImageView ball ;
    private TextView red_ball;
    private ImageView bottom_icon;
    private TextView  money;
    private TextView  end;
    private ShopCartAnimate animate;
    private ShopOnClickListtener mShopOnClickListtener;
   
    //记录动画起始位置
    private int[] startLocation;



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
        FoodGlideUtil.getInstance().setSquareGlide(context, Integer.valueOf(data.get(position).getFoodImg()), holder.imageView);
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

                //减按钮的显示逻辑
                Debbuger.LogE("点击了" + data.get(position).getFoodName() + "添加按钮");
                int i = data.get(position).getFoodNum() + 1;
                Debbuger.LogE("对应的数据为:" + i);
                data.get(position).setFoodNum(i);
                holder.num.setText(""+data.get(position).getFoodNum());
                present_sum +=Double.parseDouble(data.get(position).getFoodPresentPrice());
                origin_sum +=Double.parseDouble(data.get(position).getFoodOriginalPrice());
                if (data.get(position).getFoodNum() == 1) {
                    Debbuger.LogE("显示减按钮");
                    holder.num.setVisibility(View.VISIBLE);
                    holder.original_price.setVisibility(View.GONE);
                    holder.sub.setVisibility(View.VISIBLE);

                }
                number++;
                if (number == 1) {
                    Debbuger.LogE("选中状态");
                    red_ball.setVisibility(View.VISIBLE);
                    bottom_icon.setImageResource(R.drawable.food_store_bottom_icon_pick3);
                    money.setTextColor(Color.parseColor("#FFFFFF"));
                    money.setTextSize(16);
                    TextPaint paint = money.getPaint();
                    paint.setFakeBoldText(true);
                    end.setBackgroundColor(Color.parseColor("#53dc53"));
                    end.setTextColor(Color.parseColor("#FFFFFF"));
                    TextPaint paint2 = end.getPaint();
                    paint2.setFakeBoldText(true);
                }
                red_ball.setText(""+number);
                money.setText("￥"+ new BigDecimal(present_sum).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue());
            }
        });

        holder.sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mShopOnClickListtener != null) {
                    mShopOnClickListtener.remove(v, position);
                }
                Debbuger.LogE("点击了" + data.get(position).getFoodName() + "图片");
                //控制单个食品的增减的变化
                if (data.get(position).getFoodNum() == 0) return;
                if (data.get(position).getFoodNum() > 0) {

                    int i = data.get(position).getFoodNum() - 1;
                    Debbuger.LogE("对应的数据为:" + i);
                    data.get(position).setFoodNum(i);
                    holder.num.setText(""+data.get(position).getFoodNum());
                    present_sum -=Double.parseDouble( data.get(position).getFoodPresentPrice());

                    origin_sum -=Double.parseDouble( data.get(position).getFoodOriginalPrice());
                    money.setText("￥"+ new BigDecimal(present_sum).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue());
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
                    red_ball.setVisibility(View.INVISIBLE);
                    bottom_icon.setImageResource(R.drawable.food_store_bottom_icon_default3);
                    end.setBackgroundColor(Color.parseColor("#68676b"));
                    end.setTextColor(Color.parseColor("#b8b8b8"));
                    money.setText("未选购商品");
                    money.setTextColor(Color.parseColor("#b8b8b8"));
                    TextPaint paint = money.getPaint();
                    paint.setFakeBoldText(false);
                    TextPaint paint2 = end.getPaint();
                    paint2.setFakeBoldText(false);
                    money.setTextSize(13);
                }
                red_ball.setText(""+number);
            }

        });

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

    public void InitRedBall(TextView red_ball, ImageView bottom_icon , TextView money, TextView end ) {
        this.red_ball = red_ball;
        this.money = money;
        this.bottom_icon = bottom_icon;
        this.end =end;
    }
    public interface ShopOnClickListtener {

        void add(View view, int position);

        void remove(View view, int position);
    }

    public ShopOnClickListtener getShopOnClickListtener() {
        return mShopOnClickListtener;
    }

    public void setShopOnClickListtener(ShopOnClickListtener mShopOnClickListtener) {
        this.mShopOnClickListtener = mShopOnClickListtener;
    }
}
