package com.baidu.myapp.bean.food;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2018/10/29.
 */

public class FoodCategory extends DataSupport {
    @Column(unique = true)
    private int categoryID;
    private String categoryName;
    private String store_id;

    @Override
    public String toString() {
        return "FoodCategory{" +
                "categoryID='" + categoryID + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", stroe_id='" + store_id + '\'' +
                '}';
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
}
