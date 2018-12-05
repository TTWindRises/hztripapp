package com.baidu.myapp.impl.foodimpl;

import com.baidu.myapp.bean.food.FoodBean;
import com.baidu.myapp.dao.food.FoodBeanDAO;

import java.util.List;

/**
 * Created by Administrator on 2018/11/30.
 */

public class FoodBeanIMPL  {
    FoodBeanDAO foodBeanDAO = new FoodBeanDAO();
    public FoodBeanIMPL( ) {

    }

    private static FoodBeanIMPL foodBeanIMPL = new FoodBeanIMPL();
    public static FoodBeanIMPL GetFood() {
        return foodBeanIMPL;

    }

   public void addAll(List<FoodBean> data) {
        for (FoodBean foodBean : data)
            foodBeanDAO.addFood(foodBean);
    }
}
