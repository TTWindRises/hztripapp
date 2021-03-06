package com.baidu.myapp.fragment.food;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.baidu.myapp.R;
import com.baidu.myapp.activity.FoodStoreActivity;
import com.baidu.myapp.adapter.FoodBeanRecyclerAdapter;
import com.baidu.myapp.adapter.food.FoodLeftRecyclerAdapter;
import com.baidu.myapp.adapter.food.FoodRightRecyclerAdapter;
import com.baidu.myapp.bean.food.FoodBean;
import com.baidu.myapp.bean.food.FoodCategory;
import com.baidu.myapp.detail.DetailActivity;
import com.baidu.myapp.fragment.BaseFragment;
import com.baidu.myapp.sticky.itemDecoration.GroupInfo;
import com.baidu.myapp.sticky.itemDecoration.StickySectionDecoration;
import com.baidu.myapp.util.Debbuger;
import com.baidu.myapp.util.foodutil.SpaceItemDecoration;
import com.baidu.myapp.view.HorizontalRecycleView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/12/15.
 */
public class FoodGoodsFragment extends BaseFragment {
    //实体类数据
    List<FoodBean> foodBeenList;
    List<FoodCategory> foodCategoryList;
    //商品类别列表
    private FoodLeftRecyclerAdapter mLeftCategoryAdapter;
    private RecyclerView right_recyclerView;
    private RecyclerView left_recyclerView;
    private FoodBeanRecyclerAdapter hadapter;
    private String storeid;
    private RelativeLayout shopCartMain;
    //碎片的主页面
    private HorizontalRecycleView horizontalRecycleView;
    private LinearLayout main_layout;
    //适配器
    FoodLeftRecyclerAdapter categoryAdapter;
    FoodRightRecyclerAdapter rightadapter;
    //存储含有标题的第一个含有商品类别名称的条目的下表
    private List<Integer> titlePois = new ArrayList<>();
    //上一个标题的小标
    private int lastTitlePoi;
    private LinearLayoutManager mLinearLayoutManager;

    private List<FoodBean> foodlist;

    public FoodGoodsFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_goods_fragment, container, false);
        initView(view);
        initList();
