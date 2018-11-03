package com.baidu.myapp.bean.userinfo;

import com.baidu.myapp.bean.food.FoodOrder;
import com.baidu.myapp.bean.hotel.HotelOrder;
import com.baidu.myapp.bean.scenic.ScenicOrder;

import org.litepal.annotation.Column;

import java.util.List;

/**
 * Created by Administrator on 2018/9/26.
 */

public class UserBean {
    @Column(unique = true)
    private int userID;//用户唯一标识符
    private String userName;//用户名
    private String password;//用户密码
    private String headImg;//头像地址
    private String token;//登录验证信息
    private String phone_os;//设备信息
    private String phone_type;//设备类型
    private String appID;//软件的ID
    private String userDescribe;//用户个人描述信息
    private String userRealName;//用户的真实姓名
    private String userIDCARD;//用户的身份证号码——如果没有实名认证将关闭一些功能
    private double spendMoney;//花费了多少钱
    private double Distance;//走过了多少距离
    private int food_orderid;
    private int hotel_orderid;
    private int scenic_orderid;
    private List<FoodOrder> foodOrderList;//用户需要获取自己本身对应的所有食品订单资料
    private List<HotelOrder> hotelOrderList;//用户需要获取自己本身对应的所有酒店订单资料
    private List<ScenicOrder> scenicOrderList;//用户需要获取自己本身对应的所有景区订单资料

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhone_os() {
        return phone_os;
    }

    public void setPhone_os(String phone_os) {
        this.phone_os = phone_os;
    }

    public String getPhone_type() {
        return phone_type;
    }

    public void setPhone_type(String phone_type) {
        this.phone_type = phone_type;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getUserDescribe() {
        return userDescribe;
    }

    public void setUserDescribe(String userDescribe) {
        this.userDescribe = userDescribe;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getUserIDCARD() {
        return userIDCARD;
    }

    public void setUserIDCARD(String userIDCARD) {
        this.userIDCARD = userIDCARD;
    }

    public List<FoodOrder> getFoodOrderList() {
        return foodOrderList;
    }

    public void setFoodOrderList(List<FoodOrder> foodOrderList) {
        this.foodOrderList = foodOrderList;
    }

    public List<HotelOrder> getHotelOrderList() {
        return hotelOrderList;
    }

    public void setHotelOrderList(List<HotelOrder> hotelOrderList) {
        this.hotelOrderList = hotelOrderList;
    }

    public List<ScenicOrder> getScenicOrderList() {
        return scenicOrderList;
    }

    public void setScenicOrderList(List<ScenicOrder> scenicOrderList) {
        this.scenicOrderList = scenicOrderList;
    }

    public double getSpendMoney() {
        return spendMoney;
    }

    public void setSpendMoney(double spendMoney) {
        this.spendMoney = spendMoney;
    }

    public double getDistance() {
        return Distance;
    }

    public void setDistance(double distance) {
        Distance = distance;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "userName='" + userName + '\'' +
                ", userID=" + userID +
                ", password='" + password + '\'' +
                ", headImg='" + headImg + '\'' +
                ", token='" + token + '\'' +
                ", phone_os='" + phone_os + '\'' +
                ", phone_type='" + phone_type + '\'' +
                ", appID='" + appID + '\'' +
                ", userDescribe='" + userDescribe + '\'' +
                ", userRealName='" + userRealName + '\'' +
                ", userIDCARD='" + userIDCARD + '\'' +
                ", spendMoney=" + spendMoney +
                ", Distance=" + Distance +
                ", foodOrderList=" + foodOrderList +
                ", hotelOrderList=" + hotelOrderList +
                ", scenicOrderList=" + scenicOrderList +
                '}';
    }
}
