package com.baidu.myapp.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.myapp.R;
import com.baidu.myapp.adapter.FoodBeanRecyclerAdapter;
import com.baidu.myapp.adapter.food.BottomSheetAdapter;
import com.baidu.myapp.adapter.food.FoodLeftRecyclerAdapter;
import com.baidu.myapp.adapter.food.FoodRightRecyclerAdapter;
import com.baidu.myapp.animate.ShopCartAnimate;
import com.baidu.myapp.bean.food.FoodBean;
import com.baidu.myapp.bean.food.FoodCategory;
import com.baidu.myapp.bean.food.FoodStore;
import com.baidu.myapp.overlay.util.BezierTypeEvaluator;
import com.baidu.myapp.util.CircleCrop;
import com.baidu.myapp.util.Debbuger;
import com.baidu.myapp.util.GlideRoundTransform;
import com.baidu.myapp.util.foodutil.MyBottomSheetDialog;
import com.baidu.myapp.util.foodutil.SpaceItemDecoration;
import com.baidu.myapp.view.HorizontalRecycleView;
import com.bumptech.glide.Glide;

import org.litepal.crud.DataSupport;

import java.util.List;

import static com.baidu.location.g.j.F;

/**
 * Created by Administrator on 2018/11/7.
 */

public class FoodStoreActivity extends BaseActivity implements FoodBeanRecyclerAdapter.ShopOnClickListtener {
    HorizontalRecycleView mListView;
    //HorizontalRecycleView leftListView;
    FoodStore foodStore;
    private ViewGroup anim_mask_layout;//动画层
    private int[] startLocation;
    private ImageView ball;
    private TextView red_ball;
    private ImageView bottom_icon;
    private TextView money;
    private TextView end;
    private RelativeLayout main_layout;
    private HorizontalRecycleView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_store);
        init();
        loadHorizontalFoodView();
        loadBottomView();
        loadVerticalLeftView();
        loadVerticalRightView();
    }

    //横向列表
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
        red_ball = (TextView) findViewById(R.id.food_store_bottom_number);
        bottom_icon = findViewById(R.id.food_store_bottom_icon);
        money = findViewById(R.id.food_store_bottom_sum);
        end = findViewById(R.id.food_store_bottom_settlement);
        adapter.InitRedBall(red_ball, bottom_icon, money, end);
        //底部弹出界面

        money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheetDialog();
            }
        });
        //设置添加按钮的监听
        adapter.setShopOnClickListtener(this);
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

    //纵向右边列表
    private void loadVerticalRightView() {
        List<FoodBean> foodBeans;
        foodBeans = DataSupport.where("store_id=?", foodStore.getStoreID()).find(FoodBean.class);
        if (foodBeans != null) {
            Debbuger.LogE("存在category信息:" + foodBeans.toString());
        }
        FoodRightRecyclerAdapter adapter = new FoodRightRecyclerAdapter(FoodStoreActivity.this, foodBeans);

        mListView = (HorizontalRecycleView) findViewById(R.id.food_store_vertical_right_list);
        mListView.addItemDecoration(new SpaceItemDecoration(10));
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
    private void sheetDialog() {
        BottomSheetAdapter adapter = new BottomSheetAdapter(this);
        RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(this).inflate(R.layout.food_store_bottom_fragment, null);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT);
        recyclerView.setLayoutParams(params);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        final MyBottomSheetDialog dialog = new MyBottomSheetDialog(this, this);
        dialog.setContentView(recyclerView);
        dialog.show();
    }
    private void init() {
        //全局
        main_layout = findViewById(R.id.food_store);
        recyclerView = findViewById(R.id.food_horizontal_list);
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
        Glide.with(FoodStoreActivity.this)
                .load(R.drawable.store_zan)
                .transform(new CircleCrop(this))
                .override(32, 32)
                .into(zan);
    }


    @Override
    public void add(final View view, int position) {
        //贝塞尔起始数据点
        int[] startPosition = new int[2];
        //贝塞尔结束数据点
        int[] endPosition = new int[2];
        //控制点
        int[] recyclerPosition = new int[2];

        view.getLocationInWindow(startPosition);
        bottom_icon.getLocationInWindow(endPosition);
        recyclerView.getLocationInWindow(recyclerPosition);

        PointF startF = new PointF();
        PointF endF = new PointF();
        PointF controllF = new PointF();

        startF.x = startPosition[0];
//        startF.y = startPosition[1] - recyclerPosition[1];
        startF.y = startPosition[1] - 40;
        endF.x = endPosition[0] + 40;
//        endF.y = endPosition[1] - recyclerPosition[1];
        endF.y = endPosition[1] - 60;
        controllF.x = endF.x;
        controllF.y = startF.y;

        final ImageView imageView = new ImageView(this);
        main_layout.addView(imageView);
        imageView.setImageResource(R.drawable.food_animate_ball);
        imageView.getLayoutParams().width = getResources().getDimensionPixelSize(R.dimen.item_dish_circle_size);
        imageView.getLayoutParams().height = getResources().getDimensionPixelSize(R.dimen.item_dish_circle_size);
        imageView.setVisibility(View.VISIBLE);
        imageView.setX(startF.x);
        imageView.setY(startF.y);

        final ValueAnimator valueAnimator = ValueAnimator.ofObject(new BezierTypeEvaluator(controllF), startF, endF);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                imageView.setX(pointF.x);
                imageView.setY(pointF.y);
                Log.i("wangjtiao", "viewF:" + view.getX() + "," + view.getY());
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                valueAnimator.cancel();
                imageView.setVisibility(View.GONE);
            }

        });

        ObjectAnimator objectAnimatorX = new ObjectAnimator().ofFloat(bottom_icon, "scaleX", 0.7f, 1.0f);
        ObjectAnimator objectAnimatorY = new ObjectAnimator().ofFloat(bottom_icon, "scaleY", 0.7f, 1.0f);
        objectAnimatorX.setInterpolator(new AccelerateInterpolator());
        objectAnimatorY.setInterpolator(new AccelerateInterpolator());
        AnimatorSet set = new AnimatorSet();
        set.play(objectAnimatorX).with(objectAnimatorY).after(valueAnimator);
        set.setDuration(800);
        set.start();
    }

    @Override
    public void remove(View view, int position) {

    }
}
