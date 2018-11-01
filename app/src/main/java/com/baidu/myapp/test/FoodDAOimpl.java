package com.baidu.myapp.test;

import com.baidu.myapp.bean.scenic.ScenicBean;
import com.baidu.myapp.util.Debbuger;

import java.util.List;

/**
 * Created by Administrator on 2018/11/1.
 */

public class FoodDAOimpl extends DAOimpl {

    public void add() {
        Debbuger.LogE("FoodDAOimplADD");
    }


    public void delete() {
        Debbuger.LogE("FoodDAOimplDELETE");
    }


    public void update() {
        Debbuger.LogE("FoodDAOimplUPDATE");
    }

    public void queryByID() {
        Debbuger.LogE("FoodDAOimplQueryByID");
    }

    public List<ScenicBean> queryAll() {
        Debbuger.LogE("FoodDAOimplQueryAll");
    }
}
