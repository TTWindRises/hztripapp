package com.baidu.myapp.util.scenicutil;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.myapp.util.Debbuger;

/**
 * Created by Administrator on 2019/2/27.
 */

public class PointMove {
    LatLng lat;
    LatLng locationData;
    public PointMove(LatLng latLng, LatLng locationData) {
        this.lat = latLng;
        this.locationData = locationData;
    }

    private double latd = 0.01;
    private double longd = 0.01;
  /*  private int PONIT_ON_LEFT =1;
    private int PONIT_ON_RIGHT=2;
    private int PONIT_ON_TOP=3;
    private int PONIT_ON_BOTTOM=4;*/

    private void DetermineLacation() {
        if (lat.latitude < locationData.latitude) {
            latd = -0.001;
        } else {
            latd = 0.001;
        }
        if (lat.longitude < locationData.longitude) {
            longd = -0.001;
        } else {
            longd = 0.001;
        }
    }

    public LatLng Move() {

        DetermineLacation();
        if (Math.abs(lat.latitude - locationData.latitude) > 0.0015
                ||Math.abs(lat.longitude - locationData.longitude)>0.00125) {
            Debbuger.LogE("Moving");
            return new LatLng(locationData.latitude + latd, locationData.longitude + longd);
        } else {
            Debbuger.LogE("Moving null");
            return null;
        }
    }
}
