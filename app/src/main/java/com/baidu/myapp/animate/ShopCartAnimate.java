package com.baidu.myapp.animate;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.baidu.myapp.activity.FoodStoreActivity;
import com.baidu.myapp.util.Debbuger;

/**
 * Created by Administrator on 2018/12/11.
 */

public class ShopCartAnimate {
    //dong动画:

    private ViewGroup anim_mask_layout;//动画层
    Activity context;

    public ShopCartAnimate() {
    }

    public ShopCartAnimate(Activity context) {
        Debbuger.LogE("创建类Animate");
        this.context = context;
    }
    public void setAnim(final View v, int[] startLocation,ImageView bottom_icon) {
        anim_mask_layout = null;
        anim_mask_layout = createAnimLayout();
        ViewGroup parent = (ViewGroup) v.getParent();
        if (parent != null) {
            parent.removeAllViews();
        }
            anim_mask_layout.addView(v);//把动画小球添加到动画层}
            final View view = addViewToAnimLayout(anim_mask_layout, v,
                    startLocation);
            int[] endLocation = new int[2];// 存储动画结束位置的X、Y坐标
            bottom_icon.getLocationInWindow(endLocation);// re_zhongcai_tanchu是那个抛物线最后掉落的控件

            // 计算位移
            int endX = 0 - startLocation[0] + 40;// 动画位移的X坐标
            int endY = endLocation[1] - startLocation[1];// 动画位移的y坐标
            TranslateAnimation translateAnimationX = new TranslateAnimation(0,
                    endX, 0, 0);
            translateAnimationX.setInterpolator(new LinearInterpolator());
            translateAnimationX.setRepeatCount(0);// 动画重复执行的次数
            translateAnimationX.setFillAfter(true);

            TranslateAnimation translateAnimationY = new TranslateAnimation(0, 0,
                    0, endY);
            translateAnimationY.setInterpolator(new AccelerateInterpolator());
            translateAnimationY.setRepeatCount(0);// 动画重复执行的次数
            translateAnimationX.setFillAfter(true);

            final AnimationSet set = new AnimationSet(false);
            set.setFillAfter(false);
            set.addAnimation(translateAnimationY);
            set.addAnimation(translateAnimationX);
            set.setDuration(800);// 动画的执行时间
            view.startAnimation(set);
            // 动画监听事件
            set.setAnimationListener(new Animation.AnimationListener() {
                // 动画的开始
                @Override
                public void onAnimationStart(Animation animation) {
                    v.setVisibility(View.VISIBLE);
                    //    Log.e("动画","asdasdasdasd");
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    // TODO Auto-generated method stub
                }

                // 动画的结束
                @Override
                public void onAnimationEnd(Animation animation) {
                    v.setVisibility(View.GONE);
                    v.destroyDrawingCache();
                    set.cancel();
                    animation.cancel();
                }
            });

        }

    /**
     * @Description: 创建动画层
     * @param
     * @return void
     * @throws
     */
    private ViewGroup createAnimLayout() {
        ViewGroup rootView = (ViewGroup) context.getWindow().getDecorView();
        LinearLayout animLayout = new LinearLayout(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        animLayout.setId(Integer.MAX_VALUE);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }

    private View addViewToAnimLayout(final ViewGroup parent, final View view,
                                     int[] location) {
        int x = location[0];
        int y = location[1];
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = x;
        lp.topMargin = y;
        view.setLayoutParams(lp);
        return view;
    }

}
