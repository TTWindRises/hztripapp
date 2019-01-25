package com.baidu.myapp.sticky.itemDecoration;

import com.baidu.myapp.bean.food.FoodCategory;
import com.baidu.myapp.util.Debbuger;

import java.util.ArrayList;
import java.util.List;

import static com.baidu.location.g.j.G;
import static com.baidu.location.g.j.e;
import static com.baidu.location.g.j.f;

/**
 * Created by frank on 2017/4/14.
 */

public class GroupInfo {
    private int position;
    private List<Integer> pois = new ArrayList<>();

    public GroupInfo() {
    }

    public boolean isFirstViewInGroup() {
    for (int i=0;i<pois.size()-1;i++) {
        if (position == pois.get(i)) {
            return true;
        }
    }
        return false;

    }

    public boolean isLastViewInGroup() {
        for (int i=1;i<pois.size();i++) {
            if (position == pois.get(i)-1) {
                return true;
            }
        }
        return false;
    }
    public boolean onMaxLastItem() {
        return position == pois.get(pois.size()-1)-1;
    }
    public void setPosition(int position) {
        this.position = position;
    }

    public String getPoisTitle() {
        if (position >= 0 && position < 2) {
            return "热销";
        } else if (position >= 2 && position < 8) {
            return "推荐";
        } else if (position >= 8 && position < 10) {
            return "甜品";
        } else
            return "";
    }

    public String getPoisDecriation() {
        if (position >= 0 && position < 2) {
            return "又香又脆又美味";
        } else if (position >= 2 && position < 8) {
            return "秀色可餐";
        } else if (position >= 8 && position < 10) {
            return "清凉可口夏日必备";
        } else
            return "";
    }
    public void setPois(List<Integer> pois) {
        this.pois = pois;
    }

}
