package com.baidu.myapp.bean.food;

import com.baidu.myapp.R;

import java.util.ArrayList;
import java.util.List;

public class FoodBean {



    private String storeId;
    private String foodName;
    private int foodPrice;
    private String foodDescribe;
    private int imgFood;
    //食物与商店之间的多对多关系
    private List<FoodStoreBean> foodStore;

    public List<FoodStoreBean> getFoodStore() {
        return foodStore;
    }

    public void setFoodStore(List<FoodStoreBean> foodStore) {
        this.foodStore = foodStore;
    }

    public static List<FoodBean> getmDeliciousFoodBeans() {
        return mDeliciousFoodBeans;
    }

    public static void setmDeliciousFoodBeans(List<FoodBean> mDeliciousFoodBeans) {
        FoodBean.mDeliciousFoodBeans = mDeliciousFoodBeans;
    }

    public static List<FoodBean> mDeliciousFoodBeans = new ArrayList<FoodBean>();

    public FoodBean(String foodName, int foodPrice, String foodDescribe, int imgFood){
        this.foodName=foodName;
        this.foodPrice=foodPrice;
        this.foodDescribe=foodDescribe;
        this.imgFood=imgFood;
    }
    static {
        mDeliciousFoodBeans.add(new FoodBean("西瓜奶茶",15,"美味可口夏日必备", R.drawable.ic_favorite_red));
        mDeliciousFoodBeans.add(new FoodBean("青苹果奶茶",10,"美味可口夏日必备", R.drawable.ic_favorite));

    }
    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodDescribe() {
        return foodDescribe;
    }

    public void setFoodDescribe(String foodDescribe) {
        this.foodDescribe = foodDescribe;
    }

    public int getImgFood() {
        return imgFood;
    }

    public void setImgFood(int imgFood) {
        this.imgFood = imgFood;
    }

    public List<FoodBean> getDeliciousFoodBeans() {
        return mDeliciousFoodBeans;
    }

    public void setDeliciousFoodBeans(List<FoodBean> deliciousFoodBeans) {
        mDeliciousFoodBeans = deliciousFoodBeans;
    }
}
