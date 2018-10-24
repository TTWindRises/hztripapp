/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.myapp;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.fengmap.android.FMMapSDK;

import org.litepal.LitePal;


public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
        FMMapSDK.init(this);
        LitePal.initialize(this);
    }
}
