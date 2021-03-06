package com.baidu.myapp.view.foodview;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.myapp.R;
import com.baidu.myapp.util.Debbuger;
import com.baidu.myapp.util.foodutil.ViewUtils;

import java.math.BigDecimal;

import static com.baidu.myapp.activity.FoodStoreActivity.carAdapter;

/**
 * Created by Administrator on 2019/1/16.
 */


public class ShopCarView extends FrameLayout {
    private TextView car_limit, tv_amount;
    public ImageView iv_shop_car;
    public TextView car_badge;
    private BottomSheetBehavior behavior;
    public boolean sheetScrolling;
    public View shoprl;
    public int[] carLoc;

    public void setBehavior(final BottomSheetBehavior behavior) {
        this.behavior = behavior;
    }

    public void setBehavior(final BottomSheetBehavior behavior, final View blackView) {
        this.behavior = behavior;
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                sheetScrolling = false;
                if (newState == BottomSheetBehavior.STATE_COLLAPSED || newState == BottomSheetBehavior.STATE_HIDDEN) {
                    blackView.setVisibility(View.GONE);
                    Debbuger.LogE("去掉视图");//这里的逻辑是购物车被清空的时候去掉加载出来的条目栏
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                sheetScrolling = true;
                //显示结算条目栏
                blackView.setVisibility(View.VISIBLE);
                ViewCompat.setAlpha(blackView, slideOffset);
            }
        });
        blackView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //不被覆盖的部分被触摸就会执行，这里执行的是让弹出来的结算栏收回去
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                return true;
            }
        });
    }

    public ShopCarView(@NonNull Context context) {
        super(context);
    }

    public ShopCarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (iv_shop_car == null) {
            iv_shop_car = findViewById(R.id.iv_shop_car);
            car_badge = findViewById(R.id.car_badge);
            car_limit = findViewById(R.id.car_limit);
            tv_amount = findViewById(R.id.tv_amount);
            shoprl = findViewById(R.id.car_rl);
            shoprl.setOnClickListener(new toggleCar());
            carLoc = new int[2];
            iv_shop_car.getLocationInWindow(carLoc);
            carLoc[0] = carLoc[0] + iv_shop_car.getWidth() / 2 - ViewUtils.dip2px(getContext(), 10);
        }
    }

    public void updateAmount(BigDecimal amount) {
        if (amount.compareTo(new BigDecimal(0.0)) == 0) {
            car_limit.setText("¥20 起送");
            car_limit.setTextColor(Color.parseColor("#a8a8a8"));
            car_limit.setBackgroundColor(Color.parseColor("#535353"));
            findViewById(R.id.car_nonselect).setVisibility(View.VISIBLE);
            findViewById(R.id.amount_container).setVisibility(View.GONE);
            iv_shop_car.setImageResource(R.drawable.food_store_bottom_icon_default3);
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else if (amount.compareTo(new BigDecimal(20.0)) < 0) {
            car_limit.setText("差 ¥" + new BigDecimal(20.0).subtract(amount));
            car_limit.setTextColor(Color.parseColor("#a8a8a8"));
            car_limit.setBackgroundColor(Color.parseColor("#535353"));
            findViewById(R.id.car_nonselect).setVisibility(View.GONE);
            findViewById(R.id.amount_container).setVisibility(View.VISIBLE);
            iv_shop_car.setImageResource(R.drawable.food_store_bottom_icon_pick3);
        } else if(amount.compareTo(new BigDecimal(20.0))==1){
            car_limit.setText("     去结算     ");
            car_limit.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            car_limit.setTextColor(Color.WHITE);
            car_limit.setBackgroundColor(Color.parseColor("#59d178"));
            findViewById(R.id.car_nonselect).setVisibility(View.GONE);
            findViewById(R.id.amount_container).setVisibility(View.VISIBLE);
            iv_shop_car.setImageResource(R.drawable.food_store_bottom_icon_pick3);
        }
        tv_amount.setText("¥" + amount);
    }

    public void showBadge(int total) {
        if (total > 0) {
            car_badge.setVisibility(View.VISIBLE);
            car_badge.setText(total + "");
        } else {
            car_badge.setVisibility(View.INVISIBLE);
        }
    }

    private class toggleCar implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (sheetScrolling) {
                return;
            }
            if (carAdapter.getItemCount() == 0) {
                return;
            }
            if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            } else {
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        }
    }

}
