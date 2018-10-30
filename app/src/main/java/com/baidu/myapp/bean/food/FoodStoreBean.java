package com.baidu.myapp.bean.food;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.List;

//像这种数据需要放在服务器上面吧
//只有一些个人资料可以放在sqlite上

public class FoodStoreBean extends DataSupport implements Serializable {
    private String StoreName;//店铺名称
    private double StoreLatitude;//店铺的坐标
    private double StoreLongtitude;
    private String StoreDistance;//距离要经过两点的直线距离计算，还是路程计算，还是线路路程的平均路程计算，这个数据要显示暂时写死.
    private String StoreCategory;//销售食品的类别
    private String StoreAddress;//商家地址
    private String StorePhone; //商家电话
    private String StoreHours; //营业时间
    private String StoreImg;//商家的图片
    private String StoreDescribe;//商家信息描述
    private int TotalSales;//食品的总销售量
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
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    public double getStoreLatitude() {
        return StoreLatitude;
    }

    public void setStoreLatitude(double storeLatitude) {
        StoreLatitude = storeLatitude;
    }

    public double getStoreLongtitude() {
        return StoreLongtitude;
    }

    public void setStoreLongtitude(double storeLongtitude) {
        StoreLongtitude = storeLongtitude;
    }

    public String getStoreDistance() {
        return StoreDistance;
    }

    public void setStoreDistance(String storeDistance) {
        StoreDistance = storeDistance;
    }

    public String getStoreCategory() {
        return StoreCategory;
    }

    public void setStoreCategory(String storeCategory) {
        StoreCategory = storeCategory;
    }

    public String getStoreAddress() {
        return StoreAddress;
    }

    public void setStoreAddress(String storeAddress) {
        StoreAddress = storeAddress;
    }

    public String getStorePhone() {
        return StorePhone;
    }

    public void setStorePhone(String storePhone) {
        StorePhone = storePhone;
    }

    public String getStoreHours() {
        return StoreHours;
    }

    public void setStoreHours(String storeHours) {
        StoreHours = storeHours;
    }

    public String getStoreImg() {
        return StoreImg;
    }

    public void setStoreImg(String storeImg) {
        StoreImg = storeImg;
    }

    public String getStoreDescribe() {
        return StoreDescribe;
    }

    public void setStoreDescribe(String storeDescribe) {
        StoreDescribe = storeDescribe;
    }

    public int getTotalSales() {
        return TotalSales;
    }

    public void setTotalSales(int totalSales) {
        TotalSales = totalSales;
    }
}
