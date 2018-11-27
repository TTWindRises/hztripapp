package com.baidu.myapp.impl.foodimpl;

import com.baidu.myapp.bean.food.FoodStore;
import com.baidu.myapp.dao.food.FoodStoreDAO;
import com.baidu.myapp.util.Debbuger;

import java.util.List;

/**
 * Created by Administrator on 2018/11/7.
 */

public class FoodStoreIMPL {
    private FoodStoreIMPL( ) {
        Debbuger.LogE("创建一个新的对象");
    }

    private static FoodStoreIMPL foodStoreIMPL= new FoodStoreIMPL();
    public static synchronized FoodStoreIMPL Get() {

  /*      if (foodStoreIMPL == null) {
            synchronized (FoodStoreIMPL.class){
                Debbuger.LogE("饱汉来了");
                return foodStoreIMPL;
            }


        }*/
        return foodStoreIMPL;

    }

    FoodStoreDAO dao = new FoodStoreDAO();

    public void addFoodStore(List<FoodStore> foodStores) {
        for (FoodStore foodStore:foodStores)
        dao.add(foodStore);
    }
}
