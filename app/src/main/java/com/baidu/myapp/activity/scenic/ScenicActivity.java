package com.baidu.myapp.activity.scenic;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.myapp.MainActivity;
import com.baidu.myapp.R;
import com.baidu.myapp.activity.BaseActivity;
import com.baidu.myapp.activity.FNmapActivity;
import com.baidu.myapp.adapter.CarAdapter;
import com.baidu.myapp.adapter.food.TabFragmentAdapter;
import com.baidu.myapp.bean.food.FoodStore;
import com.baidu.myapp.bean.hotel.HotelBean;
import com.baidu.myapp.bean.scenic.ScenicBean;
import com.baidu.myapp.bean.scenic.ScenicProject;
import com.baidu.myapp.bean.scenic.spot.GetDataBean;
import com.baidu.myapp.fragment.food.FoodEvaluateFragment;
import com.baidu.myapp.fragment.food.FoodMainFragment;
import com.baidu.myapp.fragment.hotel.HotelMainFragment;
import com.baidu.myapp.fragment.scenic.ScenicProjectFragment;
import com.baidu.myapp.fragment.scenic.ScenicShowFragment;
import com.baidu.myapp.util.CircleCrop;
import com.baidu.myapp.util.Debbuger;
import com.baidu.myapp.util.GlideRoundTransform;
import com.baidu.myapp.util.foodutil.IndicatorLineUtil;
import com.baidu.myapp.view.HorizontalRecycleView;
import com.baidu.myapp.view.foodview.ShopCarView;
import com.bumptech.glide.Glide;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import static com.baidu.location.g.j.D;

/**
 * Created by Administrator on 2019/2/18.
 */

public class ScenicActivity extends BaseActivity {
    HorizontalRecycleView mListView;
    //HorizontalRecycleView leftListView;
    ScenicBean scenic;
    ScenicShowFragment scenicShowFragment;
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
        setContentView(R.layout.scenic_main);
        init();
        setCollsapsing();
        setViewPager();
    }


    //头
    private void init() {
        scenicShowFragment = new ScenicShowFragment();
        Bundle extras = getIntent().getExtras();
        scenic = (ScenicBean) extras.get("Scenic");
        //模拟数据从网络加载
        GetDataBean getDataBean = new GetDataBean();
        getDataBean.LoadLoacalSpotBean();
        getDataBean.LoadFoodData();
        getDataBean.LoadProjectBean();
        getDataBean.LoadHotelBean();
        scenicShowFragment.setScenicId(String.valueOf(scenic.getScenicId()));

        //viewpage
        viewPager = (ViewPager) findViewById(R.id.scenic_view_pager);

        //全局
        main_layout = (CoordinatorLayout) findViewById(R.id.activity_main);
//        recyclerView = (HorizontalRecycleView) findViewById(R.id.food_horizontal_list);
        //轮播图
   /*     ImageView shuffling = (ImageView) findViewById(R.id.scenic_shuffling);
        Glide.with(ScenicActivity.this)
                .load(R.drawable.store_head_bg)
                .placeholder(R.drawable.food_store_placeholder_rectangle)
                .into(shuffling);*/

        TextView textView = (TextView) findViewById(R.id.scenic_title);
        ImageView headBG = (ImageView) findViewById(R.id.scenic_store_head_bg);
        ImageView headImg = (ImageView) findViewById(R.id.scenic_store_head_img);
        ImageView back = (ImageView) findViewById(R.id.scenic_store_back_img);
        ImageView zan = (ImageView) findViewById(R.id.scenic_store_prise);
        //
        TextView score = (TextView) findViewById(R.id.scenic_score);
        TextView sell = (TextView) findViewById(R.id.scenic_sentiment);
        TextView distance = (TextView) findViewById(R.id.scenic_distance);
        TextView K1 = (TextView) findViewById(R.id.scenic_bar_1);
        TextView K2 = (TextView) findViewById(R.id.scenic_bar_2);

        //优惠券
        TextView price = (TextView) findViewById(R.id.scenic_tickets_price);
        final LinearLayout tickets = (LinearLayout) findViewById(R.id.scenic_tickets);
//        TextView full = (TextView) findViewById(R.id.scenic_tickets_describe);
        final TextView get = (TextView) findViewById(R.id.scenic_tickets_buy);
        if (scenic.getScenicPrice() == 0) {
            price.setText("免费游玩");
//        full.setText("门票");
            get.setVisibility(View.GONE);

        } else {
            get.setVisibility(View.VISIBLE);
            price.setText("￥"+(int)scenic.getScenicPrice());
//        full.setText("门票");
            get.setText("购买门票");
            get.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (scenic.isTickets()==0) {
                        tickets.setBackgroundColor(ContextCompat.getColor(ScenicActivity.this, R.color.gray));
                        scenic.setTickets(1);
                        get.setText("已购买");
//                        scenic.update(Long.valueOf(scenic.getScenicId()));
                    } else {
                        tickets.setBackgroundColor(Color.parseColor("#ef7a82"));
                        get.setText("购买门票");
                        scenic.setTickets(0);
//                        scenic.update(Long.valueOf(scenic.getScenicId()));
                    }
                }
            });
        }


        //公告
        TextView notice = (TextView) findViewById(R.id.scenic_describe);
        notice.setText(scenic.getScenicDescribe());
        //设置商店名称
        textView.setText(scenic.getScenicName());
        //设置评价、销售量、距离
        score.setText("评价4.6");
        K1.setText("  |  ");
        sell.setText("人气1100");
        K2.setText("  |  ");
        distance.setText("距离23.3km");
        //返回键
        Glide.with(ScenicActivity.this).load(R.drawable.back)
                .into(back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScenicActivity.this.finish();
            }
        });
        //顶部背景图片资源加载
