package com.baidu.myapp.util.foodutil;

import android.content.Context;
import android.widget.ImageView;

import com.baidu.myapp.R;
import com.baidu.myapp.util.GlideRoundTransform;
import com.bumptech.glide.Glide;

import static com.baidu.location.g.j.C;

/**
 * Created by Administrator on 2018/12/10.
 */

public class FoodGlideUtil {
    private static FoodGlideUtil glideUtil = new FoodGlideUtil();

    public static FoodGlideUtil getInstance() {
        return glideUtil;
    }

    public void setSquareGlide(Context context,Integer integer, ImageView imageView) {
        Glide.with(context)
                .load(integer)
                .dontAnimate()
                .placeholder(R.drawable.food_store_placeholder_square)
                .centerCrop().transform(new GlideRoundTransform(context))
                .into(imageView);
    }
}
