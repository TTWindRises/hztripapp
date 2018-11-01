package com.baidu.myapp.bean.food;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2018/10/29.
 */

public class FoodCategory extends DataSupport {
    private String CategoryID;
    private String CategoryName;

    public String getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(String categoryID) {
        CategoryID = categoryID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    @Override
    public String toString() {
        return "FoodCategory{" +
                "CategoryID='" + CategoryID + '\'' +
                ", CategoryName='" + CategoryName + '\'' +
                '}';
    }
}
