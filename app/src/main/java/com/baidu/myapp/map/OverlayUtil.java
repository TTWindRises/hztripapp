package com.baidu.myapp.map;

import android.os.Bundle;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.myapp.R;
import com.baidu.myapp.bean.food.FoodStore;

import java.util.List;

/**
 * Created by Administrator on 2018/11/6.
 */

public class OverlayUtil {
    public void addFoodStoreOverly(BaiduMap map, FoodStore foodStore) {
        map.clear();
        LatLng latLng = new LatLng(foodStore.getStoreLatitude(), foodStore.getStoreLongtitude());
        MarkerOptions options = new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_marka)).zIndex(5);
        Marker marker = (Marker) map.addOverlay(options);
        Bundle arg0 = new Bundle();
        arg0.putSerializable("FoodStore", foodStore);
        marker.setExtraInfo(arg0);
    }

    public void addFoodStoreAllOverly(BaiduMap map, List<FoodStore> foodStores) {
        map.clear();
        for (FoodStore foodStore : foodStores) {
            LatLng latLng = new LatLng(foodStore.getStoreLatitude(), foodStore.getStoreLongtitude());
            MarkerOptions options = new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_marka)).zIndex(5);
            Marker marker = (Marker) map.addOverlay(options);
            Bundle arg0 = new Bundle();
            arg0.putSerializable("FoodStore", foodStore);
            marker.setExtraInfo(arg0);//因为每次存放的marker对象都是不一样的，所以不用担心会存放的是一个数组在里面，或者拿到的只是最后一个存放的数据这种问题

        }
    }
}
