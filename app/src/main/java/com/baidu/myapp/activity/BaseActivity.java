package com.baidu.myapp.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.baidu.myapp.util.Debbuger;

import static com.baidu.location.g.j.D;

public abstract class BaseActivity extends AppCompatActivity{
    protected Context mContext = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //无效
        Debbuger.LogE("当前活动："+getClass().getSimpleName());
        ActivityCollector.addActivity(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Debbuger.LogE("onDestroy");
        ActivityCollector.removeActivity(this);
    }

    public void close(View view) {
        finish();
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void goAccount(View view) {
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
}
