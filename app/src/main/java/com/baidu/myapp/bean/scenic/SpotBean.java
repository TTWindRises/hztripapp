package com.baidu.myapp.bean.scenic;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

public class SpotBean extends DataSupport implements Serializable{
    private static final long serialVersionUID=1L;
    double latitude;
    double longtitude;
    String spotName;
    String spotDescribe;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public String getSpotName() {
        return spotName;
    }

    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }

    public String getSpotDescribe() {
        return spotDescribe;
    }

    public void setSpotDescribe(String spotDescribe) {
        this.spotDescribe = spotDescribe;
    }
}
