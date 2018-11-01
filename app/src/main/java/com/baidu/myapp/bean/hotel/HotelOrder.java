package com.baidu.myapp.bean.hotel;

import com.baidu.myapp.bean.userinfo.UserBean;

import java.security.Timestamp;
import java.util.List;

/**
 * Created by Administrator on 2018/10/31.
 */

public class HotelOrder {
    private String orderID;
    private Timestamp orderTime;
    private int orderNumber;
    private List<UserBean> userBeanList;

}
