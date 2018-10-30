package com.baidu.myapp.impl;

import com.baidu.myapp.interfaces.IHeadView;
import com.baidu.myapp.util.Debbuger;

/**
 * Created by Administrator on 2018/10/25.
 */

public class HeadViewImpl implements IHeadView {
    @Override
    public void initHeadView() {
        Debbuger.LogE("初始化头像");
    }
}
