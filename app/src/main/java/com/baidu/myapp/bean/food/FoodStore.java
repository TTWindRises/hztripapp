package com.baidu.myapp.bean.food;

import com.baidu.myapp.util.Debbuger;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;

//像这种数据需要放在服务器上面吧
//只有一些个人资料可以放在sqlite上

public class FoodStore extends DataSupport implements Serializable {
    //@Column(unique = true)
    private String storeID;
    private String storeName;//店铺名称
    private double storeLatitude;//店铺的坐标
    private double storeLongtitude;
    private String storeDistance;//距离要经过两点的直线距离计算，还是路程计算，还是线路路程的平均路程计算，这个数据要显示暂时写死.
    private String storeCategory;//销售食品的类别
    private String storeAddress;//商家地址
    private String storePraise;
    private String storePhone; //商家电话
    private String storeHours; //营业时间
    private String storeNotice;//商家的公告
    private int scenic_id;
    private String store_heard_img;//商家的头像
    private String storeImg;//商家的头部的背景图片
    private String storeDescribe;//商家信息描述
    private int TotalSales;//食品的总销售量
    private static final long serialVersionUID = 1L;
    private List<FoodBean> foodBeanList = new ArrayList<FoodBean>();

    public String getStorePraise() {
        return storePraise;
    }

    public void setStorePraise(String storePraise) {
        this.storePraise = storePraise;
    }

    public int getScenic_id() {
        return scenic_id;
    }

    public void setScenic_id(int scenic_id) {
        this.scenic_id = scenic_id;
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<FoodBean> getFoodBeanList() {
        return foodBeanList;
    }

    public List<FoodBean> getFoodBeanAll() {
        Debbuger.LogE("data:"+DataSupport.where("store_id=?",storeID).find(FoodBean.class).toString());
        return DataSupport.where("store_id=?",storeID).find(FoodBean.class);
    }

    public void setFoodBeanList(List<FoodBean> foodBeanList) {
        this.foodBeanList = foodBeanList;
    }

    public FoodStore() {
    }

    public String getStore_heard_img() {
        return store_heard_img;
    }

    public void setStore_heard_img(String store_heard_img) {
        this.store_heard_img = store_heard_img;
    }

    public String getStoreNotice() {
        return storeNotice;
    }

    public void setStoreNotice(String storeNotice) {
        this.storeNotice = storeNotice;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public double getStoreLatitude() {
        return storeLatitude;
    }

    public void setStoreLatitude(double storeLatitude) {
        this.storeLatitude = storeLatitude;
    }

    public double getStoreLongtitude() {
        return storeLongtitude;
    }

    public void setStoreLongtitude(double storeLongtitude) {
        this.storeLongtitude = storeLongtitude;
    }

    public String getStoreDistance() {
        return storeDistance;
    }

    public void setStoreDistance(String storeDistance) {
        this.storeDistance = storeDistance;
    }

    public String getStoreCategory() {
        return storeCategory;
    }

    public void setStoreCategory(String storeCategory) {
        this.storeCategory = storeCategory;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    public String getStoreHours() {
        return storeHours;
    }

    public void setStoreHours(String storeHours) {
        this.storeHours = storeHours;
    }

    public String getStoreImg() {
        return storeImg;
    }

    public void setStoreImg(String storeImg) {
        this.storeImg = storeImg;
    }

    public String getStoreDescribe() {
        return storeDescribe;
    }

    public void setStoreDescribe(String storeDescribe) {
        this.storeDescribe = storeDescribe;
    }

    public int getTotalSales() {
        return TotalSales;
    }

    public void setTotalSales(int totalSales) {
        TotalSales = totalSales;
    }

    public void saveAllFoodStore(List<FoodStore> foodStores) {
        for (FoodStore foodStore : foodStores)
            if (foodStore.save()) {

            } else {
                Debbuger.LogE("保存失败");
            }
    }
    @Override
    public String toString() {
        return "FoodStore{" +
                "storeName='" + storeName + '\'' +
                ", storeLatitude=" + storeLatitude +
                ", storeLongtitude=" + storeLongtitude +
                ", storeDistance='" + storeDistance + '\'' +
                ", storeCategory='" + storeCategory + '\'' +
                ", storeAddress='" + storeAddress + '\'' +
                ", storePhone='" + storePhone + '\'' +
                ", storeHours='" + storeHours + '\'' +
                ", storeImg='" + storeImg + '\'' +
                ", storeDescribe='" + storeDescribe + '\'' +
                ", TotalSales=" + TotalSales +
                ", foodBeanList=" + foodBeanList +
                '}';
    }
}
