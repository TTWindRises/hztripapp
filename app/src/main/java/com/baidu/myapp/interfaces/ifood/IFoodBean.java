package com.baidu.myapp.interfaces.ifood;

import com.baidu.myapp.bean.food.FoodBean;
import com.baidu.myapp.interfaces.IFoodStore;

import java.util.List;

/**
 * Created by Administrator on 2018/11/30.
 */

public interface IFoodBean {
    boolean addFood(FoodBean foodBean);

    boolean deleteByFoodID(String foodid);

    boolean updateFood(FoodBean foodBean);

    List<FoodBean> queryAllFood();
}
