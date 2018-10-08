package com.baidu.bikenavi_demo.Helper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.bikenavi_demo.R;
import com.baidu.bikenavi_demo.activity.MyActivity;
import com.baidu.bikenavi_demo.bean.scenic.ScenicBean;
import com.baidu.bikenavi_demo.bean.scenic.SpotBean;
import com.baidu.bikenavi_demo.search.BusLineSearchDemo;
import com.baidu.bikenavi_demo.search.RoutePlanDemo;
import com.baidu.bikenavi_demo.test.Markein;
import com.baidu.bikenavi_demo.test.OverlayDemo;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

public class FoodStoreDataHelper extends MyActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sql_data);
        //创建数据库
        Button btn = (Button) findViewById(R.id.create_databases);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LitePal.getDatabase();
                Toast.makeText(FoodStoreDataHelper.this,"创建成功",Toast.LENGTH_LONG).show();

            }
        });
        Button add_btn = (Button) findViewById(R.id.add_data);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/**
 *  infos.add(new ScenicBean(24.622001,111.56721, R.drawable.stefan,"姑婆山","距离1000米",1456));
 infos.add(new ScenicBean(24.582822,111.559746, R.drawable.teacher,"十八水","距离1033米",856));
 infos.add(new ScenicBean(24.416049,111.519692, R.drawable.stefan,"贺州学院","距离134米",3234));
 infos.add(new ScenicBean(24.255476,111.209192, R.drawable.stefan,"黄瑶古镇","距离2142米",423));
 infos.add(new ScenicBean(24.521079,111.621376, R.drawable.stefan,"玉石林","距离2142米",423));
 */
               /* SpotBean spotBean = new SpotBean();
                spotBean.setLatitude(24.414709);7
                spotBean.setLongtitude(111.517293)7
                spotBean.setSpotDescribe("高大上");
                spotBean.setSpotName("贺州学院西校区-艺术楼");*/
                ScenicBean scenicBean = new ScenicBean();
                scenicBean.setSpname("黄瑶古镇");
                scenicBean.setLatitude(24.255476);
                scenicBean.setLongtitude(111.209192);
                scenicBean.setImgId(R.drawable.teacher);
                scenicBean.setDistance("500米");
                scenicBean.setZan(500);






                if(scenicBean.save()){
                    Toast.makeText(FoodStoreDataHelper.this, "success", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(FoodStoreDataHelper.this, "error", Toast.LENGTH_SHORT).show();
                }

            }
        });
        Button delete_btn = (Button) findViewById(R.id.delete_data);
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataSupport.deleteAll(ScenicBean.class,"id=?","4");

            }
        });

        Button into_test = (Button) findViewById(R.id.into_test);
        into_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodStoreDataHelper.this, Markein.class);
                startActivity(intent);
            }
        });
        Button btn_overlay = (Button) findViewById(R.id.test_overlay);
        btn_overlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodStoreDataHelper.this, OverlayDemo.class);
                startActivity(intent);
            }
        });
        Button btn_bus = (Button) findViewById(R.id.bus_plan);
        btn_bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodStoreDataHelper.this, BusLineSearchDemo.class);
                startActivity(intent);

            }
        });
        Button btnrouteplan= (Button) findViewById(R.id.routeplan);
        btnrouteplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodStoreDataHelper.this, RoutePlanDemo.class);
                startActivity(intent);
            }
        });

    }
}
