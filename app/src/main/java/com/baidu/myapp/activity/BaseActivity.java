package com.baidu.myapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.baidu.myapp.util.Debbuger;

import static com.baidu.location.g.j.D;

public class BaseActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().hide();
        Debbuger.LogE("当前活动："+getClass().getSimpleName());
        ActivityCollector.addActivity(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
