package com.baidu.myapp.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.myapp.R;
import com.baidu.myapp.adapter.CarAdapter;
import com.baidu.myapp.adapter.food.TabFragmentAdapter;
import com.baidu.myapp.bean.food.FoodBean;
import com.baidu.myapp.bean.food.FoodStore;
import com.baidu.myapp.fragment.comment.SecondFragment;
import com.baidu.myapp.fragment.food.FoodEvaluateFragment;
import com.baidu.myapp.fragment.food.FoodGoodsFragment;
import com.baidu.myapp.util.CircleCrop;
import com.baidu.myapp.util.Debbuger;
import com.baidu.myapp.util.GlideRoundTransform;
import com.baidu.myapp.util.foodutil.IndicatorLineUtil;
import com.baidu.myapp.util.foodutil.ViewUtils;
import com.baidu.myapp.view.HorizontalRecycleView;
import com.baidu.myapp.view.foodview.AddWidget;
import com.baidu.myapp.view.foodview.ShopCarView;
import com.baidu.myapp.view.foodview.ZAddWidget;
import com.bumptech.glide.Glide;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.baidu.location.g.j.am;
import static com.baidu.location.g.j.t;
import static com.baidu.location.g.j.v;

/**
 * Created by Administrator on 2018/11/7.
 */

