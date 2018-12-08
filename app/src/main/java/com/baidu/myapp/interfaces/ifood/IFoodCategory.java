package com.baidu.myapp.interfaces.ifood;

import com.baidu.myapp.bean.food.FoodCategory;

import java.util.List;

/**
 * Created by Administrator on 2018/12/8.
 */

public interface IFoodCategory {
    boolean add(FoodCategory foodCategory);

    boolean delete(String categoryid);

    boolean update(String categoryid);

    FoodCategory queryByID(String categoryid);

    List<FoodCategory> queryAllCategory();

}
