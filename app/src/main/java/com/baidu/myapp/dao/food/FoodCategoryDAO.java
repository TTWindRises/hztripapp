package com.baidu.myapp.dao.food;

import com.baidu.myapp.bean.food.FoodCategory;
import com.baidu.myapp.interfaces.ifood.IFoodCategory;
import com.baidu.myapp.util.Debbuger;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 2018/12/8.
 */

public class FoodCategoryDAO implements IFoodCategory {
    @Override
    public boolean add(FoodCategory foodCategory) {
        if (foodCategory.save()) {
            Debbuger.LogE("存储数据成功");
            return true;
        } else {
            Debbuger.LogE("存储失败");
        }
        return false;
    }

    @Override
    public boolean delete(String categoryid) {
        return false;
    }

    @Override
    public boolean update(String categoryid) {
        return false;
    }

    @Override
    public FoodCategory queryByID(String categoryid) {
        return null;
    }

    @Override
    public List<FoodCategory> queryAllCategory() {
        List<FoodCategory> foodCategoryList = DataSupport.findAll(FoodCategory.class);
        return foodCategoryList;
    }
}