//        loadHorizontalFoodView();
        loadVerticalLeftView();
        loadVerticalRightView();
        initData();
        return view;
    }

    private void initList() {
        foodlist = new ArrayList<>();
        foodCategoryList = DataSupport.where("store_id=?", storeid).find(FoodCategory.class);
        if (foodCategoryList != null) {
//            Debbuger.LogE("存在category信息:" + foodCategoryList.toString());
        }
        int i = 0;
        titlePois.add(0);
        for (FoodCategory foodCategory : foodCategoryList) {
            if (foodCategory.getFoodBeanByCategoryId() != null) {
                foodlist.addAll(foodCategory.getFoodBeanByCategoryId());
                i += foodCategory.getFoodBeanByCategoryId().size();
                titlePois.add(i);
            }
        }
    }

    //TODO add category data
    private void initData() {
        mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rightadapter = new FoodRightRecyclerAdapter(getActivity(),foodlist, (FoodStoreActivity) getActivity());
        //right_recyclerView.addItemDecoration(new SpaceItemDecoration(15));
        right_recyclerView.setItemAnimator(new DefaultItemAnimator());
        right_recyclerView.setLayoutManager(mLinearLayoutManager);
        StickySectionDecoration.GroupInfoCallback callback = new StickySectionDecoration.GroupInfoCallback() {
            @Override
            public GroupInfo getGroupInfo(int position) {
                /**
                 * 分组逻辑，这里为了测试每5个数据为一组。大家可以在实际开发中
                 * 替换为真正的需求逻辑
                 */
                GroupInfo groupInfo = new GroupInfo();
                groupInfo.setPosition(position);
                groupInfo.setPois(titlePois);
                return groupInfo;

            }
        };
        categoryAdapter.setOnItemClickListener(new FoodLeftRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Debbuger.LogE("类别的Category:" + position);
                mLinearLayoutManager.scrollToPositionWithOffset(titlePois.get(position), 0);
                mLinearLayoutManager.setStackFromEnd(false);
                categoryAdapter.setCheckPosition(position);
            }
        });
        right_recyclerView.addItemDecoration(new StickySectionDecoration(getActivity(), callback));
        right_recyclerView.setAdapter(rightadapter);

        right_recyclerView.addOnItemTouchListener(new OnItemClickListener() {
                                                 @Override
                                                 public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                                     super.onItemChildClick(adapter, view, position);
                                                     if (view.getId() == R.id.food_main2) {
                                                         Intent intent = new Intent(getActivity(), DetailActivity.class);
                                                         intent.putExtra("food", (FoodBean)adapter.getData().get(position));
                                                         intent.putExtra("position", position);
                                                         getActivity().startActivity(intent);
                                                         ( getActivity()).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                                                     }
                                                 }
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        right_recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                for (int i = 0; i < titlePois.size() - 1; i++) {//这一段之前是没有执行的
                    if (mLinearLayoutManager.findFirstVisibleItemPosition() >= titlePois.get(i)) {
                        categoryAdapter.setCheckPosition(i);
                    }
                }

            }
        });
    }

    /**
     * 处理滑动 是两个ListView联动 反过来点击类目刷新item页面
     */
    private class MyOnGoodsScrollListener implements AbsListView.OnScrollListener {

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if (!(lastTitlePoi == firstVisibleItem)) {
                lastTitlePoi = firstVisibleItem;
                categoryAdapter.setCheckPosition(lastTitlePoi);
            }
        }
    }


    //横向列表
    private void loadHorizontalFoodView() {


        hadapter = new FoodBeanRecyclerAdapter(getActivity(), foodlist, (FoodStoreActivity) getActivity());
        horizontalRecycleView.addItemDecoration(new SpaceItemDecoration(8));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontalRecycleView.setItemAnimator(new DefaultItemAnimator());
        horizontalRecycleView.setLayoutManager(linearLayoutManager);
        horizontalRecycleView.setAdapter(hadapter);
        // mListView.setAdapter(new FoodBeanAdapter(FoodStoreActivity.this,foodBeanList));
    }

    //纵向左边列表
    private void loadVerticalLeftView() {


//        titlePois.remove(foodCategoryList.size());

        categoryAdapter = new FoodLeftRecyclerAdapter(getActivity(), foodCategoryList);
        left_recyclerView.addItemDecoration(new SpaceItemDecoration(0));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        left_recyclerView.setItemAnimator(new DefaultItemAnimator());
        left_recyclerView.setLayoutManager(linearLayoutManager);

        left_recyclerView.setAdapter(categoryAdapter);
    }

    //纵向右边列表
    private void loadVerticalRightView() {

    }

    private void initView(View view) {
        main_layout = view.findViewById(R.id.food_goods_main_layout);
//        horizontalRecycleView = view.findViewById(R.id.food_horizontal_list);
        left_recyclerView = view.findViewById(R.id.food_vertical_left_category_list);
        right_recyclerView = view.findViewById(R.id.food_store_vertical_right_list);
//        shopCartMain = view.findViewById(R.id.food_store_shop_cart_main);
    }


    public void setStoreID(String storeid) {
        this.storeid = storeid;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String event) {


    }
//        FoodLeftRecyclerAdapter categoryAdapter;

    public FoodRightRecyclerAdapter getFoodAdapter() {
        return rightadapter;
    }

    public FoodBeanRecyclerAdapter getHAdapter() {
        return hadapter;
    }

    public FoodLeftRecyclerAdapter getCategoryAdapter() {
        return categoryAdapter;
    }
/*

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
//        recyclerView.getLocationInWindow(recyclerPosition);

        PointF startF = new PointF();
        PointF endF = new PointF();
        PointF controllF = new PointF();

        startF.x = startPosition[0];
//        startF.y = startPosition[1] - recyclerPosition[1];
        startF.y = startPosition[1];
        endF.x = endPosition[0] + 40;
//        endF.y = endPosition[1] - recyclerPosition[1];
        endF.y = endPosition[1];
        controllF.x = endF.x;
        controllF.y = startF.y;

        final ImageView imageView = new ImageView(getActivity());
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
*/


}

