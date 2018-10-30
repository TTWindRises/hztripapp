package com.baidu.myapp.bean.food;

import com.baidu.myapp.R;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class FoodBean extends DataSupport {
    private String foodID;
    private String foodName;
    private int foodPrice;
    private String foodDescribe;
    private String foodImg;
    private String foodCategoryID; //食品类别号，归属于哪一个类别,考虑要加一个类目表，方便查询
    private int foodSales;//单个食品的销量,初始为零，然后不能低于零，每个月要重新计算一次,因为显示的是上一个月的销量
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
    //食品构造初始化
    public FoodBean() {
    }

    public FoodBean(String foodID, String foodName, int foodPrice, String foodDescribe, String foodImg) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodDescribe = foodDescribe;
        this.foodImg = foodImg;
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

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public String getFoodImg() {
        return foodImg;
    }

    public void setFoodImg(String foodImg) {
        this.foodImg = foodImg;
    }

    public List<FoodBean> getDeliciousFoodBeans() {
        return mDeliciousFoodBeans;
    }

    public void setDeliciousFoodBeans(List<FoodBean> deliciousFoodBeans) {
        mDeliciousFoodBeans = deliciousFoodBeans;
    }
}
