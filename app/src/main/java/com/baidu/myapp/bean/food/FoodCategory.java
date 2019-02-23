package com.baidu.myapp.bean.food;

import com.baidu.myapp.bean.scenic.ScenicBean;
import com.baidu.myapp.util.Debbuger;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 2018/10/29.
 */

public class FoodCategory extends DataSupport {
    @Column(unique = true)
    private int categoryID;
    private String categoryName;
    private String store_id;
    private List<FoodBean> foodBeanList;

    public List<FoodBean> getFoodBeanList() {
        return foodBeanList;
    }

    public void setFoodBeanList(List<FoodBean> foodBeanList) {
        this.foodBeanList = foodBeanList;
    }

    public List<FoodBean> getFoodBeanByCategoryId() {
        return DataSupport.where("category_id=?", String.valueOf(categoryID)).find(FoodBean.class);
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }
    public void saveFoodCategory(List<FoodCategory> categories) {
        for (FoodCategory category : categories) {
            if (category.save()) {
            } else {
                Debbuger.LogE("保存失败");
            }
        }

    }
    @Override
    public String toString() {
        return "FoodCategory{" +
                "categoryID=" + categoryID +
                ", categoryName='" + categoryName + '\'' +
                ", store_id='" + store_id + '\'' +
                ", foodBeanList=" + foodBeanList +
                '}';
    }
}
