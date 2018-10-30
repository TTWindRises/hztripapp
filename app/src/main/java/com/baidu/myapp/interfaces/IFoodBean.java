package com.baidu.myapp.interfaces;

import com.baidu.myapp.bean.food.FoodBean;

import java.util.List;

/**
 * Created by Administrator on 2018/10/29.
 */

public interface IFoodBean {
    void addFood(FoodBean foodBean);

    void deleteFoodByID(String foodID);

    void deleteFoodAll();

    void deleteFoodByName(String foodName);

    void updateFood(FoodBean foodBean);

    FoodBean queryFoodByID(String foodID);

    List<FoodBean> queryFoodAll();

    void queryFoodOrderByID(String orderID);

    void queryFoodAllOreder();

    void addFoodOrder();

    void deleteFoodOrderByID();

    void deleteFoodOrderAll();

    void updateFoodOrderByID();

}
