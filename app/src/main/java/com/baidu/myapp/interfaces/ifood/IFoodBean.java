package com.baidu.myapp.interfaces.ifood;

import com.baidu.myapp.bean.food.FoodBean;

import java.util.List;

/**
 * Created by Administrator on 2018/11/1.
 */

public interface IFoodBean {
    boolean add();

    boolean update(FoodBean foodBean);

    boolean deleteByID(String id);

    FoodBean queryByID(String id);

    List<FoodBean> queryAll();

}
