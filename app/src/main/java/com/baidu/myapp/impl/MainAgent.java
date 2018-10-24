package com.baidu.myapp.impl;

import android.util.Log;

import com.baidu.myapp.interfaces.ILoginVerify;

/**
 * Created by Administrator on 2018/10/19.
 */

public class MainAgent extends ILoginVerify {
    @Override
    public void Init() {
        mLog("初始化啦");
    }

    @Override
    public void Login() {
        mLog("登录验证啦");
    }

    @Override
    public void Logout() {
        mLog("登出啦");
    }

    @Override
    public void Pay() {
        mLog("支付成功了啦");
    }

    @Override
    public void Exit() {
        mLog("退出成功了");

    }

    private void mLog(String s) {
        Log.e("ZZR", s);
    }




}
