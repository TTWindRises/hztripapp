package com.baidu.myapp.dao.food;

import com.baidu.myapp.bean.food.FoodBean;
import com.baidu.myapp.bean.food.FoodStore;
import com.baidu.myapp.interfaces.IFoodStore;
import com.baidu.myapp.interfaces.ifood.IFoodBean;
import com.baidu.myapp.util.Debbuger;

import org.litepal.crud.DataSupport;

import java.util.List;

import static com.baidu.location.g.j.D;
import static com.baidu.location.g.j.e;
import static com.baidu.location.g.j.n;
import static com.baidu.location.g.j.t;
import static org.litepal.crud.DataSupport.deleteAll;
import static org.litepal.crud.DataSupport.where;

/**
 * Created by Administrator on 2018/11/28.
 */

public class FoodBeanDAO implements IFoodBean {


    @Override
    public boolean addFood(FoodBean foodBean) {

            if (foodBean.save()) {
                Debbuger.LogE("存储数据成功");
                return true;
            } else {
                Debbuger.LogE("存储食品信息失败");
            }


        return false;
    }

    @Override
    public boolean deleteByFoodID(String foodid) {
        int i = DataSupport.deleteAll(FoodBean.class, "foodid=?", foodid);
        if (i > 0) {

            Debbuger.LogE("删除了" + i + "条数据");
            return true;
        }
        return false;
    }

    @Override
    public boolean updateFood(FoodBean foodBean) {
        if (DataSupport.where("foodid=?", String.valueOf(foodBean.getFoodID())).find(FoodBean.class) == null) {
            Debbuger.LogE("更新失败,找不到数据foodid=" + foodBean.getFoodID() + "的数据");
        } else {
            foodBean.updateAll("foodid=? and store_id=?", String.valueOf(foodBean.getFoodID()), foodBean.getStore_id());
            return true;
        }

        return false;
    }

    @Override
    public List<FoodBean> queryAllFood() {
        if (DataSupport.findAll(FoodBean.class) != null) {
            return DataSupport.findAll(FoodBean.class);
        } else {
            Debbuger.LogE("不存在数据");
        }
        return null;
    }
}
