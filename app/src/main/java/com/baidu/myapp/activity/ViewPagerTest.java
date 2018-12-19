package com.baidu.myapp.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.myapp.R;
import com.baidu.myapp.adapter.food.TabFragmentAdapter;
import com.baidu.myapp.animate.AnimationUtil;
import com.baidu.myapp.bean.food.FoodStore;
import com.baidu.myapp.fragment.food.FoodEvaluateFragment;
import com.baidu.myapp.fragment.food.FoodGoodsFragment;
import com.baidu.myapp.util.foodutil.IndicatorLineUtil;
import com.baidu.myapp.view.HorizontalRecycleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/12/19.
 */

public class ViewPagerTest extends BaseActivity {
    HorizontalRecycleView mListView;
    //HorizontalRecycleView leftListView;
    FoodStore foodStore;
    private TextView red_ball;
    private ImageView bottom_icon;
    private TextView money;
    private TextView end;
    private RelativeLayout main_layout;
    private HorizontalRecycleView recyclerView;
    //两个碎片页面
    private ViewPager viewPager;
    //Fragment列表
    private List<Fragment> mFragments = new ArrayList<>();
    //列表标题
    private List<String> mTitles = new ArrayList<>();
    private TabFragmentAdapter adapter;
    private RelativeLayout shopCartMain;
    TabLayout slidingTabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager);
        setViewPager();

    }
    //两个Pager
    private void setViewPager() {
        //viewpage
        viewPager = (ViewPager) findViewById(R.id.food_store_view_pager);
        shopCartMain = (RelativeLayout) findViewById(R.id.food_store_shop_cart_main);
//        //全局
//        main_layout = (RelativeLayout) findViewById(R.id.activity_main);
        FoodGoodsFragment goodsFragment = new FoodGoodsFragment();
        goodsFragment.setStoreID("7");//让碎片能够找到相应的实体类
        FoodEvaluateFragment evaluateFragment = new FoodEvaluateFragment();
        mFragments.add(goodsFragment);
        mFragments.add(evaluateFragment);
        mTitles.add("商品");
        mTitles.add("评价");

        adapter = new TabFragmentAdapter(getSupportFragmentManager(),mFragments, mTitles);


        viewPager.setAdapter(adapter);
        slidingTabLayout = (TabLayout) findViewById(R.id.slidinglayout);
        slidingTabLayout.setupWithViewPager(viewPager);
        slidingTabLayout.post(new Runnable() {
            @Override
            public void run() {
                IndicatorLineUtil.setIndicator(slidingTabLayout,60,60);
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
//                        shopCartMain.startAnimation(
//                                AnimationUtil.createInAnimation(ViewPagerTest.this, shopCartMain.getMeasuredHeight()));
                        break;
                    case 1:

//                        //左右滑
//                        shopCartMain.startAnimation(
//                                AnimationUtil.createOutAnimation(ViewPagerTest.this, shopCartMain.getMeasuredHeight()));
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
