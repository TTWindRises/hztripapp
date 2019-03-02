package com.baidu.myapp.util.scenicutil;

import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.myapp.bean.scenic.ScenicBean;

/**
 * Created by Administrator on 2019/2/26.
 */

public class IsRang {
    LatLng latLng;
    MyLocationData locationData;
    public IsRang(LatLng latLng,MyLocationData locationData) {
        this.latLng = latLng;
        this.locationData = locationData;
    }

   public Boolean onRang() {
       return false;
   }

    public static Boolean IsInArea(IsRang isRang) {
        return isRang.onRang();
    }
    public class OnScenicRang extends IsRang {

        public OnScenicRang(LatLng latLng, MyLocationData locationData) {
            super(latLng, locationData);

        }

        public Boolean onRang(ScenicBean scenicBean, MyLocationData locationData) {

            return true;
        }
    }
    public class OnSpotRang extends IsRang {
        public OnSpotRang(LatLng latLng, MyLocationData locationData) {
            super(latLng, locationData);
        }
    }
}