//        headBG.setImageResource(R.drawable.hezhouxueyuan_quanjing);
        Glide.with(ScenicActivity.this).load(Integer.parseInt(scenic.getScenicImg()))
                .centerCrop().transform(new GlideRoundTransform(this))
                .placeholder(R.drawable.food_store_placeholder_square)
                .dontAnimate()
                .into(headBG);
        headBG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScenicActivity.this, FNmapActivity.class);
                startActivity(intent);
            }
        });
        //头像资源加载
//        Glide.with(ScenicActivity.this).load(Integer.parseInt(scenic.getScenicImg()))
//                .centerCrop().transform(new GlideRoundTransform(this))
//                .placeholder(R.drawable.food_store_placeholder_square)
//                .dontAnimate()
//                .into(headImg);
        //赞
        Glide.with(ScenicActivity.this)
                .load(R.drawable.scenic_zan6)
                .transform(new CircleCrop(this))
                .override(100, 100)
                .into(zan);


        zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, scenic.getScenicName() + "已加入攻略计划", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //两个Pager
    private void setViewPager() {


//        goodsFragment.setStoreID(foodStore.getStoreID());//让碎片能够找到相应的实体类
        ScenicProjectFragment scenicProjectFragment = new ScenicProjectFragment();
        scenicProjectFragment.setData(DataSupport.where("scenic_id=?", String.valueOf(scenic.getScenicId())).find(ScenicProject.class));
        FoodMainFragment foodMainFragment = new FoodMainFragment();
        foodMainFragment.setData(DataSupport.where("scenic_id=?", String.valueOf(scenic.getScenicId())).find(FoodStore.class));
        HotelMainFragment hotelMainFragment = new HotelMainFragment();
        hotelMainFragment.setData(DataSupport.where("scenic_id=?", String.valueOf(scenic.getScenicId())).find(HotelBean.class));

        mFragments.add(scenicShowFragment);
        mFragments.add(scenicProjectFragment);
        mFragments.add(hotelMainFragment);
        mFragments.add(foodMainFragment);
        mTitles.add("景点");
        mTitles.add("项目");
        mTitles.add("酒店");
        mTitles.add("美食");
        adapter = new TabFragmentAdapter(getSupportFragmentManager(), mFragments, mTitles);


        viewPager.setAdapter(adapter);
        slidingTabLayout = (TabLayout) findViewById(R.id.slidinglayout);
        slidingTabLayout.setupWithViewPager(viewPager);
        slidingTabLayout.post(new Runnable() {
            @Override
            public void run() {
                IndicatorLineUtil.setIndicator(slidingTabLayout, 5, 5);
            }
        });

    }

    private void setCollsapsing() {
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.touming));

    }

    @Override
    protected void onStart() {
        super.onStart();
        Debbuger.LogE(getLocalClassName()+"onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Debbuger.LogE(getLocalClassName()+"onStop()");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Debbuger.LogE(getLocalClassName()+"onDestroy()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Debbuger.LogE(getLocalClassName()+"onPause()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Debbuger.LogE(getLocalClassName()+"onPause()");
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //保存销毁之前的数据
        Debbuger.LogE(getLocalClassName()+"onSaveInstanceState()");

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Debbuger.LogE(getLocalClassName()+"onRestoreInstanceState()");
    }

}
