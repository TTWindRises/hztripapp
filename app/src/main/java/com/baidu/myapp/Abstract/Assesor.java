package com.baidu.myapp.Abstract;

import com.baidu.myapp.util.Debbuger;

/**
 * Created by Administrator on 2018/10/26.
 */

public abstract class Assesor {
     public abstract void init();
     protected boolean ExitPresses() {
         Debbuger.LogE("抽象中的非抽象方法实现了固定的一部分");
         return false;
     }
}
