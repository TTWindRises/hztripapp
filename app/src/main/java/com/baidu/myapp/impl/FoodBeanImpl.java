package com.baidu.myapp.impl;

import com.baidu.myapp.bean.food.FoodBean;
import com.baidu.myapp.interfaces.IFoodBean;
import com.baidu.myapp.util.Debbuger;

import org.litepal.crud.DataSupport;

import java.util.List;

import static org.litepal.crud.DataSupport.findAll;

/**
 * Created by Administrator on 2018/10/29.
 */

public class FoodBeanImpl implements IFoodBean {
    @Override
    public void addFood(FoodBean foodBean) {
        FoodBean food = foodBean;
        if (food.save()) {
            Debbuger.LogE("保存食品数据成功:" + food.toString());
        }
    }

    @Override
    public void deleteFoodByID(String foodID) {
        DataSupport.deleteAll(FoodBean.class, "foodid=?", foodID);
    }

    @Override
    public void deleteFoodAll() {
        //DataSupport.deleteAll();
    }

    @Override
    public void deleteFoodByName(String foodName) {
        DataSupport.deleteAll(FoodBean.class, "foodName=?", foodName);
    }

    @Override
    public void updateFood(FoodBean foodBean) {
        //不能去拿数据库默认的ID号去更新数据吧。视图层很难捕获到啊
        FoodBean food = foodBean;
        food.update(Long.parseLong(foodBean.getFoodID()));
    }

    @Override
    public FoodBean queryFoodByID(String foodID) {
        List<FoodBean> foodBean = DataSupport.where("foodid=?", foodID).find(FoodBean.class);
        return foodBean.get(0);
    }

    @Override
    public List<FoodBean> queryFoodAll() {
        List<FoodBean>foodBeanList= DataSupport.findAll(FoodBean.class);
        return foodBeanList;
    }

    @Override
    public void queryFoodOrderByID(String orderID) {

    }

    @Override
    public void queryFoodAllOreder() {

    }

    @Override
    public void addFoodOrder() {

    }

    @Override
    public void deleteFoodOrderByID() {

    }

    @Override
    public void deleteFoodOrderAll() {

    }

    @Override
    public void updateFoodOrderByID() {

    }
}
