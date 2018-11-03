package com.baidu.myapp.bean.food;

import com.baidu.myapp.bean.userinfo.UserBean;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.security.Timestamp;
import java.util.List;

/**
 * Created by Administrator on 2018/10/29.
 */

public class FoodOrder extends DataSupport {
    @Column(unique = true)
    private int orderID;
    private List<FoodBean> foodBeans;
    private Timestamp orderTime;
    private int orderNumber;
    private List<UserBean> userBeanList;


    public List<FoodBean> getFoodBeans() {
        return foodBeans;
    }

    public void setFoodBeans(List<FoodBean> foodBeans) {
        this.foodBeans = foodBeans;
    }

    @Override
    public String toString() {
        return "FoodOrder{" +
                "orderID='" + orderID + '\'' +
                ", foodBeans=" + foodBeans +
                ", orderTime=" + orderTime +
                ", orderNumber=" + orderNumber +
                ", userBeanList=" + userBeanList +
                '}';
    }
}