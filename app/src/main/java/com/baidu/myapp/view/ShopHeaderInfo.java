package com.baidu.myapp.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.myapp.R;
import com.baidu.myapp.util.foodutil.ViewUtils;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Administrator on 2019/3/8.
 */

public class ShopHeaderInfo extends RelativeLayout{

    //头部
    public TextView shop_name;
    private ImageView shop_arrow;
    private LinearLayout shop_layout;
    public ImageView iv_shop;

    public ShopHeaderInfo(Context context) {
        super(context);
    }

    public ShopHeaderInfo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.header_info, this);
        shop_name = findViewById(R.id.food_title);
        shop_arrow = findViewById(R.id.food_store_prise);
        iv_shop = findViewById(R.id.food_store_head_img);
        shop_layout = findViewById(R.id.food_layout);
    }


    public void setWgAlpha(float alpha) {

        shop_arrow.setAlpha(alpha);
        iv_shop.setAlpha(alpha);
        shop_layout.setAlpha(alpha);
    }
}
