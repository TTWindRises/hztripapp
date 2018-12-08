package com.baidu.myapp.impl.foodimpl;

import com.baidu.myapp.bean.food.FoodCategory;
import com.baidu.myapp.dao.food.FoodCategoryDAO;
import com.baidu.myapp.interfaces.ifood.IFoodCategory;

import java.util.List;

/**
 * Created by Administrator on 2018/12/8.
 */

public class FoodCategoryIMPL {
    FoodCategoryDAO foodCategoryDAO = new FoodCategoryDAO();
    public FoodCategoryIMPL() {
    }

    public static FoodCategoryIMPL foodCategoryIMPL = new FoodCategoryIMPL();

    public static FoodCategoryIMPL getInstance() {
        return foodCategoryIMPL;
    }

    public void addAllCategory(List<FoodCategory> foodCategories) {
        for (FoodCategory foodCategory : foodCategories)
            foodCategoryDAO.add(foodCategory);

    }
}
