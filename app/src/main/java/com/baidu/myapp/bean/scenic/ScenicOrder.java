package com.baidu.myapp.bean.scenic;

import com.baidu.myapp.bean.userinfo.UserBean;

import org.litepal.crud.DataSupport;

import java.security.Timestamp;
import java.util.List;

/**
 * Created by Administrator on 2018/10/31.
 */

public class ScenicOrder extends DataSupport{
    private String orderID;//景区订单号
    private Timestamp orderTime;//下单的时间
    private int orderNumber;//订单的数量
    private List<UserBean> userBeanList;//该景区的下单的用户信息
    private List<ScenicProject> scenicProjectList;//订单中的景区项目信息
    private List<ScenicBean> scenicBeen;//订单中景区的信息

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<UserBean> getUserBeanList() {
        return userBeanList;
    }

    public void setUserBeanList(List<UserBean> userBeanList) {
        this.userBeanList = userBeanList;
    }

    public List<ScenicProject> getScenicProjectList() {
        return scenicProjectList;
    }

    public void setScenicProjectList(List<ScenicProject> scenicProjectList) {
        this.scenicProjectList = scenicProjectList;
    }

    public List<ScenicBean> getScenicBeen() {
        return scenicBeen;
    }

    public void setScenicBeen(List<ScenicBean> scenicBeen) {
        this.scenicBeen = scenicBeen;
    }

    @Override
    public String toString() {
        return "ScenicOrder{" +
                "orderID='" + orderID + '\'' +
                ", orderTime=" + orderTime +
                ", orderNumber=" + orderNumber +
                ", userBeanList=" + userBeanList +
                ", scenicProjectList=" + scenicProjectList +
                ", scenicBeen=" + scenicBeen +
                '}';
    }
}
