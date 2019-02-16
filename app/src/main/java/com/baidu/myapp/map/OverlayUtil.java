package com.baidu.myapp.map;

import android.os.Bundle;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.myapp.R;
import com.baidu.myapp.bean.food.FoodStore;
import com.baidu.myapp.bean.scenic.ScenicBean;

import java.util.List;

import static com.baidu.location.g.a.f;
import static com.baidu.myapp.R.id.map;

/**
 * Created by Administrator on 2018/11/6.
 */

public class OverlayUtil {
    BaiduMap map;
    public OverlayUtil() {
    }
    public OverlayUtil(BaiduMap baiduMap) {
        this.map = baiduMap;
    }

    public void addFoodStoreOverly(BaiduMap map, FoodStore foodStore) {
        map.clear();
        LatLng latLng = new LatLng(foodStore.getStoreLatitude(), foodStore.getStoreLongtitude());
        MarkerOptions options = new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_marka)).zIndex(11);
        Marker marker = (Marker) map.addOverlay(options);
        Bundle arg0 = new Bundle();
        arg0.putSerializable("FoodStore", foodStore);
        marker.setExtraInfo(arg0);
    }
    public void addOverlyByLatLng(LatLng latLng,int img,String text) {

        MarkerOptions options = new MarkerOptions().position(latLng).
                icon(BitmapDescriptorFactory.fromResource(img))
                .zIndex(14).scaleX(0.4f).scaleY(0.4f)
                ;
       map.addOverlay(options);
        OverlayOptions mTextOptions = new TextOptions()
                .text(text) //文字内容
//                    .bgColor(0xAAFFFF00) //背景色
                .fontSize(28) //字号
                .fontColor(0xFFF00FFF) //文字颜色
                .rotate(0) //旋转角度
                .position(latLng);
        map.addOverlay(mTextOptions);
    }

    public void addTextByLatLng(LatLng latLng,String text) {
        OverlayOptions mTextOptions = new TextOptions()
                .text(text) //文字内容
//                    .bgColor(0xAAFFFF00) //背景色
                .fontSize(28) //字号
                .fontColor(0xFFF00FFF) //文字颜色
                .rotate(0) //旋转角度
                .position(latLng);
        map.addOverlay(mTextOptions);
    }
    public void addFoodStoreAllOverly(List<FoodStore> foodStores) {
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
    public void addScenicAllOverly(List<ScenicBean> scenicBeens) {
        map.clear();
        for (ScenicBean scenicBean : scenicBeens) {
            LatLng latLng = new LatLng(scenicBean.getScenicLatitude(), scenicBean.getScenicLongtitude());
            LatLng latLng2 = new LatLng(scenicBean.getScenicLatitude()-0.006f, scenicBean.getScenicLongtitude());
            MarkerOptions options = new MarkerOptions().position(latLng).
                    icon(BitmapDescriptorFactory.fromResource(scenicBean.getScenicOverlayImg()))
                    .zIndex(14).scaleX(0.45f).scaleY(0.45f)
                    ;
            Marker marker = (Marker) map.addOverlay(options);
            //构建TextOptions对象
            OverlayOptions mTextOptions = new TextOptions()
                    .text(scenicBean.getScenicName()) //文字内容
//                    .bgColor(0xAAFFFF00) //背景色
                    .fontSize(34) //字号
                    .fontColor(0xFFFF00FF) //文字颜色
                    .rotate(0) //旋转角度
                    .position(latLng);
            String text = " ";
            if (scenicBean.getScenicPrice() == 0) {
                text = "免费景区";
            }

            OverlayOptions mTextOption1 = new TextOptions()
                    .text(text) //文字内容
//                    .bgColor(0xAAFFFF00) //背景色
                    .fontSize(24) //字号
                    .fontColor(0xFFFF00FF) //文字颜色
                    .rotate(0) //旋转角度
                    .align(TextOptions.ALIGN_CENTER_HORIZONTAL,TextOptions.ALIGN_TOP)
                    .position(latLng2);
//            OverlayOptions mTextOptions2 = new TextOptions()
//                    .text("门票:￥"+scenicBean.getScenicPrice()) //文字内容
////                    .bgColor(0xAAFFFF00) //背景色
//                    .fontSize(28) //字号
//                    .fontColor(0xFFFF00FF) //文字颜色
//                    .rotate(0) //旋转角度
//                    .position(latLng1);
//            "门票:￥"+scenicBean.getScenicPrice()

//在地图上显示文字覆盖物
            map.addOverlay(mTextOptions);
            map.addOverlay(mTextOption1);
//            map.addOverlay(mTextOptions2);
            Bundle arg0 = new Bundle();
            arg0.putSerializable("ScenicBean", scenicBean);
            marker.setExtraInfo(arg0);//因为每次存放的marker对象都是不一样的，所以不用担心会存放的是一个数组在里面，或者拿到的只是最后一个存放的数据这种问题

        }
    }
}
