package com.baidu.myapp.bean.food;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 2018/10/29.
 */

public class FoodOrder extends DataSupport {
    private String orderID;
    private List<FoodBean> foodBeans;
}