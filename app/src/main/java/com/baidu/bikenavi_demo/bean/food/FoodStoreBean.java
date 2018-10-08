package com.baidu.bikenavi_demo.bean.food;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.List;

//像这种数据需要放在服务器上面吧
//只有一些个人资料可以放在sqlite上

public class FoodStoreBean extends DataSupport implements Serializable {
    private String storeName;
    private double latitude;
    private double longtitude;
    private static final long serialVersionUID = 1L;
    private List<FoodBean> foodBeanList;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<FoodBean> getFoodBeanList() {
        return foodBeanList;
    }

    public void setFoodBeanList(List<FoodBean> foodBeanList) {
        this.foodBeanList = foodBeanList;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }
}
