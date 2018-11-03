package com.baidu.myapp.bean.food;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import static android.R.attr.id;

public class FoodBean extends DataSupport {

    @Column(unique = true)
    private int foodID;//食品的编号



    private String foodName;//食品的名称
    private int foodNum;//食品的数量
    private int foodPrice;//食品的价格
    private String foodDescribe;//食品的介绍描述信息
    private String foodImg;//食品的展示图片
    private String foodCategoryID; //食品类别号，归属于哪一个类别,考虑要加一个类目表，方便查询
    private int foodSales;//单个食品的销量,初始为零，然后不能低于零，每个月要重新计算一次,因为显示的是上一个月的销量
    //食物与商店之间的多对多关系
    private String store_id;//属于哪家商店，利用这个id可以查寻到商家的信息

    public String getFoodCategoryID() {
        return foodCategoryID;
    }

    public void setFoodCategoryID(String foodCategoryID) {
        this.foodCategoryID = foodCategoryID;
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

    public FoodBean(int foodID, String foodName, int foodPrice, String foodDescribe, String foodImg) {
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
    @Override
    public String toString() {
        return "FoodBean{" +
                "foodID=" + foodID +
                ", foodName='" + foodName + '\'' +
                ", foodNum=" + foodNum +
                ", foodPrice=" + foodPrice +
                ", foodDescribe='" + foodDescribe + '\'' +
                ", foodImg='" + foodImg + '\'' +
                ", foodCategoryID='" + foodCategoryID + '\'' +
                ", foodSales=" + foodSales +
                ", store_id='" + store_id + '\'' +
                '}';
    }
}
