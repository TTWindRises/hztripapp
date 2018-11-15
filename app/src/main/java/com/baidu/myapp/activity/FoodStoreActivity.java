package com.baidu.myapp.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.myapp.R;
import com.baidu.myapp.bean.food.FoodStore;
import com.baidu.myapp.util.CircleCrop;
import com.baidu.myapp.util.GlideRoundTransform;
import com.bumptech.glide.Glide;

import static com.baidu.location.g.j.C;
import static com.baidu.location.g.j.T;

/**
 * Created by Administrator on 2018/11/7.
 */

public class FoodStoreActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_store);
        init();
    }

    private void  init() {
        TextView textView = (TextView) findViewById(R.id.food_title);
        ImageView headBG = (ImageView) findViewById(R.id.food_store_head_bg);
        ImageView headImg = (ImageView) findViewById(R.id.food_store_head_img);
        ImageView back = (ImageView) findViewById(R.id.food_store_back_img);
        ImageView zan = (ImageView) findViewById(R.id.food_store_prise);
        //
        TextView score = (TextView) findViewById(R.id.food_store_score);
        TextView sell = (TextView) findViewById(R.id.food_store_sell);
        TextView distance = (TextView) findViewById(R.id.food_store_distance);
        TextView K1 = (TextView) findViewById(R.id.food_store_1);
        TextView K2 = (TextView) findViewById(R.id.food_store_2);
        Bundle extras = getIntent().getExtras();
        FoodStore foodStore = (FoodStore) extras.get("FoodStore");
        //设置商店名称
        textView.setText(foodStore.getStoreName());
        //设置评价、销售量、距离
        score.setText("评价4.6");
        K1.setText("  |  ");
        sell.setText("月销1110");
        K2.setText("  |  ");
        distance.setText("距离1000m");
        //返回键
        Glide.with(FoodStoreActivity.this).load(R.drawable.back)
                .into(back);
        //顶部背景图片资源加载
        headBG.setImageResource(Integer.parseInt(foodStore.getStoreImg()));
        //头像资源加载
        Glide.with(FoodStoreActivity.this).load(Integer.parseInt(foodStore.getStore_heard_img()))
                .centerCrop().transform(new GlideRoundTransform(this))
                .into(headImg);
        //赞
        Glide.with(FoodStoreActivity.this).load(R.drawable.store_zan).transform(new CircleCrop(this))
                .into(zan);
    }
}
