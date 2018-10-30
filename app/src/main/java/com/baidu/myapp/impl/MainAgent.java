package com.baidu.myapp.impl;

import android.util.Log;

import com.baidu.myapp.Abstract.Assesor;
import com.baidu.myapp.interfaces.ILoginVerify;
import com.baidu.myapp.util.Debbuger;

/**
 * Created by Administrator on 2018/10/19.
 */

public class MainAgent extends Assesor {


    @Override
    public void init() {
        Debbuger.LogE("----init---");
    }

    @Override
    public boolean ExitPresses() {
        Debbuger.LogE("-----exit----");
        return true;
    }
}
