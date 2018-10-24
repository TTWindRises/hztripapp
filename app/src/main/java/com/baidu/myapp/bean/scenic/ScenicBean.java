package com.baidu.myapp.bean.scenic;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by 曾志荣年 on 2018/5/17.
 */

public class ScenicBean extends DataSupport implements Serializable {
    private double latitude;
    private double longtitude;
    private int ImgId;
    private int overlayImgId;
    private String spname;
   /* private int doorPrice;

    private int visits;*/


    private String distance;
    private int zan;
    private static final long serialVersionUID=-1010711755;


    public int getOverlayImgId() {
        return overlayImgId;
    }

    public void setOverlayImgId(int overlayImgId) {
        this.overlayImgId = overlayImgId;
    }
    public int getImgId() {
        return ImgId;
    }

    public void setImgId(int imgId) {
        ImgId = imgId;
    }

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

    public String getSpname() {
        return spname;
    }

    public void setSpname(String spname) {
        this.spname = spname;
    }



    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getZan() {
        return zan;
    }

    public void setZan(int zan) {
        this.zan = zan;
    }


}
