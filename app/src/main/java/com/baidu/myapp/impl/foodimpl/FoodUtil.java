package com.baidu.myapp.impl.foodimpl;

import android.content.Context;

import com.baidu.myapp.enums.FoodEnum;
import com.baidu.myapp.interfaces.ifood.IFoodBuy;

/**
 * Created by Administrator on 2018/12/5.
 */

public class FoodUtil {
    IFoodBuy iFoodBuy;
    public FoodUtil() {
    }

    private static FoodUtil foodUtil = new FoodUtil();
    public static FoodUtil getInstance() {
        return foodUtil;
    }
    public void setCallBack(IFoodBuy callback) {
        this.iFoodBuy = callback;
            iFoodBuy.CallBackFoodAddResult(FoodEnum.FOOD_BOTTOM_ICON_PICK);
        }


}
