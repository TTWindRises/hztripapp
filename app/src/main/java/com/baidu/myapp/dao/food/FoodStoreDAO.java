package com.baidu.myapp.dao.food;

import com.baidu.myapp.bean.food.FoodStore;
import com.baidu.myapp.interfaces.IFoodStore;
import com.baidu.myapp.util.Debbuger;

import org.litepal.crud.DataSupport;

import java.util.List;

import static org.litepal.crud.DataSupport.where;

/**
 * Created by Administrator on 2018/11/7.
 */

public class FoodStoreDAO implements IFoodStore{
    public FoodStoreDAO() {
        Debbuger.LogE("创建了FoodStoreDao对象");
    }

    @Override
   public boolean add(FoodStore foodStore) {
        if (DataSupport.where("storeid=?", foodStore.getStoreID()).find(FoodStore.class) == null) {

            if (foodStore.save()) {
                Debbuger.LogE("存储数据成功");
            } else {
                Debbuger.LogE("存储数据失败");
            }
        } else {
            Debbuger.LogE("已存在商家编号"+foodStore.getStoreID()+"的商店信息");
        }

     /*  List<FoodStore> list= DataSupport.where("storeid=?", foodStore.getStoreID()).find(FoodStore.class);

        if (list==null) {
            foodStore.save();
            return true;
        }else {
            foodStore.save();
            Debbuger.LogE("已存在商品编号："+foodStore.getStoreID()+"\n数据信息为:"+list.toString());
        }*/
        return false;
    }

    @Override
    public boolean deleteByID(String storeid) {
        int i=DataSupport.deleteAll(FoodStore.class, "storeid=?", storeid);
        if (i > 0) {
            Debbuger.LogE("删除了"+i+"条数据");
            return true;
        }
        Debbuger.LogE("删除时找不到目标");
        return false;
    }

    @Override
    public boolean deleteAll() {
        int i=DataSupport.deleteAll(FoodStore.class);
        if (i > 0) {
            Debbuger.LogE("删除了"+i+"条数据");
            return true;
        }
        Debbuger.LogE("删除时找不到目标");
        return false;
    }

    @Override
    public boolean update(FoodStore foodStore) {
        return false;
    }

    @Override
    public List<FoodStore> queryByID(String storeid) {
        List<FoodStore> foodStores = where("storeid=?", storeid).find(FoodStore.class);
        if (foodStores != null) {
            Debbuger.LogE("查询成功："+foodStores.toString());
            return foodStores;
        }
        return null;
    }

    @Override
    public List<FoodStore> queryAll() {
        List<FoodStore> foodStores = DataSupport.findAll(FoodStore.class);
        if (foodStores != null) {
            Debbuger.LogE("查询成功："+foodStores.toString());
            return foodStores;
        }
        return null;
    }
}
