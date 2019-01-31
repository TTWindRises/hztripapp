package com.baidu.myapp.view.foodview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.myapp.R;
import com.baidu.myapp.bean.food.FoodBean;
import com.baidu.myapp.util.Debbuger;
import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;

/**
 * Created by Administrator on 2019/1/16.
 */
public class ZAddWidget extends FrameLayout {

    private View sub;
    private TextView tv_count;
    private int count;
    private ImageView addbutton;
    private boolean sub_anim, circle_anim;
    private FoodBean foodBean;

    public interface OnAddClick {

        void onAddClick(FoodBean fb);

        void onSubClick(FoodBean fb);
    }

    private OnAddClick onAddClick;

    public ZAddWidget(@NonNull Context context) {
        super(context);
    }

    public ZAddWidget(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.food_addwidget, this);
        addbutton = findViewById(R.id.food_vertical_right_item_add);
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
        sub = findViewById(R.id.food_vertical_righ_item_sub);
        tv_count = findViewById(R.id.food_vertical_right_item_number);
        addbutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                count++;
                tv_count.setText(count + "");
                foodBean.setFoodNum(count);
                if (count == 1) {
                    Debbuger.LogE("谁点击了我");
                    sub.setAnimation(getShowAnimation());
                    sub.setVisibility(VISIBLE);
                    tv_count.setVisibility(VISIBLE);
                }
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
                if (count > 0) {
                    count--;
                    tv_count.setText(count == 0 ? "1" : count + "");
                    foodBean.setFoodNum(count) ;
                }
                if (count == 0) {
                    sub.setAnimation(getHiddenAnimation());
                    sub.setVisibility(INVISIBLE);
                    tv_count.setVisibility(INVISIBLE);
//                    subAnim();
                }
                if (onAddClick != null) {
                    onAddClick.onSubClick(foodBean);
                }
            }
        });
    }

   /* private void subAnim(){
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
    }*/
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
    public void setData(OnAddClick onAddClick, FoodBean foodBean) {
        this.foodBean = foodBean;
        this.onAddClick = onAddClick;
        count = foodBean.getFoodNum();
        if (count == 0) {
            sub.setVisibility(INVISIBLE);
            tv_count.setVisibility(INVISIBLE);
        } else {
            sub.setVisibility(VISIBLE);
            tv_count.setVisibility(VISIBLE);
            tv_count.setText(count + "");
        }
    }



    public void expendAdd(int count) {
        this.count = count;
        tv_count.setText(count == 0 ? "1" : count + "");
        if (count == 0) {
//            subAnim();
        }
    }
}
