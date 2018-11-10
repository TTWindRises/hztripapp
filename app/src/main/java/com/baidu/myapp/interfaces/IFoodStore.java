package com.baidu.myapp.interfaces;

import com.baidu.myapp.bean.food.FoodStore;

import java.util.List;

/**
 * Created by Administrator on 2018/11/7.
 */

public interface IFoodStore {
    boolean add(FoodStore foodStore);

    boolean deleteByID(String storeid);

    boolean deleteAll();

    boolean update(FoodStore foodStore);

    List<FoodStore> queryByID(String id);

    List<FoodStore> queryAll();

}
