package com.baidu.myapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.baidu.myapp.R;
import com.baidu.myapp.impl.HeadViewImpl;
import com.baidu.myapp.interfaces.IHeadView;
import com.baidu.myapp.util.CircleCrop;
import com.baidu.myapp.util.Debbuger;
import com.baidu.myapp.util.FileUtils;
import com.baidu.myapp.view.HeadView;
import com.bumptech.glide.Glide;
import com.fengmap.android.map.FMMap;
import com.fengmap.android.map.FMMapUpgradeInfo;
import com.fengmap.android.map.FMMapView;
import com.fengmap.android.map.event.OnFMMapInitListener;

import static android.view.KeyCharacterMap.load;

/**
 * Created by Administrator on 2018/10/23.
 */

public class FNmapActivity extends BaseActivity implements OnFMMapInitListener {
    private FMMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fnmap);
        Debbuger.LogE("来到了蜂鸟三D地图");
        openMapByPath();

    }

    /**
     * 加载地图数据
     */


    private void openMapByPath() {
        Debbuger.LogE("加载蜂鸟地图");
        FMMapView mapView = (FMMapView) findViewById(R.id.map_view);
        mMap = mapView.getFMMap();
        mMap.setOnFMMapInitListener(FNmapActivity.this);
        //加载离线数据
        String path = FileUtils.getDefaultMapPath(this);
        String mapId = "90886";
        mMap.openMapById(mapId, true);
//         openMapById(id,true) 加载在线地图数据，并自动更新地图数据
//         mMap.openMapById(FileUtils.DEFAULT_MAP_ID,true);
    }

    @Override
    public void onMapInitSuccess(String s) {
        Debbuger.LogE("地图初始化成功");
    }

    @Override
    public void onMapInitFailure(String s, int i) {
        Debbuger.LogE("地图初始化失败");

    }

    @Override
    public boolean onUpgrade(FMMapUpgradeInfo fmMapUpgradeInfo) {
        return false;
    }
}
