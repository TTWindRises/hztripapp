package com.baidu.myapp.util.foodutil;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2018/12/10.
 */

public class Computational {
    public Computational() {
    }

    private static Computational computational = new Computational();

    public static Computational getInstance() {
        return computational;
    }

    public double getDiscount(String pre, String ori) {
        double pres = Double.parseDouble(pre);
        double oris = Double.parseDouble(ori);
        double discount = pres / oris;
        //方法一,利用字符串来保留1位小数，结果不会四舍五入
        //String dis1 = String.format("%0.1f", discount);
        //方法二，利用BigDecimal方法来获取的数据会四舍五入
        return new BigDecimal(discount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()*10;
    }
}