public class FoodStoreActivity extends BaseActivity implements ZAddWidget.OnAddClick {
    HorizontalRecycleView mListView;
    //HorizontalRecycleView leftListView;
    public static final String CAR_ACTION = "handleCar";
    public static final String CLEARCAR_ACTION = "clearCar";
    FoodStore foodStore;
    FoodGoodsFragment goodsFragment;
    BigDecimal amount=new BigDecimal(0.0);
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
        IntentFilter intentFilter = new IntentFilter(CAR_ACTION);
        intentFilter.addAction(CLEARCAR_ACTION);
        registerReceiver(broadcastReceiver, intentFilter);
    }


    //头
    private void init() {
        //viewpage
        viewPager = (ViewPager) findViewById(R.id.food_store_view_pager);

        //全局
        main_layout = (CoordinatorLayout) findViewById(R.id.activity_main);
//        recyclerView = (HorizontalRecycleView) findViewById(R.id.food_horizontal_list);
//        //轮播图
//        ImageView shuffling = (ImageView) findViewById(R.id.food_store_shuffling);
//        Glide.with(FoodStoreActivity.this).load(R.drawable.store_head_bg).placeholder(R.drawable.food_store_placeholder_rectangle).into(shuffling);

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
        final TextView get = (TextView) findViewById(R.id.food_store_coupon_get);
        final LinearLayout coupon = (LinearLayout) findViewById(R.id.food_store_Coupon);
        price.setText("￥6");
        full.setText("满20可用");
        get.setText("领取");
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (get.getText().equals("领取")) {
                    coupon.setBackgroundColor(ContextCompat.getColor(FoodStoreActivity.this,R.color.gray));
                    get.setText("已领取");
                }

            }
        });
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
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodStoreActivity.this.finish();
            }
        });
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

        goodsFragment = new FoodGoodsFragment();
        goodsFragment.setStoreID(foodStore.getStoreID());//让碎片能够找到相应的实体类
        SecondFragment secondFragment = new SecondFragment();
        mFragments.add(goodsFragment);
        mFragments.add(secondFragment);
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
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case CAR_ACTION:
                    //这里是跟详情页有关联的，是把点击了MainAcitity的item的详情需要在Fragment里面也加载相应的内容，
                    FoodBean foodBean = (FoodBean) intent.getSerializableExtra("foodbean");
                    FoodBean fb = foodBean;
                    int p = intent.getIntExtra("position", -1);
                    if (p >= 0 && p < goodsFragment.getFoodAdapter().getItemCount()) {//就是通过适配器获取点击了那个item的数据信息，将foodNum变更而已
                        fb = goodsFragment.getFoodAdapter().getItem(p);
                        fb.setFoodNum(foodBean.getFoodNum());
                        goodsFragment.getFoodAdapter().setData(p, fb);//这里很显然就是把position的data信息传递到fragment的适配器里面
                    } else {
                        for (int i = 0; i < goodsFragment.getFoodAdapter().getItemCount(); i++) {
                            fb = goodsFragment.getFoodAdapter().getItem(i);
                            if (fb.getFoodID() == foodBean.getFoodID()) {
                                fb.setFoodNum(foodBean.getFoodNum());
                                goodsFragment.getFoodAdapter().setData(i, fb);
                                break;
                            }
                        }
                    }
                    dealCar(fb);
                    break;
                case CLEARCAR_ACTION:
                    clearCar();
                    break;
            }
            if (CAR_ACTION.equals(intent.getAction())) {

            }
        }
    };
    @Override
    public void onAddClick(FoodBean fb) {
        dealCar(fb);
    }


    @Override
    public void onSubClick(FoodBean fb) {
        dealCar(fb);
    } //这里就是消除购物车item组件的视图，和bottomsheetview的逻辑了。

    private void dealCar(FoodBean foodBean) {
        HashMap<String, Integer> typeSelect = new HashMap<>();//更新左侧类别badge用
        amount = new BigDecimal(0.0);
        int total = 0;
        boolean hasFood = false;
  /*    if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            goodsFragment.getFoodAdapter().notifyDataSetChanged();
            goodsFragment.getHAdapter().notifyDataSetChanged();
        }*/
        goodsFragment.getFoodAdapter().notifyDataSetChanged();
//        goodsFragment.getHAdapter().notifyDataSetChanged();

        List<FoodBean> flist = carAdapter.getData();
        int p = -1;//这个是标记判断什么用的，得了了解一下
        for (int i = 0; i < flist.size(); i++) {
            FoodBean fb = flist.get(i);
            if (fb.getFoodID() == foodBean.getFoodID()) {
                fb = foodBean;
                hasFood = true;
                if (foodBean.getFoodNum() == 0) {
                    p = i;
                } else {
                    carAdapter.setData(i, foodBean);
                }
            }
            total += fb.getFoodNum();
            if (typeSelect.containsKey(String.valueOf(fb.getCategory_id()))) {
                typeSelect.put(String.valueOf(fb.getCategory_id()), typeSelect.get(String.valueOf(fb.getCategory_id())) + fb.getFoodNum());
                Debbuger.LogE("typeSelect:" + typeSelect.get(String.valueOf(fb.getCategory_id())));

            } else {
                typeSelect.put(String.valueOf(fb.getCategory_id()), fb.getFoodNum());
            }
            amount = amount.add(fb.getPrice().multiply(BigDecimal.valueOf(fb.getFoodNum())));
        }
        if (p >= 0) {
            carAdapter.remove(p);
        } else if (!hasFood && foodBean.getFoodNum() > 0) {
            carAdapter.addData(foodBean);
            if (typeSelect.containsKey(String.valueOf(foodBean.getCategory_id()))) {
                typeSelect.put(String.valueOf(foodBean.getCategory_id()), typeSelect.get(String.valueOf(foodBean.getCategory_id())) + foodBean.getFoodNum());
            } else {
                typeSelect.put(String.valueOf(foodBean.getCategory_id()), foodBean.getFoodNum());
            }
            amount = amount.add(foodBean.getPrice().multiply(BigDecimal.valueOf(foodBean.getFoodNum())));
            total += foodBean.getFoodNum();
        }
        shopCarView.showBadge(total);
        goodsFragment.getCategoryAdapter().updateBadge(typeSelect);
        shopCarView.updateAmount(amount);
    }

    public void clearCar(View view) {
        ViewUtils.showClearCar(mContext, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clearCar();
            }
        });
    }

    private void clearCar() {

        List<FoodBean> flist = carAdapter.getData();
        for (int i = 0; i < flist.size(); i++) {
            FoodBean fb = flist.get(i);

            //这个SelectCount是被添加的数量，实际上要被记录应该是完成交易之后的
            fb.setFoodNum(0); //这个数量只是显示上的数量，这个操作就是让所有的item重复置0嘛，但是显然是加载了很多次无意义的清楚操作
            //ps我错了，添加了多少个就会清除多少次，没毛病的。上面那个是我的错误想法,这个的奥秘应该在适配器的getData里,因为是通过回调addData到carAdpter里的
//            所以getData（）的大小正好是操作了的那几个。没毛病。
        }
        carAdapter.setNewData(new ArrayList<FoodBean>());

//        goodsFragment.getHAdapter().notifyDataSetChanged();
        goodsFragment.getFoodAdapter().notifyDataSetChanged();//为什么会残留影像,因为 ：虽然说是重新绑定视图了，但是你没在哪里写有视图的默认不可见状态。
//        所以刷新的时候，数据是刷新了，但是视图还是保留原先的状态。仅此而已、、、 - -白眼
        goodsFragment.getCategoryAdapter().updateBadge(new HashMap<String, Integer>());
        goodsFragment.getCategoryAdapter().notifyDataSetChanged();
        shopCarView.showBadge(0);
        shopCarView.updateAmount(new BigDecimal(0.0));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }
}
