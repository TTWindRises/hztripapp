package com.baidu.myapp.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.myapp.R;
import com.baidu.myapp.adapter.FoodBeanRecyclerAdapter;
import com.baidu.myapp.adapter.food.FoodLeftRecyclerAdapter;
import com.baidu.myapp.bean.food.FoodBean;
import com.baidu.myapp.bean.food.FoodCategory;
import com.baidu.myapp.bean.food.FoodStore;
import com.baidu.myapp.util.CircleCrop;
import com.baidu.myapp.util.Debbuger;
import com.baidu.myapp.util.GlideRoundTransform;
import com.baidu.myapp.util.foodutil.SpaceItemDecoration;
import com.baidu.myapp.view.HorizontalRecycleView;
import com.bumptech.glide.Glide;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 2018/11/7.
 */

public class FoodStoreActivity extends BaseActivity {
    HorizontalRecycleView mListView;
    //HorizontalRecycleView leftListView;
    FoodStore foodStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_store);
        init();
        loadHorizontalFoodView();
        loadBottomView();
        loadVerticalLeftView();

    }

    private void loadHorizontalFoodView() {
        List<FoodBean> foodBeanList;
        foodBeanList = DataSupport.where("store_id=?", foodStore.getStoreID()).find(FoodBean.class);
        if (foodBeanList != null) {
            Debbuger.LogE("存在food信息:" + foodBeanList.toString());
        }
        FoodBeanRecyclerAdapter adapter = new FoodBeanRecyclerAdapter(FoodStoreActivity.this, foodBeanList);

        mListView = (HorizontalRecycleView) findViewById(R.id.food_horizontal_list);
        mListView.addItemDecoration(new SpaceItemDecoration(8));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mListView.setItemAnimator(new DefaultItemAnimator());
        mListView.setLayoutManager(linearLayoutManager);
        mListView.setAdapter(adapter);
        // mListView.setAdapter(new FoodBeanAdapter(FoodStoreActivity.this,foodBeanList));
    }

    //纵向左边列表
    private void loadVerticalLeftView() {
        List<FoodCategory> foodCategoryList;
        foodCategoryList = DataSupport.where("store_id=?", foodStore.getStoreID()).find(FoodCategory.class);
        if (foodCategoryList != null) {
            Debbuger.LogE("存在category信息:" + foodCategoryList.toString());
        }
        FoodLeftRecyclerAdapter adapter = new FoodLeftRecyclerAdapter(FoodStoreActivity.this, foodCategoryList);

        mListView = (HorizontalRecycleView) findViewById(R.id.food_vertical_left_category_list);
        mListView.addItemDecoration(new SpaceItemDecoration(0));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mListView.setItemAnimator(new DefaultItemAnimator());
        mListView.setLayoutManager(linearLayoutManager);
        mListView.setAdapter(adapter);
    }

    private void loadBottomView() {

        ImageView bottom_icon = findViewById(R.id.food_store_bottom_icon);
        bottom_icon.setImageResource(R.drawable.food_store_bottom_icon_default3);
        TextView food_number = findViewById(R.id.food_store_bottom_number);
        food_number.setText("");

    }

    private void init() {
        //轮播图
        ImageView shuffling = findViewById(R.id.food_store_shuffling);
        Glide.with(FoodStoreActivity.this).load(R.drawable.store_head_bg).placeholder(R.drawable.food_store_placeholder_rectangle).into(shuffling);

        TextView textView = (TextView) findViewById(R.id.food_title);
        ImageView headBG = (ImageView) findViewById(R.id.food_store_head_bg);
        ImageView headImg = (ImageView) findViewById(R.id.food_store_head_img);
        ImageView back = (ImageView) findViewById(R.id.food_store_back_img);
        ImageView zan = (ImageView) findViewById(R.id.food_store_prise);
        //
        TextView score = (TextView) findViewById(R.id.food_store_score);
        TextView sell = (TextView) findViewById(R.id.food_store_sale);
        TextView distance = (TextView) findViewById(R.id.food_store_distance);
        TextView K1 = (TextView) findViewById(R.id.food_store_1);
        TextView K2 = (TextView) findViewById(R.id.food_store_2);
        //推荐标题
        TextView recommend_title = (TextView) findViewById(R.id.food_store_recommend_title);
        recommend_title.setText("商家推荐");
        //优惠券
        TextView price = (TextView) findViewById(R.id.food_store_coupon_price);
        TextView full = (TextView) findViewById(R.id.food_store_coupon_full);
        TextView get = (TextView) findViewById(R.id.food_store_coupon_get);
        price.setText("￥6");
        full.setText("满20可用");
        get.setText("领取");
        Bundle extras = getIntent().getExtras();
        foodStore = (FoodStore) extras.get("FoodStore");
        //公告
        TextView notice = (TextView) findViewById(R.id.food_store_notice);
        notice.setText("公告: 单点不配送！高峰期没时间联系，不热爱学习禁止点击购买，不热爱学习禁止购买，不热爱学习禁止购买！重要的事情说三遍，谢谢理解！");
        //设置商店名称
        textView.setText(foodStore.getStoreName());
        //设置评价、销售量、距离
        score.setText("评价4.6");
        K1.setText("  |  ");
        sell.setText("月销1100");
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
                .placeholder(R.drawable.food_store_placeholder_square)
                .dontAnimate()
                .into(headImg);
        //赞
        Glide.with(FoodStoreActivity.this).load(R.drawable.store_zan).transform(new CircleCrop(this))
                .into(zan);
    }


}
