package com.baidu.myapp.view.foodview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.baidu.myapp.R;
import com.baidu.myapp.bean.food.FoodBean;
import com.baidu.myapp.util.Debbuger;
import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;

/**
 * Created by Administrator on 2019/1/16.
 */
public class AddWidget extends FrameLayout {

    private View sub;
    private TextView tv_count;
    private int count;
    private AddButton addbutton;
    private boolean sub_anim, circle_anim;
    private FoodBean foodBean;

    public interface OnAddClick {

        void onAddClick(FoodBean fb);

        void onSubClick(FoodBean fb);
    }

    private OnAddClick onAddClick;

    public AddWidget(@NonNull Context context) {
        super(context);
    }

    public AddWidget(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.view_addwidget, this);
        addbutton = findViewById(R.id.addbutton);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AddWidget);
        for (int i = 0; i < a.getIndexCount(); i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.AddWidget_circle_anim:
                    circle_anim = a.getBoolean(R.styleable.AddWidget_circle_anim, false);
                    break;
                case R.styleable.AddWidget_sub_anim:
                    sub_anim = a.getBoolean(R.styleable.AddWidget_sub_anim, false);
                    break;
            }

        }
        a.recycle();
        sub = findViewById(R.id.iv_sub);
        tv_count = findViewById(R.id.tv_count);
        addbutton.setAnimListner(new AddButton.AnimListner() {
            @Override
            public void onStop() {
                if (count == 0) {
                    ViewAnimator.animate(sub)
                            .translationX(addbutton.getLeft() - sub.getLeft(), 0)
                            .rotation(360)
                            .alpha(0, 255)
                            .duration(300)
                            .interpolator(new DecelerateInterpolator())
                            .andAnimate(tv_count)
                            .translationX(addbutton.getLeft() - tv_count.getLeft(), 0)
                            .rotation(360)
                            .alpha(0, 255)
                            .interpolator(new DecelerateInterpolator())
                            .duration(300)
                            .start()
                    ;
                }
                count++;
                tv_count.setText(count + "");
                foodBean.setFoodNum(count);
                if (onAddClick != null) {
                    onAddClick.onAddClick(foodBean);
                }
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count == 0) {
                    return;
                }
                if (count == 1 && sub_anim) {
                    Debbuger.LogE("底部的控件s");
                    subAnim();
                }
                count--;
                tv_count.setText(count == 0 ? "1" : count + "");
                foodBean.setFoodNum(count);
                if (onAddClick != null) {
                    onAddClick.onSubClick(foodBean);
                }
            }
        });
    }

    private void subAnim(){
        ViewAnimator.animate(sub)
                .translationX(0, addbutton.getLeft() - sub.getLeft())
                .rotation(-360)
                .alpha(255, 0)
                .duration(300)
                .interpolator(new AccelerateInterpolator())
                .andAnimate(tv_count)
                .onStop(new AnimationListener.Stop() {
                    @Override
                    public void onStop() {
                        if (circle_anim) {
                            addbutton.expendAnim();
                        }
                    }
                })
                .translationX(0, addbutton.getLeft() - tv_count.getLeft())
                .rotation(-360)
                .alpha(255, 0)
                .interpolator(new AccelerateInterpolator())
                .duration(300)
                .start()
        ;
    }

    public void setData(OnAddClick onAddClick, FoodBean foodBean) {
        this.foodBean = foodBean;
        this.onAddClick = onAddClick;
        count = foodBean.getFoodNum();
        if (count == 0) {
            sub.setAlpha(0);
            tv_count.setAlpha(0);
        } else {
            sub.setAlpha(1f);
            tv_count.setAlpha(1f);
            tv_count.setText(count + "");
        }
    }

    public void setState(long count) {
        addbutton.setState(count > 0);
    }

    public void expendAdd(int count) {
        this.count = count;
        tv_count.setText(count == 0 ? "1" : count + "");
        if (count == 0) {
            subAnim();
        }
    }
}
