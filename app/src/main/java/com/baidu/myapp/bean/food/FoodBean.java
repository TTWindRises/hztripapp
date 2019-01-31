package com.baidu.myapp.bean.food;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;

import com.baidu.myapp.util.foodutil.ViewUtils;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.math.BigDecimal;

public class FoodBean extends DataSupport {
    @Column(unique = true)
    private int foodID;//食品的编号
    private String foodName;//食品的名称
    private int foodNum;//食品的数量
    private String foodPresentPrice;//食品的实际价格
    private String foodOriginalPrice; //食品的原价
    private String foodDescribe;//食品的介绍描述信息
    private String foodPraise;
    private String foodImg;//食品的展示图片
    private int category_id; //食品类别号，归属于哪一个类别,考虑要加一个类目表，方便查询
    private int food_order_id;
    private int foodShelvesState;//上架状态，0下架，1上架。
    private int foodSales;//单个食品的销量,初始为零，然后不能低于零，每个月要重新计算一次,因为显示的是上一个月的销量
    //食物与商店之间的多对多关系
    private String store_id;//属于哪家商店，利用这个id可以查寻到商家的信息

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getFoodSales() {
        return foodSales;
    }

    public void setFoodSales(int foodSales) {
        this.foodSales = foodSales;
    }

    //食品构造初始化
    public FoodBean() {
    }

    @Override
    public String toString() {
        return "FoodBean{" +
                "foodID=" + foodID +
                ", foodName='" + foodName + '\'' +
                ", category_id='" + category_id + '\'' +
//                ", food_order_id=" + food_order_id +
//                ", store_id='" + store_id + '\'' +
//                ", shelvesState='" + foodShelvesState + '\'' +
                ", foodNum='" + foodNum + '\'' +
                '}';
    }

    public int getFoodShelvesState() {
        return foodShelvesState;
    }

    public void setFoodShelvesState(int foodShelvesState) {
        this.foodShelvesState = foodShelvesState;
    }

    public String getFoodPraise() {
        return foodPraise;
    }

    public void setFoodPraise(String foodPraise) {
        this.foodPraise = foodPraise;
    }

    public int getFood_order_id() {
        return food_order_id;
    }

    public void setFood_order_id(int food_order_id) {
        this.food_order_id = food_order_id;
    }

    public FoodBean(int foodID, String foodName, int foodNum, String foodPresentPrice, String foodOriginalPrice, String foodDescribe, String foodImg, int foodCategoryID, int foodSales, String store_id) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.foodNum = foodNum;
        this.foodPresentPrice = foodPresentPrice;
        this.foodOriginalPrice = foodOriginalPrice;
        this.foodDescribe = foodDescribe;
        this.foodImg = foodImg;
        this.category_id = foodCategoryID;
        this.foodSales = foodSales;
        this.store_id = store_id;
    }
    public String getFoodPresentPrice() {
        return foodPresentPrice;
    }

    public void setFoodPresentPrice(String foodPresentPrice) {
        this.foodPresentPrice = foodPresentPrice;
    }

    public String getFoodOriginalPrice() {
        return foodOriginalPrice;
    }

    public void setFoodOriginalPrice(String foodOriginalPrice) {
        this.foodOriginalPrice = foodOriginalPrice;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }


    public String getFoodDescribe() {
        return foodDescribe;
    }

    public void setFoodDescribe(String foodDescribe) {
        this.foodDescribe = foodDescribe;
    }

    public int getFoodNum() {
        return foodNum;
    }

    public void setFoodNum(int foodNum) {
        this.foodNum = foodNum;
    }

    public int getFoodID() {

        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    public String getFoodImg() {
        return foodImg;
    }

    public void setFoodImg(String foodImg) {
        this.foodImg = foodImg;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public BigDecimal getPrice() {
        return new BigDecimal(getFoodPresentPrice());
    }

    public SpannableString getStrPrice(Context context) {
        String priceStr = String.valueOf(getPrice());
        SpannableString spanString = new SpannableString("¥" + priceStr);
        AbsoluteSizeSpan span = new AbsoluteSizeSpan(ViewUtils.sp2px(context, 11));
        spanString.setSpan(span, 0, 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return spanString;
    }

    public SpannableString getStrPrice(Context context, BigDecimal price) {
        String priceStr = String.valueOf(price);
        SpannableString spanString = new SpannableString("¥" + priceStr);
        AbsoluteSizeSpan span = new AbsoluteSizeSpan(ViewUtils.sp2px(context, 11));
        spanString.setSpan(span, 0, 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return spanString;
    }
}
