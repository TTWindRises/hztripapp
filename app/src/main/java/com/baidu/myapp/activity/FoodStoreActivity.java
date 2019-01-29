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
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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
import com.baidu.myapp.adapter.CarAdapter;
import com.baidu.myapp.adapter.FoodBeanRecyclerAdapter;
import com.baidu.myapp.adapter.food.BottomSheetAdapter;
import com.baidu.myapp.adapter.food.FoodLeftRecyclerAdapter;
import com.baidu.myapp.adapter.food.FoodRightRecyclerAdapter;
import com.baidu.myapp.adapter.food.TabFragmentAdapter;
import com.baidu.myapp.animate.AnimationUtil;
import com.baidu.myapp.animate.ShopCartAnimate;
import com.baidu.myapp.bean.food.FoodBean;
import com.baidu.myapp.bean.food.FoodCategory;
import com.baidu.myapp.bean.food.FoodStore;
import com.baidu.myapp.fragment.food.FoodEvaluateFragment;
import com.baidu.myapp.fragment.food.FoodGoodsFragment;
import com.baidu.myapp.overlay.util.BezierTypeEvaluator;
import com.baidu.myapp.util.CircleCrop;
import com.baidu.myapp.util.Debbuger;
import com.baidu.myapp.util.GlideRoundTransform;
import com.baidu.myapp.util.foodutil.IndicatorLineUtil;
import com.baidu.myapp.util.foodutil.MyBottomSheetDialog;
import com.baidu.myapp.util.foodutil.SpaceItemDecoration;
import com.baidu.myapp.util.foodutil.ViewUtils;
import com.baidu.myapp.view.HorizontalRecycleView;
import com.baidu.myapp.view.foodview.AddWidget;
import com.bumptech.glide.Glide;

import org.litepal.crud.DataSupport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.baidu.location.g.j.F;

/**
 * Created by Administrator on 2018/11/7.
 */

public class FoodStoreActivity extends BaseActivity implements AddWidget.OnAddClick {
    HorizontalRecycleView mListView;
    //HorizontalRecycleView leftListView;
    FoodStore foodStore;
    private CoordinatorLayout main_layout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private HorizontalRecycleView recyclerView;
    //两个碎片页面
    private ViewPager viewPager;
    //Fragment列表
    private List<Fragment> mFragments = new ArrayList<>();
    //列表标题
    private List<String> mTitles = new ArrayList<>();
    private TabFragmentAdapter adapter;
    TabLayout slidingTabLayout;

    //购物车
    public BottomSheetBehavior behavior;
    public static CarAdapter carAdapter;
    private ShopCarView shopCarView;
    private CoordinatorLayout rootview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_store);
        init();
        setCollsapsing();
        setViewPager();
        initShopCar();
    }


    //头
    private void init() {
        //viewpage
        viewPager = (ViewPager) findViewById(R.id.food_store_view_pager);

        //全局
        main_layout = (CoordinatorLayout) findViewById(R.id.activity_main);
//        recyclerView = (HorizontalRecycleView) findViewById(R.id.food_horizontal_list);
        //轮播图
        ImageView shuffling = (ImageView) findViewById(R.id.food_store_shuffling);
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

    //两个Pager
    private void setViewPager() {

        FoodGoodsFragment goodsFragment = new FoodGoodsFragment();
        goodsFragment.setStoreID(foodStore.getStoreID());//让碎片能够找到相应的实体类
        FoodEvaluateFragment evaluateFragment = new FoodEvaluateFragment();
        mFragments.add(goodsFragment);
        mFragments.add(evaluateFragment);
        mTitles.add("商品");
        mTitles.add("评价");

        adapter = new TabFragmentAdapter(getSupportFragmentManager(), mFragments, mTitles);


        viewPager.setAdapter(adapter);
        slidingTabLayout = (TabLayout) findViewById(R.id.slidinglayout);
        slidingTabLayout.setupWithViewPager(viewPager);
        slidingTabLayout.post(new Runnable() {
            @Override
            public void run() {
                IndicatorLineUtil.setIndicator(slidingTabLayout, 60, 60);
            }
        });

    }

    private void setCollsapsing() {
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.touming));

    }
    //购物车

    private void initShopCar() {
        behavior = BottomSheetBehavior.from(findViewById(R.id.car_container));//底部弹出结算栏的行为
        shopCarView = (ShopCarView) findViewById(R.id.car_mainfl);
        View blackView = findViewById(R.id.blackview);//blackView是一个暗屏
        shopCarView.setBehavior(behavior, blackView);
        RecyclerView carRecView = (RecyclerView) findViewById(R.id.car_recyclerview);
//		carRecView.setNestedScrollingEnabled(false);
        carRecView.setLayoutManager(new LinearLayoutManager(mContext));
        ((DefaultItemAnimator) carRecView.getItemAnimator()).setSupportsChangeAnimations(false);
        carAdapter = new CarAdapter(new ArrayList<FoodBean>(), this);
        carAdapter.bindToRecyclerView(carRecView);
    }

    @Override
    public void onAddClick(View view, FoodBean fb) {
        dealCar(fb);

    }


    @Override
    public void onSubClick(FoodBean fb) {
        dealCar(fb);
    } //这里就是消除购物车item组件的视图，和bottomsheetview的逻辑了。

    private void dealCar(FoodBean foodBean) {
        HashMap<String, Long> typeSelect = new HashMap<>();//更新左侧类别badge用
        BigDecimal amount = new BigDecimal(0.0);
        int total = 0;
        boolean hasFood = false;
       /* if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            firstFragment.getFoodAdapter().notifyDataSetChanged();
        }*/
        List<FoodBean> flist = carAdapter.getData();
        int p = -1;//这个是标记判断什么用的，得了了解一下
        for (int i = 0; i < flist.size(); i++) {
            FoodBean fb = flist.get(i);
            Debbuger.LogE("fb.getFoodID:" + fb.getFoodID() + "\nfoodBean.getFoodID:"+foodBean.getFoodID());
            if (fb.getFoodID() == foodBean.getFoodID()) {
                fb = foodBean;
                hasFood = true;
                if (foodBean.getSelectCount() == 0) {
                    p = i;
                } else {
                    carAdapter.setData(i, foodBean);
                }
            }
            total += fb.getSelectCount();
         /*   if (typeSelect.containsKey(fb.getType())) {
                typeSelect.put(fb.getType(), typeSelect.get(fb.getType()) + fb.getSelectCount());
            } else {
                typeSelect.put(fb.getType(), fb.getSelectCount());
            }*/
            amount = amount.add(fb.getPrice().multiply(BigDecimal.valueOf(fb.getSelectCount())));
        }
        if (p >= 0) {
            carAdapter.remove(p);
        } /*else if (!hasFood && foodBean.getSelectCount() > 0) {
            carAdapter.addData(foodBean);
            if (typeSelect.containsKey(foodBean.getType())) {
                typeSelect.put(foodBean.getType(), typeSelect.get(foodBean.getType()) + foodBean.getSelectCount());
            } else {
                typeSelect.put(foodBean.getType(), foodBean.getSelectCount());
            }
            amount = amount.add(foodBean.getPrice().multiply(BigDecimal.valueOf(foodBean.getSelectCount())));
            total += foodBean.getSelectCount();
        }*/
        shopCarView.showBadge(total);
     /*   firstFragment.getTypeAdapter().updateBadge(typeSelect);*/
        shopCarView.updateAmount(amount);
    }

}
